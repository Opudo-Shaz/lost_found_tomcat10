package com.example.lostandfound.servlets;

import com.example.lostandfound.EmailUtil;
import jakarta.mail.MessagingException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SendClaimEmail")
public class SendClaimEmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Common claim data
        String finderEmail = request.getParameter("finderEmail");
        String claimedBy = request.getParameter("claimedBy");
        String itemName = request.getParameter("itemName");
        String claimerContact = request.getParameter("claimerContact");
        String claimerNote = request.getParameter("claimerNote");
        String claimerImages = request.getParameter("claimerImages");

        // Claimer-specific info
        String claimantEmail = request.getParameter("claimantEmail");
        String finderContact = request.getParameter("finderContact");
        String locationFound = request.getParameter("locationFound");

        // For optional follow-up
        String baseUrl = request.getParameter("baseUrl");
        String itemIdParam = request.getParameter("itemId");
        Long itemId = itemIdParam != null ? Long.parseLong(itemIdParam) : null;

        try {
            // 1. Notify Finder
            EmailUtil.sendClaimNotificationEmail(finderEmail, claimedBy, itemName, claimerContact, claimerNote, claimerImages);

            // 2. Notify Claimer
            if (claimantEmail != null && finderContact != null && locationFound != null) {
                EmailUtil.sendClaimantNotificationEmail(claimantEmail, itemName, finderContact, locationFound);
            }

            // 3. Optionally send follow-up link
            if (claimantEmail != null && baseUrl != null && itemId != null) {
                EmailUtil.sendFollowUpNotification(claimantEmail, itemName, itemId, baseUrl);
            }

            response.getWriter().println("All relevant emails sent successfully.");

        } catch (MessagingException e) {
            response.getWriter().println("Failed to send one or more emails: " + e.getMessage());
        }
    }
}
