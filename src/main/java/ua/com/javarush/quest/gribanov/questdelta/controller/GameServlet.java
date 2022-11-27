package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.dto.GameDTO;
import ua.com.javarush.quest.gribanov.questdelta.dto.QuestionDTO;
import ua.com.javarush.quest.gribanov.questdelta.dto.UserDTO;
import ua.com.javarush.quest.gribanov.questdelta.service.GameService;
import ua.com.javarush.quest.gribanov.questdelta.service.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.*;
import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(value = AppURL.GAME_URL)
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long questID = Long.parseLong(req.getParameter("questId"));
        UserService userService = UserService.get();
        Optional<UserDTO> user = userService.getUser(req.getSession());
        GameService gameService = GameService.get();
        if (user.isPresent()) {
            GameDTO game = gameService.getGame(user.get().getId(), questID).orElse(null);
            req.getSession().setAttribute("user", user.get());
            req.setAttribute("game", game);

            if (Objects.nonNull(game)) {
                QuestionDTO questionDTO = game.getCurrentQuestion();
                req.setAttribute("question", questionDTO);
            }
            forwardToJSP(req, resp, GAME_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long gameID = Long.parseLong(req.getParameter("gameId"));
        String answer = req.getParameter("answer");
        if (nonNull(answer)) {
            Long answerID = Long.parseLong(answer);
            GameService gameService = GameService.get();
            Optional<GameDTO> game = gameService.updateGame(gameID, answerID);
            if (game.isPresent()) {
                req.setAttribute("game", game.get());
                req.setAttribute("question", game.get().getCurrentQuestion());
                req.setAttribute("state", game.get().getState());

                forwardToJSP(req, resp, GAME_JSP);
            }
        } else {
            resp.sendError(406, "Any answer hasn't selected");
        }
    }
}
