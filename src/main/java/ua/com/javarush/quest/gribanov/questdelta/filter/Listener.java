package ua.com.javarush.quest.gribanov.questdelta.filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.javarush.quest.gribanov.questdelta.service.LoaderService;

@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LoaderService loaderService = LoaderService.get();
        loaderService.load();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LoaderService loaderService = LoaderService.get();
        loaderService.save();
    }
}
