package com.example.lostandfound.servlets;

import com.example.lostandfound.connection.DBconnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/confirmFound")
public class MarkFoundServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int lostItemId = Integer.parseInt(request.getParameter("item_id"));
        String role = request.getParameter("role"); // "owner" or "finder"

        try (Connection conn = DBconnection.getConnection()) {
            // Update confirmation flags
            String updateSql = "UPDATE lost_items SET " +
                    (role.equals("owner") ? "owner_confirmed = TRUE" : "finder_confirmed = TRUE") +
                    " WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, lostItemId);
            updateStmt.executeUpdate();

            // Check if both have confirmed
            String checkSql = "SELECT * FROM lost_items WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, lostItemId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getBoolean("owner_confirmed") && rs.getBoolean("finder_confirmed")) {
                // Insert into found_items
                String insertSql = "INSERT INTO found_items (name, description, location, date_found, image_path) VALUES (?, ?, ?, NOW(), ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, rs.getString("name"));
                insertStmt.setString(2, rs.getString("description"));
                insertStmt.setString(3, rs.getString("location"));
                insertStmt.setString(4, rs.getString("image_path"));
                insertStmt.executeUpdate();

                // Delete from lost_items
                String deleteSql = "DELETE FROM lost_items WHERE id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, lostItemId);
                deleteStmt.executeUpdate();
            }

            response.sendRedirect("lostItems.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("lostItems.jsp?status=error");
        }
    }
}
