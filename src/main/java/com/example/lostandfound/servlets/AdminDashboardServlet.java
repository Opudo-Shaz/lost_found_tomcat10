package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.AdminDAO;
import com.example.lostandfound.connection.DBconnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBconnection.getConnection()) {
            AdminDAO adminDAO = new AdminDAO(conn);

            long totalMembers = adminDAO.getTotalMembers();
            long totalLostItems = adminDAO.getTotalLostItems();
            long totalFoundItems = adminDAO.getTotalFoundItems();

            request.setAttribute("totalMembers", totalMembers);
            request.setAttribute("totalLostItems", totalLostItems);
            request.setAttribute("totalFoundItems", totalFoundItems);

            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Something went wrong while loading dashboard.");
        }
    }
}
