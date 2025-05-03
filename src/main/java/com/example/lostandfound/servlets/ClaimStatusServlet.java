package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.FoundItemsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/found-items/claimStatus")
public class ClaimStatusServlet extends HttpServlet {

    private FoundItemsDAO foundItemDAO;

    @Override
    public void init() throws ServletException {
        foundItemDAO = new FoundItemsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Long id = Long.parseLong(idParam);

            if ("yes".equalsIgnoreCase(status)) {
                foundItemDAO.updateStatus(id, "CLAIMED");
                out.println("<html><body><p>✅ Thank you for confirming! Your item status has been updated to <strong>CLAIMED</strong>.</p></body></html>");
            } else if ("no".equalsIgnoreCase(status)) {
                out.println(
                        "<html>" +
                                "<body>" +
                                "<p>Please provide a reason for disputing:</p>" +
                                "<form action=\"/claimStatus\" method=\"post\">" +
                                "<input type=\"hidden\" name=\"action\" value=\"dispute\"/>" +
                                "<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/>" +
                                "<textarea name=\"reason\" placeholder=\"Please explain to us what happened...\" required></textarea><br/>" +
                                "<button type=\"submit\">Submit</button>" +
                                "</form>" +
                                "</body>" +
                                "</html>"
                );
            } else {
                out.println("<html><body><p>⚠️ Invalid request. Please provide a valid status (yes/no).</p></body></html>");
            }

        } catch (NumberFormatException e) {
            out.println("<html><body><p>❌ Invalid item ID.</p></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        String reason = request.getParameter("reason");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("dispute".equals(action)) {
            try {
                Long id = Long.parseLong(idParam);

                if (reason == null || reason.trim().isEmpty()) {
                    out.println("<html><body><p>⚠️ Reason cannot be empty. Please provide a valid reason for disputing.</p></body></html>");
                    return;
                }

                foundItemDAO.updateStatusToDisputed(id, reason);
                out.println("<html><body><p>✅ Thank you for your feedback! We will look into the matter and get back to you shortly.</p></body></html>");

            } catch (NumberFormatException e) {
                out.println("<html><body><p>❌ Invalid item ID.</p></body></html>");
            }
        } else {
            out.println("<html><body><p>⚠️ Unknown action.</p></body></html>");
        }
    }
}
