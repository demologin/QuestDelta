package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.dto.UserDTO;
import ua.com.javarush.quest.gribanov.questdelta.service.UserService;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(name = "Login", value = AppURL.LOGIN_URL)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.get();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        Optional<UserDTO> optionalUser = userService.findUser(login, password);
        if (optionalUser.isPresent()) {
            req.getSession().setAttribute("user", optionalUser.get());
            if("on".equals(rememberMe)){
                req.getSession().setMaxInactiveInterval(0);
            } else {
                req.getSession().setMaxInactiveInterval(600);
            }
            forwardToJSP(req, resp, HOME_JSP);
        } else {
            resp.sendError(500, "Incorrect login or password");
            req.setAttribute("errorMessage", "hhh");
            //forwardToJSP(req, resp, LOGIN_JSP);
        }
    }
}
