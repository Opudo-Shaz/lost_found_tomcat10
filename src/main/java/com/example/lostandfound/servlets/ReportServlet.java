package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.FoundItemsDAO;
import com.example.lostandfound.daos.LostItemsDAO;
import com.example.lostandfound.model.FoundItem;
import com.example.lostandfound.model.LostItem;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private final LostItemsDAO lostItemsDAO = new LostItemsDAO();
    private final FoundItemsDAO foundItemsDAO = new FoundItemsDAO();

    @Override
    public void init() throws ServletException {
        System.out.println("ReportServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String format = request.getParameter("format");

        // Get filter parameters
        String category = request.getParameter("category");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String location = request.getParameter("location");
        String status = request.getParameter("status");

        // Handle PDF generation logic
        if ("pdf".equalsIgnoreCase(format)) {
            if ("lost".equalsIgnoreCase(type)) {
                List<LostItem> lostItems = lostItemsDAO.getFilteredLostItems(category, fromDate, toDate, location);
                generateLostItemsPdf(response, lostItems);
            } else if ("found".equalsIgnoreCase(type)) {
                List<FoundItem> foundItems = foundItemsDAO.getFilteredFoundItems(category, location, fromDate, toDate, status);
                generateFoundItemsPdf(response, foundItems);
            }
        } else { // Default report page
            if ("lost".equalsIgnoreCase(type)) {
                List<LostItem> lostItems = lostItemsDAO.getFilteredLostItems(category, fromDate, toDate, location);
                request.setAttribute("items", lostItems);
                request.setAttribute("category", category);
                request.setAttribute("fromDate", fromDate);
                request.setAttribute("toDate", toDate);
                request.setAttribute("location", location);
                request.getRequestDispatcher("/reports/lostItemsReport.jsp").forward(request, response);
            } else if ("found".equalsIgnoreCase(type)) {
                List<FoundItem> foundItems = foundItemsDAO.getFilteredFoundItems(category, location, fromDate, toDate, status);
                request.setAttribute("items", foundItems);
                request.setAttribute("category", category);
                request.setAttribute("fromDate", fromDate);
                request.setAttribute("toDate", toDate);
                request.setAttribute("location", location);
                request.setAttribute("status", status);
                request.getRequestDispatcher("/reports/foundItemsReport.jsp").forward(request, response);
            }

        }
    }

    // Method to generate PDF for Lost Items
    private void generateLostItemsPdf(HttpServletResponse response, List<LostItem> lostItems) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=lost_items_report.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Lost Items Report"));
            document.add(new Paragraph("==================================="));

            for (LostItem item : lostItems) {
                document.add(new Paragraph("Item Name: " + item.getName()));
                document.add(new Paragraph("Description: " + item.getDescription()));
                document.add(new Paragraph("Category: " + item.getCategory()));
                document.add(new Paragraph("Location Lost: " + item.getLocationLost()));
                document.add(new Paragraph("Date Lost: " + item.getDateLost()));
                document.add(new Paragraph("-----------------------------------"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    // Method to generate PDF for Found Items
    private void generateFoundItemsPdf(HttpServletResponse response, List<FoundItem> foundItems) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=found_items_report.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Found Items Report"));
            document.add(new Paragraph("==================================="));

            for (FoundItem item : foundItems) {
                document.add(new Paragraph("Item Name: " + item.getName()));
                document.add(new Paragraph("Description: " + item.getDescription()));
                document.add(new Paragraph("Category: " + item.getCategory()));
                document.add(new Paragraph("Location Found: " + item.getLocationFound()));
                document.add(new Paragraph("Date Found: " + item.getFoundDate()));
                document.add(new Paragraph("Status: " + item.getStatus()));
                document.add(new Paragraph("-----------------------------------"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
