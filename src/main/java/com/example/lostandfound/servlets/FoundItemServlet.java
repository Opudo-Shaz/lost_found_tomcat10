package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.FoundItemsDAO;
import com.example.lostandfound.model.FoundItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@WebServlet("/found-items")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class FoundItemServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FoundItemServlet.class.getName());
    private FoundItemsDAO foundItemsDAO;

    @Override
    public void init() throws ServletException {
        foundItemsDAO = new FoundItemsDAO();
        logger.info("FoundItemServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");

        try {
            if ("view".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                FoundItem item = foundItemsDAO.viewFoundItem(id);

                if (item != null) {
                    request.setAttribute("foundItem", item);
                    request.getRequestDispatcher("/viewFoundItem.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Found item not found.");
                }

            } else if ("edit".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                FoundItem item = foundItemsDAO.getFoundItemById(id);
                request.setAttribute("foundItem", item);
                request.getRequestDispatcher("/editFoundItem.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                foundItemsDAO.deleteFoundItem(id);
                response.sendRedirect(request.getContextPath() + "/found-items");

            } else if ("post-found-item".equals(action)) {
                List<String> categories = List.of("Electronics","Personal items","Pets", "Clothing", "Jewelry", "Books", "Documents", "Other");
                request.setAttribute("categories", categories);
                request.setAttribute("item", new FoundItem());
                request.getRequestDispatcher("/addFoundItem.jsp").forward(request, response);

            } else if ("claim".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                FoundItem item = foundItemsDAO.getFoundItemById(id);
                request.setAttribute("foundItem", item);
                request.getRequestDispatcher("/claimFoundItem.jsp").forward(request, response);
            } else {
                List<FoundItem> foundItems;
                if (query != null && !query.trim().isEmpty()) {
                    foundItems = foundItemsDAO.searchFoundItems(query.trim());
                    request.setAttribute("query", query);
                } else {
                    foundItems = foundItemsDAO.getAllFoundItems();
                }

                request.setAttribute("items", foundItems);
                request.getRequestDispatcher("/foundItems.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.severe("Error during GET: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                FoundItem item = new FoundItem();
                item.setName(request.getParameter("name"));
                item.setDescription(request.getParameter("description"));
                item.setCategory(request.getParameter("category"));
                item.setLocationFound(request.getParameter("locationFound"));
                item.setFinderContact(request.getParameter("finderContact"));
                item.setFinderEmail(request.getParameter("finderEmail"));
                item.setFoundDate(request.getParameter("dateFound"));
                item.setStatus(FoundItem.Status.UNCLAIMED);
                item.setDisputeReason(null);

                // Handle file upload
                Part filePart = request.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
                    String newFileName = UUID.randomUUID().toString() + "." + extension;

                    String uploadPath = getServletContext().getRealPath("/") + "uploads";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    String fullPath = uploadPath + File.separator + newFileName;
                    filePart.write(fullPath);

                    item.setImageUrl("uploads/" + newFileName);
                }

                foundItemsDAO.addFoundItem(item);
                response.sendRedirect(request.getContextPath() + "/found-items");

            } else if ("update".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                FoundItem item = new FoundItem();
                item.setId(id);
                item.setName(request.getParameter("name"));
                item.setDescription(request.getParameter("description"));
                item.setCategory(request.getParameter("category"));
                item.setLocationFound(request.getParameter("locationFound"));
                item.setFinderContact(request.getParameter("finderContact"));
                item.setFinderEmail(request.getParameter("finderEmail"));
                item.setFoundDate(request.getParameter("dateFound"));
                item.setStatus(FoundItem.Status.valueOf(request.getParameter("status")));
                item.setDisputeReason(request.getParameter("disputeReason"));

                // Handle image update if needed
                Part filePart = request.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
                    String newFileName = UUID.randomUUID().toString() + "." + extension;

                    String uploadPath = getServletContext().getRealPath("/") + "uploads";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    String fullPath = uploadPath + File.separator + newFileName;
                    filePart.write(fullPath);

                    item.setImageUrl("uploads/" + newFileName);
                }

                foundItemsDAO.updateFoundItem(item);
                response.sendRedirect(request.getContextPath() + "/found-items");
            }

        } catch (Exception e) {
            logger.severe("Error during POST: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
