package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.LostItemsDAO;
import com.example.lostandfound.model.LostItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
@WebServlet("/lost-items")
public class LostItemsServlet extends HttpServlet {

    private LostItemsDAO lostItemsDAO;

    @Override
    public void init() throws ServletException {
        lostItemsDAO = new LostItemsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");

        // Handle different actions
        if ("edit".equals(action)) {
            // Edit action: Fetch lost item by ID
            Long id = Long.parseLong(request.getParameter("id"));
            LostItem item = lostItemsDAO.getLostItemById(id);
            request.setAttribute("item", item);
            request.getRequestDispatcher("/editLostItem.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            // Delete action: Delete lost item by ID
            Long id = Long.parseLong(request.getParameter("id"));
            lostItemsDAO.deleteLostItem(id);
            response.sendRedirect(request.getContextPath() + "/lost-items?action=view");

        } else if ("view".equals(action)) {
            // View action: Show details of a single lost item by ID
            Long id = Long.parseLong(request.getParameter("id"));
            LostItem item = lostItemsDAO.getLostItemById(id);

            if (item != null) {
                request.setAttribute("item", item);
                request.getRequestDispatcher("/viewLostItem.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Lost item not found.");
            }

        } else if ("add".equals(action)) {
            List<String> categories = Arrays.asList("Electronics","Personal items","Pets", "Clothing", "Documents", "Others");
            request.setAttribute("categories", categories);
            // ✅ New block to show an add item form
            request.getRequestDispatcher("/addLostItem.jsp").forward(request, response);

        }else {
            // Default: Show all lost items or filter by search query
            List<LostItem> lostItems;
            if (query != null && !query.trim().isEmpty()) {
                lostItems = lostItemsDAO.searchLostItems(query.trim());
                request.setAttribute("query", query);
            } else {
                lostItems = lostItemsDAO.getAllLostItems();
            }

            request.setAttribute("items", lostItems);
            request.getRequestDispatcher("/lostItems.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        Part imagePart = request.getPart("image");
        if (imagePart != null) {
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            System.out.println("Uploaded file: " + fileName);
        }


        if ("add".equals(action)) {
            // Add new lost item
            LostItem item = new LostItem();
            item.setName(request.getParameter("name"));
            item.setDescription(request.getParameter("description"));
            item.setCategory(request.getParameter("category"));
            item.setCurrentLocation(request.getParameter("currentLocation"));
            item.setOwnerContact(request.getParameter("ownerContact"));
            item.setDateLost(request.getParameter("dateLost"));
            item.setLocationLost(request.getParameter("locationLost"));
            item.setOwnerEmail(request.getParameter("ownerEmail"));

            lostItemsDAO.addLostItem(item);
            response.sendRedirect(request.getContextPath() + "/lost-items");

        } else if ("update".equals(action)) {
            // Update an existing lost item
            Long id = Long.parseLong(request.getParameter("id"));
            LostItem item = new LostItem();
            item.setId(id);
            item.setName(request.getParameter("name"));
            item.setDescription(request.getParameter("description"));
            item.setCategory(request.getParameter("category"));
            item.setCurrentLocation(request.getParameter("currentLocation"));
            item.setOwnerContact(request.getParameter("ownerContact"));
            item.setDateLost(request.getParameter("dateLost"));
            item.setLocationLost(request.getParameter("locationLost"));
            item.setOwnerEmail(request.getParameter("ownerEmail"));

            lostItemsDAO.updateLostItem(item);
            response.sendRedirect(request.getContextPath() + "/lost-items");
        }
    }
}
