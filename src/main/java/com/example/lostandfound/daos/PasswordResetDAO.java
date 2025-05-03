package com.example.lostandfound.daos;

import com.example.lostandfound.EmailUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordResetDAO {

    // Replace with your actual database connection info
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lost_and_found", "username", "password");
    }

    public void sendPasswordResetLink(String email) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusHours(1);

        try (Connection conn = getConnection()) {
            // Optional: Check if user exists
            PreparedStatement checkUserStmt = conn.prepareStatement("SELECT id FROM users WHERE email = ?");
            checkUserStmt.setString(1, email);
            ResultSet rs = checkUserStmt.executeQuery();
            if (!rs.next()) return; // user doesn't exist

            long userId = rs.getLong("id");

            // Store reset token
            PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO password_resets (user_id, token, expires_at) VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE token = ?, expires_at = ?"
            );
            insertStmt.setLong(1, userId);
            insertStmt.setString(2, token);
            insertStmt.setTimestamp(3, Timestamp.valueOf(expiry));
            insertStmt.setString(4, token);
            insertStmt.setTimestamp(5, Timestamp.valueOf(expiry));
            insertStmt.executeUpdate();

            // Send reset email
            String resetLink = "http://localhost:8080/password?action=reset&token=" + token;
            String subject = "Password Reset Request";
            String body = String.format(
                    "<html>\n" +
                            "<body>\n" +
                            "    <p>Hello,</p>\n" +
                            "    <p>You requested a password reset. Click the link below to reset your password:</p>\n" +
                            "    <p><a href=\"%s\">Reset Password</a></p>\n" +
                            "    <p>This link will expire in 1 hour.</p>\n" +
                            "    <p>If you did not request this, please ignore this email.</p>\n" +
                            "</body>\n" +
                            "</html>",
                    resetLink
            );


            EmailUtil.sendEmail(email, subject, body);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateToken(String token) {
        String sql = "SELECT * FROM password_resets WHERE token = ? AND expires_at > NOW()";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void resetPassword(String token, String newPassword) {
        String findUserSql = "SELECT user_id FROM password_resets WHERE token = ?";
        String updatePasswordSql = "UPDATE users SET password = ? WHERE id = ?";
        String deleteTokenSql = "DELETE FROM password_resets WHERE token = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            Long userId = null;
            try (PreparedStatement stmt = conn.prepareStatement(findUserSql)) {
                stmt.setString(1, token);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    userId = rs.getLong("user_id");
                } else {
                    return; // token not valid
                }
            }

            // In a real app, hash the password before storing
            try (PreparedStatement stmt = conn.prepareStatement(updatePasswordSql)) {
                stmt.setString(1, newPassword);
                stmt.setLong(2, userId);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(deleteTokenSql)) {
                stmt.setString(1, token);
                stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
