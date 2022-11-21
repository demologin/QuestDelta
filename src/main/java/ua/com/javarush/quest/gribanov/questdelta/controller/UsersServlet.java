package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.dto.UserDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Role;
import ua.com.javarush.quest.gribanov.questdelta.service.UserService;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(name = "Users", value = AppURL.USERS_URL)
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.get();
        Optional<UserDTO> optUser=userService.getUser(req.getSession());
        if(optUser.isPresent()){
            UserDTO userDTO = optUser.get();
            if(Role.ADMINISTRATOR.equals(userDTO.getRole())){
                req.setAttribute("users", userService.getAllUsersDTO());
                forwardToJSP(req, resp, USERS_JSP);
            } else {
                forwardToJSP(req, resp, HOME_JSP);
            }
        }
    }
}
