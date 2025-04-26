package com.example.lostandfound.listeners;

import com.example.lostandfound.scheduler.EmailNotificationTask;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Timer;

@WebListener
public class AppStartupListener implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer();
        long delay = 0;
        long period = 2 * 24 * 60 * 60 * 1000L; // every 2 days

        timer.schedule(new EmailNotificationTask(), delay, period);
        System.out.println("Email notification scheduler started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
            System.out.println("Email notification scheduler stopped.");
        }
    }
}
