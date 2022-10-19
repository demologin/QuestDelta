package ua.com.javarush.quest.gribanov.questdelta.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class GameDispatcher {
    private static final String PREFIX = "WEB-INF/";
    public static final String GAME_JSP = "game.jsp";
    public static final String HOME_JSP = "index.jsp";
    public static final String LOGIN_JSP = "login.jsp";
    public static final String QUESTS_JSP = "quests.jsp";
    public static void forwardToJSP(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        String jspPath = PREFIX + path;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPath);
        requestDispatcher.forward(req, resp);
    }

}
