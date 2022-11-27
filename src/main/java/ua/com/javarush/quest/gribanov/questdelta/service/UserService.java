package ua.com.javarush.quest.gribanov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.gribanov.questdelta.dto.UserDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.*;
import ua.com.javarush.quest.gribanov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.gribanov.questdelta.repository.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService userService = new UserService();
    private final UserRepository userRepository = UserRepository.get();
    private final String AVATAR_FILENAME_PREFIX = "userImage_";
    private final String ADMIN_LOGIN = "admin";
    private final String ATTRIBUTE_USER = "user";
    private final String ATTRIBUTE_USER_ID = "userId";
    private final String ATTRIBUTE_USER_NAME = "name";
    private final String ATTRIBUTE_USER_LOGIN = "login";
    private final String ATTRIBUTE_USER_PASSWORD = "password";
    private final String ATTRIBUTE_USER_AVATAR = "image";

    private UserService (){

    }

    public static UserService get(){
        return userService;
    }

    public Optional<UserDTO> findUser(String login, String password){
        User userTemplate = User.builder()
                .login(login)
                .password(password)
                .build();
        Optional<User> user = userRepository.find(userTemplate)
                .findFirst();

        return user.isPresent() ? Mapper.user.getDTO(user.get()) : Optional.empty();
    }

    public Optional<UserDTO> getUser(HttpSession session) {
        Object user = session.getAttribute(ATTRIBUTE_USER);
        return user != null
                ? Optional.of((UserDTO) user)
                : Optional.empty();
    }

    public Collection<UserDTO> getAllUsersDTO(){
        Collection<User> users = userRepository.getAll();
        return users.stream()
                .filter(u->!(ADMIN_LOGIN.equals(u.getLogin())))
                .map(Mapper.user::getDTO)
                .map(o-> o.orElse(null))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> createUser(String name, String login, String password){
        User userTemplate = User.builder()
                .login(login)
                .build();
        if (userRepository.find(userTemplate).findAny().isPresent()){
            return Optional.empty();
        } else {
            userTemplate.setName(name);
            userTemplate.setPassword(password);
            userTemplate.setRole(Role.GUEST);
            userRepository.add(userTemplate);
            return Mapper.user.getDTO(userTemplate);
        }
    }

    public void updateUser(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter(ATTRIBUTE_USER_ID));

        String name = request.getParameter(ATTRIBUTE_USER_NAME);
        String login = request.getParameter(ATTRIBUTE_USER_LOGIN);

        User user = userRepository.getByID(id);
        User updatedUser = User.builder()
                .name(name)
                .login(login)
                .role(user.getRole())
                .avatar(user.getAvatar())
                .password(user.getPassword())
                .createdQuests(user.getCreatedQuests())
                .playingGames(user.getPlayingGames())
                .build();
        if (request.getParameterMap().containsKey(ATTRIBUTE_USER_PASSWORD)){
            String password = request.getParameter(ATTRIBUTE_USER_PASSWORD);
            updatedUser.setPassword(password);
        }
        updatedUser.setId(id);
        userRepository.update(updatedUser);
        Optional<UserDTO> optUserDTO = Mapper.user.getDTO(updatedUser);
        optUserDTO.ifPresent(userDTO -> request.getSession().setAttribute("user", userDTO));
    }
    public void updateAvatar(HttpServletRequest request) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter(ATTRIBUTE_USER_ID));
        User user = userRepository.getByID(id);
        Part imageData = request.getPart(ATTRIBUTE_USER_AVATAR);
        InputStream inputStream = imageData.getInputStream();
        if (inputStream.available() > 0) {
            String userIdString = request.getParameter(ATTRIBUTE_USER_ID);
            String filename = imageData.getSubmittedFileName();
            String ext = filename.substring(filename.lastIndexOf("."));
            String currentUserAvatar = user.getAvatar();
            String newUserAvatar = AVATAR_FILENAME_PREFIX + userIdString;
            if (null == currentUserAvatar || currentUserAvatar.isEmpty() || !currentUserAvatar.equals(newUserAvatar)){
                user.setAvatar(newUserAvatar);
            }
            ImageService.uploadImage(newUserAvatar, ext, inputStream, ImageService.Key.USER);
        }
        userRepository.update(user);
        Optional<UserDTO> optUserDTO = Mapper.user.getDTO(user);
        optUserDTO.ifPresent(userDTO -> request.getSession().setAttribute("user", userDTO));
    }

    public void updateUserRole(Long id, Role role){
        User user = userRepository.getByID(id);
        user.setRole(role);
        userRepository.update(user);
    }

    public void deleteUser(Long id){
        GameRepository gameRepository = GameRepository.get();
        QuestRepository questRepository = QuestRepository.get();
        QuestionRepository questionRepository = QuestionRepository.get();
        AnswerRepository answerRepository = AnswerRepository.get();
        User user = userRepository.getByID(id);
        if (Objects.nonNull(user)){
            userRepository.remove(user);
        }
        Collection<Game> playingGames = user.getPlayingGames();
        if (Objects.nonNull(playingGames) && !playingGames.isEmpty()) {
            for (Game playingGame : playingGames) {
                gameRepository.remove(playingGame);
            }
        }
        Collection<Quest> createdQuests = user.getCreatedQuests();
        if (Objects.nonNull(createdQuests) && !createdQuests.isEmpty()){
            for (Quest createdQuest : createdQuests) {
                Collection<Question> questions = createdQuest.getQuestions();
                if (Objects.nonNull(questions) && !questions.isEmpty()){
                    for (Question question : questions) {
                        Collection<Answer> answers = question.getAnswers();
                        if (Objects.nonNull(answers) && !answers.isEmpty()){
                            for (Answer answer : answers) {
                                answerRepository.remove(answer);
                            }
                        }
                        questionRepository.remove(question);
                    }
                }
                questRepository.remove(createdQuest);
            }
        }
    }
}
