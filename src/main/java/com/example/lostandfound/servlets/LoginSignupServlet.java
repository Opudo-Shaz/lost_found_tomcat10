package com.example.lostandfound.servlets;

import com.example.lostandfound.connection.DBconnection;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = {"/login", "/signup", "/auth"})
public class LoginSignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/login".equals(path)) {
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        } else if ("/signup".equals(path)) {
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("signup".equalsIgnoreCase(action)) {
            handleSignup(request, response);
        } else {
            handleLogin(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", getUserRole(username));

            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }

    private void handleSignup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String role = "user";
        String avatarImage = "default.png";

        if (userExists(username, email)) {
            request.setAttribute("errorMessage", "Username or email already exists.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "INSERT INTO users (first_name, last_name, username, email, password, contact, address, role, created_at, updated_at, avatar_image) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, username);
                stmt.setString(4, email);
                stmt.setString(5, password);
                stmt.setString(6, contact);
                stmt.setString(7, address);
                stmt.setString(8, role);
                stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                stmt.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                stmt.setString(11, avatarImage);

                stmt.executeUpdate();
            }

            request.setAttribute("successMessage", "Account created successfully! Please log in.");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Signup failed. Try again later.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
        }
    }

    private boolean authenticateUser(String username, String password) {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password); // Replace with hash compare in production
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUserRole(String username) {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean userExists(String username, String email) {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT 1 FROM users WHERE username = ? OR email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
