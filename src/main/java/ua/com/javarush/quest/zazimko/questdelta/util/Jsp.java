package ua.com.javarush.quest.zazimko.questdelta.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class Jsp {
    public void forward(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
        String path = "WEB-INF/%s.jsp".formatted(jspName);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws ServletException, IOException {
        req.getSession().setAttribute("error", errorMessage);
        forward(req, resp, uri);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }
}
