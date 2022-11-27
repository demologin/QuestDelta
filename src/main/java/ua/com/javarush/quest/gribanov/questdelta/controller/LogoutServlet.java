package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;

import java.io.IOException;
import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(name = "Logout", value = AppURL.LOGOUT_URL)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        forwardToJSP(req, resp, HOME_JSP);
    }
}
