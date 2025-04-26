package com.example.lostandfound.daos;

import com.example.lostandfound.connection.DBconnection;
import com.example.lostandfound.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = mapResultSetToUser(rs);
                users.add(user);
            }

            logger.info("Successfully retrieved all users.");

        } catch (SQLException e) {
            logger.severe("Error retrieving users: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    // Get user by ID
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

    // Get user by username
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

    // Get user by email
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

    // Add a new user
    public void addUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, contact, address, role, created_at, updated_at, avatar_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getRole());
            stmt.setTimestamp(9, Timestamp.valueOf(user.getCreatedAt()));
            stmt.setTimestamp(10, Timestamp.valueOf(user.getUpdatedAt()));
            stmt.setString(11, user.getAvatarImage());

            stmt.executeUpdate();

            logger.info("Successfully added user: " + user.getUsername());

        } catch (SQLException e) {
            logger.severe("Error adding user: " + user.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing user
    public void updateUser(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, email = ?, password = ?, contact = ?, address = ?, role = ?, created_at = ?, updated_at = ?, avatar_image = ? WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getRole());
            stmt.setTimestamp(9, Timestamp.valueOf(user.getCreatedAt()));
            stmt.setTimestamp(10, Timestamp.valueOf(user.getUpdatedAt()));
            stmt.setString(11, user.getAvatarImage());
            stmt.setLong(12, user.getId());

            stmt.executeUpdate();

            logger.info("Successfully updated user with ID: " + user.getId());

        } catch (SQLException e) {
            logger.severe("Error updating user with ID " + user.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete user by ID
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

    // Utility method to map ResultSet to User object
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
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        user.setAvatarImage(rs.getString("avatar_image"));
        return user;
    }
}
