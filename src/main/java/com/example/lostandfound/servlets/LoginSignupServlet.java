package com.example.lostandfound.servlets;

import com.example.lostandfound.connection.DBconnection;
import org.mindrot.jbcrypt.BCrypt;

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
        String confirmPassword = request.getParameter("confirm_password");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        String role = "user";
        String avatarImage = "default.png";

        if (isValidName(firstName) || isValidName(lastName)) {
            request.setAttribute("errorMessage", "First and last names should only contain letters.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (!isValidUsername(username)) {
            request.setAttribute("errorMessage", "Username must be alphanumeric (5-20 characters).");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Please enter a valid email address.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain uppercase, lowercase, digit, and special character.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Password and confirm password do not match.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (!isValidContact(contact)) {
            request.setAttribute("errorMessage", "Please enter a valid phone number.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        if (userExists(username, email)) {
            request.setAttribute("errorMessage", "Username or email already exists.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
            return;
        }

        // 🔐 Debug log: raw password
        System.out.println("Debug (Signup): Raw password = " + password);

        // ✅ Hash password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // 🔐 Debug log: hashed password
        System.out.println("Debug (Signup): Hashed password = " + hashedPassword);

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "INSERT INTO users (first_name, last_name, username, email, password, contact, address, role, created_at, updated_at, avatar_image) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, username);
                stmt.setString(4, email);
                stmt.setString(5, hashedPassword);
                stmt.setString(6, contact);
                stmt.setString(7, address);
                stmt.setString(8, role);
                stmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
                stmt.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
                stmt.setString(11, avatarImage);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    request.setAttribute("successMessage", "Account created successfully! Please log in.");
                    request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Signup failed. Try again.");
                    request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Signup failed due to server error.");
            request.getRequestDispatcher("/user/signup.jsp").forward(request, response);
        }
    }

    private boolean authenticateUser(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("password");

                    // 🔐 Debug log: login attempt
                    System.out.println("Debug (Login): Entered password = " + password);
                    System.out.println("Debug (Login): Stored hashed password = " + hashedPassword);

                    boolean match = BCrypt.checkpw(password, hashedPassword);

                    // 🔐 Debug result
                    System.out.println("Debug (Login): Password match = " + match);

                    return match;
                }
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

    // validators
    private boolean isValidName(String name) {
        return name == null || !name.matches("[A-Za-z]{1,50}");
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("[A-Za-z0-9]{5,20}");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isValidPassword(String password){
        return password != null && password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[^A-Za-z0-9].*");
    }

    private boolean isValidContact(String contact) {
        return contact != null && contact.matches("\\d{10,15}");
    }
}
