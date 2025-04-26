package com.example.lostandfound.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/", "/terms", "/privacy"})
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/terms":
                request.getRequestDispatcher("/terms.jsp").forward(request, response);
                break;
            case "/privacy":
                request.getRequestDispatcher("/privacy.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }
}
