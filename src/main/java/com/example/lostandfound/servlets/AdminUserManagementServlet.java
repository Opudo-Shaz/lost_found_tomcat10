package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.UserDAO;
import com.example.lostandfound.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUserManagementServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);

            // Flash message
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("flash") != null) {
                request.setAttribute("flash", session.getAttribute("flash"));
                session.removeAttribute("flash");
            }

            request.getRequestDispatcher("/admin/user-management.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Error loading user management page.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try {
            if ("add".equals(action)) {
                User user = new User();
                user.setFirstName(request.getParameter("first_name"));
                user.setLastName(request.getParameter("last_name"));
                user.setUsername(request.getParameter("username"));
                user.setEmail(request.getParameter("email"));
                user.setContact(request.getParameter("contact"));
                user.setAddress(request.getParameter("address"));
                user.setRole(request.getParameter("role"));
                user.setPassword(request.getParameter("password"));
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
                user.setAvatarImage(request.getParameter("avatar_image"));

                userDAO.addUser(user);
                session.setAttribute("flash", "User added successfully!");

            } else if ("update".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));

                User user = new User();
                user.setId(id);
                user.setFirstName(request.getParameter("first_name"));
                user.setLastName(request.getParameter("last_name"));
                user.setUsername(request.getParameter("username"));
                user.setEmail(request.getParameter("email"));
                user.setContact(request.getParameter("contact"));
                user.setAddress(request.getParameter("address"));
                user.setRole(request.getParameter("role"));

                // Optional password update
                String passwordParam = request.getParameter("password");
                if (passwordParam != null && !passwordParam.isEmpty()) {
                    user.setPassword(passwordParam);
                } else {
                    user.setPassword(null);
                }

                // Handle created_at safely
                String createdAtParam = request.getParameter("created_at");
                if (createdAtParam != null && !createdAtParam.isEmpty()) {
                    user.setCreatedAt(LocalDateTime.parse(createdAtParam));
                } else {
                    user.setCreatedAt(LocalDateTime.now());
                }

                user.setUpdatedAt(LocalDateTime.now());
                user.setAvatarImage(request.getParameter("avatar_image"));

                userDAO.updateUser(user);
                session.setAttribute("flash", "User updated successfully!");

            } else if ("delete".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                userDAO.deleteUser(id);
                session.setAttribute("flash", "User deleted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("flash", "An error occurred while processing the user.");
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
