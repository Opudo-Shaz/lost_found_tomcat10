package com.example.lostandfound.scheduler;

import com.example.lostandfound.daos.FoundItemsDAO;
import com.example.lostandfound.EmailUtil;
import com.example.lostandfound.model.FoundItem;

import jakarta.mail.MessagingException;
import java.util.List;
import java.util.TimerTask;

public class EmailNotificationTask extends TimerTask {

    private final FoundItemsDAO foundItemDAO = new FoundItemsDAO();

    @Override
    public void run() {
        List<FoundItem> pendingItems = foundItemDAO.getPendingItems();

        for (FoundItem foundItem : pendingItems) {
            if (foundItem.getClaimantEmail() != null && !foundItem.getClaimantEmail().isEmpty()
                    && foundItem.getName() != null && foundItem.getId() != null) {

                try {
                    String baseUrl = "http://localhost:8080/LostAndfound_system_war_exploded";
                    EmailUtil.sendFollowUpNotification(
                            foundItem.getClaimantEmail(),
                            foundItem.getName(),
                            foundItem.getId(),
                            baseUrl
                    );
                } catch (MessagingException e) {
                    System.err.println("Failed to send follow-up email: " + e.getMessage());
                }

            } else {
                System.err.println("Skipping item due to missing data: " + foundItem);
            }
        }
    }
}
