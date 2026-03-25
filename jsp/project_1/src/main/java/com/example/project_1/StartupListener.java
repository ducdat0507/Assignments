package com.example.project_1;

import com.example.project_1.dao.UserDAO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (UserDAO.findByUsername("admin") == null) {
            UserDAO.createUser("admin", "1234567");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { }
}
