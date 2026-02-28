package com.example.lostandfound.daos;

import com.example.lostandfound.connection.DBconnection;
import com.example.lostandfound.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

            logger.info("Successfully retrieved all users.");
        } catch (SQLException e) {
            logger.severe("Error retrieving users: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    public Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }

            if (user != null) {
                logger.info("Successfully retrieved user with ID: " + id);
            } else {
                logger.warning("No user found with ID: " + id);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving user by ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }

            if (user != null) {
                logger.info("Successfully retrieved user with username: " + username);
            } else {
                logger.warning("No user found with username: " + username);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving user by username " + username + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }

            if (user != null) {
                logger.info("Successfully retrieved user with email: " + email);
            } else {
                logger.warning("No user found with email: " + email);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving user by email " + email + ": " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, contact, address, role, created_at, updated_at, avatar_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashedPassword = hashPassword(user.getPassword());

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, hashedPassword);
            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getRole());

            LocalDateTime now = LocalDateTime.now();
            stmt.setTimestamp(9, Timestamp.valueOf(user.getCreatedAt() != null ? user.getCreatedAt() : now));
            stmt.setTimestamp(10, Timestamp.valueOf(user.getUpdatedAt() != null ? user.getUpdatedAt() : now));

            stmt.setString(11, user.getAvatarImage());

            stmt.executeUpdate();
            logger.info("Successfully added user: " + user.getUsername());

        } catch (SQLException e) {
            logger.severe("Error adding user: " + user.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, password = ?, contact = ?, address = ?, role = ?, created_at = ?, updated_at = ?, avatar_image = ? WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());

            // Password logic
            String passwordToUse = user.getPassword();
            if (passwordToUse != null && !passwordToUse.startsWith("$2a$")) {
                passwordToUse = hashPassword(passwordToUse); // Only hash if it's plain text
            }
            stmt.setString(5, passwordToUse);

            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getRole());

            LocalDateTime now = LocalDateTime.now();
            stmt.setTimestamp(9, user.getCreatedAt() != null ? Timestamp.valueOf(user.getCreatedAt()) : Timestamp.valueOf(now));
            stmt.setTimestamp(10, user.getUpdatedAt() != null ? Timestamp.valueOf(user.getUpdatedAt()) : Timestamp.valueOf(now));

            stmt.setString(11, user.getAvatarImage());
            stmt.setLong(12, user.getId());

            stmt.executeUpdate();
            logger.info("Successfully updated user with ID: " + user.getId());

        } catch (SQLException e) {
            logger.severe("Error updating user with ID " + user.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            logger.info("Successfully deleted user with ID: " + id);

        } catch (SQLException e) {
            logger.severe("Error deleting user with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setContact(rs.getString("contact"));
        user.setAddress(rs.getString("address"));
        user.setRole(rs.getString("role"));
        user.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
        user.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
        user.setAvatarImage(rs.getString("avatar_image"));
        return user;
    }

    private String hashPassword(String plainPassword) {
        // If you do not want to use BCrypt, implement your own or use SHA-256 (not recommended for passwords)
        return org.mindrot.jbcrypt.BCrypt.hashpw(plainPassword, org.mindrot.jbcrypt.BCrypt.gensalt());
    }
}
