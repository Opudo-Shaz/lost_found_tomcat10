package com.example.lostandfound.servlets;

import com.example.lostandfound.daos.FoundItemsDAO;
import com.example.lostandfound.model.FoundItem;
import com.example.lostandfound.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet("/found-items/save-claim/*")
@MultipartConfig
public class SaveClaimServlet extends HttpServlet {

    private FoundItemsDAO foundItemsDAO;

    @Override
    public void init() throws ServletException {
        foundItemsDAO = new FoundItemsDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo(); // e.g., "/123"
        Long itemId = Long.parseLong(pathInfo.substring(1));

        // Get form data
        String claimerEmail = request.getParameter("claimerEmail");
        String claimerContact = request.getParameter("claimerContact");
        String claimerNote = request.getParameter("claimerNote");

        Part imagePart = request.getPart("image");
        Part receiptPart = request.getPart("receipt");
        // You can handle saving files if needed, for now skipping

        try {
            // Get the found item
            FoundItem item = foundItemsDAO.getFoundItemById(itemId);

            if (item == null) {
                request.setAttribute("error", "Item not found.");
                request.getRequestDispatcher("/claimFoundItem.jsp").forward(request, response);
                return;
            }
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();


            // Update the item status to pending and add claimant details
            item.setStatus(FoundItem.Status.PENDING);
            item.setClaimantEmail(claimerEmail);
            item.setClaimerContact(claimerContact);
            item.setClaimerNote(claimerNote);

            foundItemsDAO.updateFoundItem(item);

            // Log that the item was updated successfully
            System.out.println("Claim submitted for item: " + item.getName());

            // Prepare email details for both the finder and the claimant
            String itemName = item.getName();
            String finderEmail = item.getFinderEmail();
            String finderContact = item.getFinderContact();
            String locationFound = item.getLocationFound(); // Assuming `location` is available for the item

            // Email to Claimer (Notification Email)
            System.out.println("Sending email to claimer: " + claimerEmail);
            EmailUtil.sendClaimantNotificationEmail(
                    claimerEmail,
                    itemName,
                    finderContact,
                    locationFound
            );

            // Email to Finder (Notification Email)
            System.out.println("Sending email to finder: " + finderEmail);
            EmailUtil.sendClaimNotificationEmail(
                    finderEmail,
                    claimerEmail, // Claimed by the claimer
                    itemName,
                    claimerContact,
                    claimerNote,
                    "" // You may pass the actual image URLs if available
            );

            // Set success message and necessary attributes for the view
            request.setAttribute("success", "Your claim has been submitted!");
            request.setAttribute("finderEmail", finderEmail);
            request.setAttribute("finderContact", finderContact);
            request.setAttribute("itemId", itemId); // Pass itemId for further processing

            // Forward to the claim form page with a success message
            request.getRequestDispatcher("/claimFoundItem.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while submitting your claim.");
            request.getRequestDispatcher("/claimFoundItem.jsp").forward(request, response);
        }
    }
}
