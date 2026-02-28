package com.example.lostandfound.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    private final Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    public long getTotalMembers() throws Exception {
        String query = "SELECT COUNT(*) FROM users";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0;
    }

    public long getTotalLostItems() throws Exception {
        String query = "SELECT COUNT(*) FROM lost_items";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0;
    }

    public long getTotalFoundItems() throws Exception {
        String query = "SELECT COUNT(*) FROM found_items";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0;
    }
}
