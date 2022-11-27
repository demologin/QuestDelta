package ua.com.javarush.quest.gribanov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.gribanov.questdelta.constant.AppURL;
import ua.com.javarush.quest.gribanov.questdelta.dto.StatisticDTO;
import ua.com.javarush.quest.gribanov.questdelta.service.StatisticService;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.gribanov.questdelta.util.GameDispatcher.*;

@WebServlet(name = "Statistic", value = AppURL.STAT_URL)
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticService statisticService = StatisticService.get();
        Optional<StatisticDTO> statistic = statisticService.getStatistic();
        if (statistic.isPresent()) {
            req.setAttribute("statistic", statistic.get());
            forwardToJSP(req, resp, STATISTIC_JSP);
        }

    }
}
