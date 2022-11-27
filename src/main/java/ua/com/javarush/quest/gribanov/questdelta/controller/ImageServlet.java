package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.service.ImageService;
import ua.com.javarush.quest.gribanov.questdelta.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(value={AppURL.USER_IMAGES_URL, AppURL.QUEST_IMAGES_URL})
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Optional<Path> file = resolveImagePath(req);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.contains("user_images")) {
            UserService userService = UserService.get();
            userService.updateAvatar(req);
            resp.sendRedirect(AppURL.USER_URL);
        }
    }

    private Optional<Path> resolveImagePath(HttpServletRequest req){
        String requestURI = req.getRequestURI();
        ImageService imageService = ImageService.get();
        ImageService.Key key;
        String target;
        if (requestURI.contains("user_images")) {
            target = req.getContextPath() + "/user_images/";
            key = ImageService.Key.USER;
        } else if (requestURI.contains("quest_images")){
            target = req.getContextPath() + "/quest_images/";
            key = ImageService.Key.QUEST;
        } else {
            return Optional.empty();
        }
        String nameImage = requestURI.replace(target, "");
        return imageService.getImagePath(key, nameImage);
    }

}
