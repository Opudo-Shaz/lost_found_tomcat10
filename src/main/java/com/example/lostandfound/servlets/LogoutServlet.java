package com.example.lostandfound.servlets;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to log out
        HttpSession session = request.getSession();
        session.invalidate();

        // Redirect to login page
        response.sendRedirect("login.jsp");
    }
}
