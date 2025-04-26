package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.UserDAO;
import com.example.lostandfound.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;
    private Gson gson;

    @Override
    public void init() {
        userDAO = new UserDAO();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Handle GET: return all users or a specific user by ID (if ?id= provided)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        PrintWriter out = resp.getWriter();

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Optional<User> user = userDAO.getUserById(id);
                if (user.isPresent()) {
                    out.print(gson.toJson(user.get()));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"User not found\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Invalid ID format\"}");
            }
        } else {
            List<User> users = userDAO.getAllUsers();
            out.print(gson.toJson(users));
        }
    }

    // Handle POST: add a new user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        User user = gson.fromJson(reader, User.class);

        userDAO.addUser(user);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        PrintWriter out = resp.getWriter();
        out.print("{\"message\":\"User created successfully\"}");
    }

    // Handle PUT: update an existing user
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        User user = gson.fromJson(reader, User.class);

        if (user.getId() == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"error\":\"User ID is required for update\"}");
            return;
        }

        userDAO.updateUser(user);
        resp.setContentType("application/json");
        resp.getWriter().print("{\"message\":\"User updated successfully\"}");
    }

    // Handle DELETE: delete a user by ID (sent as query param)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                userDAO.deleteUser(id);
                resp.setContentType("application/json");
                resp.getWriter().print("{\"message\":\"User deleted successfully\"}");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().print("{\"error\":\"Invalid ID format\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("{\"error\":\"Missing user ID\"}");
        }
    }
}
