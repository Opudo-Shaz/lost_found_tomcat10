package com.example.lostandfound.daos;

import com.example.lostandfound.connection.DBconnection;
import com.example.lostandfound.model.FoundItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.lostandfound.connection.DBconnection.getConnection;

public class FoundItemsDAO {

    private static final Logger logger = Logger.getLogger(FoundItemsDAO.class.getName());

    private FoundItem extractFoundItem(ResultSet rs) throws SQLException {
        FoundItem item = new FoundItem();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setCategory(rs.getString("category"));
        item.setLocationFound(rs.getString("location_found"));
        item.setFinderContact(rs.getString("finder_contact"));
        item.setFoundDate(rs.getString("found_date"));
        item.setCurrentLocation(rs.getString("current_location"));
        item.setItemHolderName(rs.getString("item_holder_name"));
        item.setStatus(FoundItem.Status.valueOf(rs.getString("status")));
        item.setDisputeReason(rs.getString("dispute_reason"));
        return item;
    }


    public List<FoundItem> searchFoundItems(String query) {
        List<FoundItem> results = new ArrayList<>();
        String sql = "SELECT * FROM found_items WHERE name LIKE ? OR description LIKE ? OR category LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String wildcardQuery = "%" + query + "%";
            stmt.setString(1, wildcardQuery);
            stmt.setString(2, wildcardQuery);
            stmt.setString(3, wildcardQuery);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FoundItem item = extractFoundItem(rs); // your method for mapping rows
                results.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


    // Get all found items
    public List<FoundItem> getAllFoundItems() {
        List<FoundItem> foundItems = new ArrayList<>();
        String sql = "SELECT * FROM found_items";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FoundItem item = mapResultSetToFoundItem(rs);
                foundItems.add(item);
            }

            logger.info("Successfully retrieved all found items.");

        } catch (SQLException e) {
            logger.severe("Error retrieving found items: " + e.getMessage());
            e.printStackTrace();
        }

        return foundItems;
    }

    // Get a found item by ID
    public FoundItem getFoundItemById(Long id) {
        FoundItem item = null;
        String sql = "SELECT * FROM found_items WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = mapResultSetToFoundItem(rs);
                }
            }

            if (item != null) {
                logger.info("Successfully retrieved found item with ID: " + id);
            } else {
                logger.warning("No found item found with ID: " + id);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving found item by ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        return item;
    }

    // Add a found item
    public void addFoundItem(FoundItem item) {
        String sql = "INSERT INTO found_items (name, description, category, current_location, found_date, " +
                "item_holder_name, location_found, finder_contact, claimed_by, finder_email, claimer_contact, " +
                "claimant_email, claimer_note, finder_images, claimer_images, status, dispute_reason) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getCategory());
            stmt.setString(4, item.getCurrentLocation());
            stmt.setString(5, item.getFoundDate());
            stmt.setString(6, item.getItemHolderName());
            stmt.setString(7, item.getLocationFound());
            stmt.setString(8, item.getFinderContact());
            stmt.setString(9, item.getClaimedBy());
            stmt.setString(10, item.getFinderEmail());
            stmt.setString(11, item.getClaimerContact());
            stmt.setString(12, item.getClaimantEmail());
            stmt.setString(13, item.getClaimerNote());
            stmt.setString(14, item.getFinderImages());
            stmt.setString(15, item.getClaimerImages());
            stmt.setString(16, item.getStatus().name());
            stmt.setString(17, item.getDisputeReason());

            stmt.executeUpdate();

            logger.info("Successfully added found item: " + item.getName());

        } catch (SQLException e) {
            logger.severe("Error adding found item: " + item.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a found item by ID
    public void deleteFoundItem(Long id) {
        String sql = "DELETE FROM found_items WHERE id = ?";
        boolean rowDeleted = false;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            rowDeleted = stmt.executeUpdate() > 0;

            if (rowDeleted) {
                logger.info("Successfully deleted found item with ID: " + id);
            } else {
                logger.warning("No found item found with ID: " + id + " to delete.");
            }

        } catch (SQLException e) {
            logger.severe("Error deleting found item with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    // Update an existing found item
    public void updateFoundItem(FoundItem item) {
        String sql = "UPDATE found_items SET name=?, description=?, category=?, current_location=?, found_date=?, " +
                "item_holder_name=?, location_found=?, finder_contact=?, claimed_by=?, finder_email=?, claimer_contact=?, " +
                "claimant_email=?, claimer_note=?, finder_images=?, claimer_images=?, status=?, dispute_reason=? WHERE id=?";

        boolean rowUpdated = false;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getCategory());
            stmt.setString(4, item.getCurrentLocation());
            stmt.setString(5, item.getFoundDate());
            stmt.setString(6, item.getItemHolderName());
            stmt.setString(7, item.getLocationFound());
            stmt.setString(8, item.getFinderContact());
            stmt.setString(9, item.getClaimedBy());
            stmt.setString(10, item.getFinderEmail());
            stmt.setString(11, item.getClaimerContact());
            stmt.setString(12, item.getClaimantEmail());
            stmt.setString(13, item.getClaimerNote());
            stmt.setString(14, item.getFinderImages());
            stmt.setString(15, item.getClaimerImages());
            stmt.setString(16, item.getStatus().name());
            stmt.setString(17, item.getDisputeReason());
            stmt.setLong(18, item.getId());

            rowUpdated = stmt.executeUpdate() > 0;

            if (rowUpdated) {
                logger.info("Successfully updated found item with ID: " + item.getId());
            } else {
                logger.warning("No found item found with ID: " + item.getId() + " to update.");
            }

        } catch (SQLException e) {
            logger.severe("Error updating found item with ID " + item.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }
    public FoundItem viewFoundItem(Long id) {
        FoundItem item = null;
        String sql = "SELECT * FROM found_items WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = extractFoundItem(rs);
                }
            }

            if (item != null) {
                logger.info("Successfully retrieved found item with ID: " + id);
            } else {
                logger.warning("No found item found with ID: " + id);
            }

        } catch (SQLException e) {
            logger.severe("Error retrieving found item by ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        return item;
    }


    // Utility method to map ResultSet to FoundItem object
    private FoundItem mapResultSetToFoundItem(ResultSet rs) throws SQLException {
        FoundItem item = new FoundItem();

        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setCategory(rs.getString("category"));
        item.setCurrentLocation(rs.getString("current_location"));
        item.setFoundDate(rs.getString("found_date"));
        item.setItemHolderName(rs.getString("item_holder_name"));
        item.setLocationFound(rs.getString("location_found"));
        item.setFinderContact(rs.getString("finder_contact"));
        item.setClaimedBy(rs.getString("claimed_by"));
        item.setFinderEmail(rs.getString("finder_email"));
        item.setClaimerContact(rs.getString("claimer_contact"));
        item.setClaimantEmail(rs.getString("claimant_email"));
        item.setClaimerNote(rs.getString("claimer_note"));
        item.setFinderImages(rs.getString("finder_images"));
        item.setClaimerImages(rs.getString("claimer_images"));
        item.setStatus(FoundItem.Status.valueOf(rs.getString("status")));
        item.setDisputeReason(rs.getString("dispute_reason"));

        return item;
    }

    public void updateStatus(Long id, String status) {
        String sql = "UPDATE found_items SET status = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setLong(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating item status", e);
        }
    }

    public void updateStatusToDisputed(Long id, String reason) {
        String sql = "UPDATE found_items SET status = 'DISPUTED', dispute_reason = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reason);
            stmt.setLong(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating item to DISPUTED", e);
        }
    }


    public List<FoundItem> getPendingItems() {
        List<FoundItem> pendingItems = new ArrayList<>();

        String query = "SELECT id, name, claimant_email FROM found_items WHERE status = 'PENDING'";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FoundItem item = new FoundItem();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setClaimantEmail(rs.getString("claimant_email"));
                pendingItems.add(item);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching pending items: " + e.getMessage());
            e.printStackTrace();
        }

        return pendingItems;
    }
}
