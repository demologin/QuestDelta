package ua.com.javarush.quest.gribanov.questdelta.service;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


public class ImageService {
    private static final ImageService imageService = new ImageService();
    private static final String QUEST_IMAGES_FOLDER = "questImages";
    private static final String USER_IMAGES_FOLDER = "userImages";
    private static final String NO_IMAGE_PNG = "no-image.png";
    private static final List<String> EXTENSIONS = List.of(".jpg", ".jpeg", ".png", ".bmp", ".gif", ".webp", ".jfif", ".tiff");


    private static final Path questImages = LoaderService.getTempFolder().resolve(QUEST_IMAGES_FOLDER);
    private static final Path userImages = LoaderService.getTempFolder().resolve(USER_IMAGES_FOLDER);

    private ImageService() {
    }

    public static ImageService get() {
        return imageService;
    }

    public Optional<Path> getImagePath(Key key, String filename) {
        Path imagesFolder;
        if (key.equals(Key.QUEST)) {
            imagesFolder = questImages;
        } else {
            imagesFolder = userImages;
        }
        return EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .findAny()
                .or(() -> Optional.of(imagesFolder.resolve(NO_IMAGE_PNG)));
    }

    public static void uploadImage(String fileName, String fileExt, InputStream inputStream, Key key) {
        Path imagesFolder;
        String fullFileName = fileName + fileExt;
        if (key.equals(Key.QUEST)) {
            imagesFolder = questImages;
        } else {
            imagesFolder = userImages;
        }
        deleteOldFiles(fileName, key);

        try (inputStream) {
            if (inputStream.available() > 0) {
                Files.copy(inputStream, imagesFolder.resolve(fullFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteOldFiles(String filename, Key key) {
        Path imagesFolder;
        if (key.equals(Key.QUEST)) {
            imagesFolder = questImages;
        } else {
            imagesFolder = userImages;
        }
        EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .forEach(p -> {
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void fillFoldersIfEmpty() {
        String resourcePathString = FilenameUtils.getPath(Objects.requireNonNull(ImageService.class.getResource("/")).getPath());
        Path resourcesPath = Path.of(resourcePathString.replace("%20", " "));
        Path defaultQuestImages = resourcesPath.resolve(QUEST_IMAGES_FOLDER);
        Path defaultUserImages = resourcesPath.resolve(USER_IMAGES_FOLDER);

        try {
            copyDefaultFiles(defaultQuestImages, questImages);
            copyDefaultFiles(defaultUserImages, userImages);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void copyDefaultFiles(Path defaultFolder, Path targetFolder) throws IOException {
        if (Files.exists(defaultFolder)) {
            if (!Files.exists(targetFolder)) {
                Files.createDirectory(targetFolder);
            }
            try (Stream<Path> pathStream = Files.list(targetFolder)) {
                if (pathStream.findAny().isEmpty()) {
                    copyFiles(defaultFolder, targetFolder);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private void copyFiles(Path source, Path target) {
        try (Stream<Path> pathStream = Files.list(source)) {
            pathStream.forEach(f -> {
                try {
                    Files.copy(f, target.resolve(f.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Key {
        QUEST,
        USER
    }
}
