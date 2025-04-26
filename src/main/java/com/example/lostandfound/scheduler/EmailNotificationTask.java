package com.example.lostandfound.scheduler;

import com.example.lostandfound.daos.FoundItemDAO;
import com.example.lostandfound.EmailUtil;
import com.example.lostandfound.entity.FoundItem;

import javax.mail.MessagingException;
import java.util.List;
import java.util.TimerTask;

public class EmailNotificationTask extends TimerTask {

    private final FoundItemDAO foundItemDAO = new FoundItemDAO();

    @Override
    public void run() {
        List<FoundItem> pendingItems = foundItemDAO.getPendingItems();

        for (FoundItem foundItem : pendingItems) {
            if (foundItem.getClaimantEmail() != null && !foundItem.getClaimantEmail().isEmpty()
                    && foundItem.getName() != null && foundItem.getId() != null) {

                try {
                    String baseUrl = "http://yourdomain.com"; // replace with actual base URL
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
