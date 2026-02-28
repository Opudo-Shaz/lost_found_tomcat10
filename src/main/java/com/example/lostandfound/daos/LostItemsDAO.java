package com.example.lostandfound.daos;

import com.example.lostandfound.connection.DBconnection;
import com.example.lostandfound.model.LostItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LostItemsDAO {

    private static final Logger logger = Logger.getLogger(LostItemsDAO.class.getName());

    private LostItem extractLostItem(ResultSet rs) throws SQLException {
        LostItem item = new LostItem();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));                     // From Item superclass
        item.setDescription(rs.getString("description"));       // From Item superclass
        item.setCategory(rs.getString("category"));             // From Item superclass
        item.setCurrentLocation(rs.getString("current_location")); // From Item superclass

        item.setOwnerContact(rs.getString("owner_contact"));
        item.setDateLost(rs.getString("date_lost"));
        item.setLocationLost(rs.getString("location_lost"));
        item.setOwnerEmail(rs.getString("owner_email"));
        item.setOwnerConfirmed(rs.getBoolean("owner_confirmed"));
        item.setFinderConfirmed(rs.getBoolean("finder_confirmed"));


        return item;
    }

    public List<LostItem> searchLostItems(String query) {
        List<LostItem> results = new ArrayList<>();
        String sql = "SELECT * FROM lost_items WHERE name LIKE ? OR description LIKE ? OR category LIKE ?";

        try ( Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String wildcard = "%" + query + "%";
            stmt.setString(1, wildcard);
            stmt.setString(2, wildcard);
            stmt.setString(3, wildcard);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LostItem item = extractLostItem(rs);
                results.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }



    // Get all lost items
    public List<LostItem> getAllLostItems() {
        List<LostItem> lostItems = new ArrayList<>();
        String sql = "SELECT * FROM lost_items";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LostItem item = mapResultSetToLostItem(rs);
                lostItems.add(item);
            }

            logger.info("Successfully retrieved all lost items.");

        } catch (SQLException e) {
            logger.severe("Error retrieving lost items: " + e.getMessage());
            e.printStackTrace();
        }

        return lostItems;
    }

    // Get a single lost item by ID
    public LostItem getLostItemById(Long id) {
        String sql = "SELECT * FROM lost_items WHERE id = ?";
        LostItem item = null;

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = mapResultSetToLostItem(rs);
                }
            }

            if (item != null) {
                logger.info("Successfully retrieved lost item with ID: " + id);
            } else {
                logger.warning("No lost item found with ID: " + id);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving lost item by ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        return item;
    }

    // Add a lost item
    public void addLostItem(LostItem item) {
        String sql = "INSERT INTO lost_items (name, description, category, current_location, owner_contact, date_lost, location_lost, owner_email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getCategory());
            stmt.setString(4, item.getCurrentLocation());
            stmt.setString(5, item.getOwnerContact());
            stmt.setString(6, item.getDateLost());
            stmt.setString(7, item.getLocationLost());
            stmt.setString(8, item.getOwnerEmail());

            stmt.executeUpdate();

            logger.info("Successfully added lost item: " + item.getName());

        } catch (SQLException e) {
            logger.severe("Error adding lost item: " + item.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update a lost item
    public void updateLostItem(LostItem item) {
        String sql = "UPDATE lost_items SET name = ?, description = ?, category = ?, current_location = ?, " +
                "owner_contact = ?, date_lost = ?, location_lost = ?, owner_email = ? WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getCategory());
            stmt.setString(4, item.getCurrentLocation());
            stmt.setString(5, item.getOwnerContact());
            stmt.setString(6, item.getDateLost());
            stmt.setString(7, item.getLocationLost());
            stmt.setString(8, item.getOwnerEmail());
            stmt.setLong(9, item.getId());

            stmt.executeUpdate();

            logger.info("Successfully updated lost item with ID: " + item.getId());

        } catch (SQLException e) {
            logger.severe("Error updating lost item with ID " + item.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a lost item by ID
    public void deleteLostItem(Long id) {
        String sql = "DELETE FROM lost_items WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

            logger.info("Successfully deleted lost item with ID: " + id);

        } catch (SQLException e) {
            logger.severe("Error deleting lost item with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Utility method to map ResultSet to LostItem object
    private LostItem mapResultSetToLostItem(ResultSet rs) throws SQLException {
        LostItem item = new LostItem();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setCategory(rs.getString("category"));
        item.setCurrentLocation(rs.getString("current_location"));
        item.setOwnerContact(rs.getString("owner_contact"));
        item.setDateLost(rs.getString("date_lost"));
        item.setLocationLost(rs.getString("location_lost"));
        item.setOwnerEmail(rs.getString("owner_email"));
        item.setOwnerConfirmed(rs.getBoolean("owner_confirmed"));
        item.setFinderConfirmed(rs.getBoolean("finder_confirmed"));

        return item;
    }

    public List<LostItem> getFilteredLostItems(String category, String fromDate, String toDate, String locationLost) {
        List<LostItem> filteredItems = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM lost_items WHERE 1=1");
        List<String> parameters = new ArrayList<>();

        if (category != null && !category.trim().isEmpty()) {
            sql.append(" AND category = ?");
            parameters.add(category.trim());
        }

        if (locationLost != null && !locationLost.trim().isEmpty()) {
            sql.append(" AND location_lost LIKE ?");
            parameters.add("%" + locationLost.trim() + "%");
        }

        if (fromDate != null && !fromDate.trim().isEmpty()) {
            sql.append(" AND date_lost >= ?");
            parameters.add(fromDate.trim());
        }

        if (toDate != null && !toDate.trim().isEmpty()) {
            sql.append(" AND date_lost <= ?");
            parameters.add(toDate.trim());
        }

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setString(i + 1, parameters.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                filteredItems.add(mapResultSetToLostItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredItems;
    }

    public void confirmByOwner(Long id) {
        String sql = "UPDATE lost_items SET owner_confirmed = TRUE WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void confirmByFinder(Long id) {
        String sql = "UPDATE lost_items SET finder_confirmed = TRUE WHERE id = ?";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
