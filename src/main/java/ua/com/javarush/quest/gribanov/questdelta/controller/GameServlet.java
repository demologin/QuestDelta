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
import ua.com.javarush.quest.gribanov.questdelta.entity.User;
import ua.com.javarush.quest.gribanov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.gribanov.questdelta.repository.UserRepository;
import ua.com.javarush.quest.gribanov.questdelta.service.GameService;
import ua.com.javarush.quest.gribanov.questdelta.service.UserService;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(value = AppURL.GAME_URL)
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questID = Long.parseLong(req.getParameter("questId"));
        UserService userService = UserService.get();
        Optional<UserDTO> user=userService.getUser(req.getSession());
        GameService gameService = GameService.get();
        if (user.isPresent()) {
            GameDTO game = gameService.getGame(user.get().getId(), questID).get();
            req.setAttribute("game", game);
            req.setAttribute("question", game.getCurrentQuestion());
            forwardToJSP(req, resp, GAME_JSP);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long gameID = Long.parseLong(req.getParameter("gameID"));
        Long answerID = Long.parseLong(req.getParameter("answer"));
        GameService gameService = GameService.get();

        Optional<GameDTO> game = gameService.updateGame(gameID, answerID);
        if (game.isPresent()){
            req.setAttribute("game", game.get());
            req.setAttribute("question", game.get().getCurrentQuestion());
            forwardToJSP(req, resp, GAME_JSP);
        }
    }

    private void forwardToQuestion(HttpServletRequest req, HttpServletResponse resp, QuestionDTO questionDTO){

    }
}
