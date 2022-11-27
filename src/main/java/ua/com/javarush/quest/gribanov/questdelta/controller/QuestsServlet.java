package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.dto.QuestDTO;
import ua.com.javarush.quest.gribanov.questdelta.service.QuestService;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(name = "quests", value = AppURL.QUESTS_URL)
public class QuestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        QuestService questService = QuestService.get();
        Collection<QuestDTO> quests = questService.getAll();
        req.setAttribute("quests", quests);
        Optional<Map<Long, String>> authors = questService.getAuthors(quests);
        authors.ifPresent(longStringMap -> req.setAttribute("authors", longStringMap));
        forwardToJSP(req, resp, QUESTS_JSP);
    }
}
