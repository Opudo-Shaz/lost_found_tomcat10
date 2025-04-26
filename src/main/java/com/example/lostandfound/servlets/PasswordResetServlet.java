package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.PasswordResetDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/password")
public class PasswordResetServlet extends HttpServlet {

    private final PasswordResetDAO passwordResetDAO = new PasswordResetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        if ("forgot".equals(action)) {
            req.getRequestDispatcher("/user/forgot-password.jsp").forward(req, resp);
        } else if ("reset".equals(action)) {
            String token = req.getParameter("token");

            if (!passwordResetDAO.validateToken(token)) {
                req.setAttribute("error", "Invalid or expired token.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("token", token);
            req.getRequestDispatcher("/user/reset-password.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        if ("forgot".equals(action)) {
            String email = req.getParameter("email");
            passwordResetDAO.sendPasswordResetLink(email);
            req.setAttribute("message", "If an account with that email exists, we have sent a reset link to your inbox.");
            req.getRequestDispatcher("/user/forgot-password.jsp").forward(req, resp);

        } else if ("reset".equals(action)) {
            String token = req.getParameter("token");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Passwords do not match.");
                req.setAttribute("token", token);
                req.getRequestDispatcher("/user/reset-password.jsp").forward(req, resp);
                return;
            }

            passwordResetDAO.resetPassword(token, password);
            req.setAttribute("message", "Password reset successfully. You can now login.");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
        }
    }
}
