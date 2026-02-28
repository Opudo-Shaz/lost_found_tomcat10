package com.example.lostandfound;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {
    static boolean isHtml = true;
    private static final String FROM_EMAIL = "patikalostandfound@gmail.com";
    private static final String PASSWORD = "rtos djvp dque xtqr";

    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });
    }

    public static void sendEmail(String recipientEmail, String subject, String body) throws MessagingException {
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            throw new IllegalArgumentException("Recipient email is missing or invalid.");
        }

        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setContent(body, isHtml ? "text/html; charset=UTF-8" : "text/plain; charset=UTF-8");

        Transport.send(message);
    }

    public static void sendClaimNotificationEmail(String finderEmail, String claimedBy, String itemName, String claimerContact, String claimerNote, String claimerImages) throws MessagingException {
        String subject = "Claim Placed for Your Found Item";

        String body = String.format(
                "<html>\n" +
                        "<body>\n" +
                        "    <p>Hello,</p>\n" +
                        "    <p>A claim has been placed for your found item: <strong>'%s'</strong> by <strong>%s</strong>.</p>\n" +
                        "    <p><strong>Claimer's Contact:</strong> %s</p>\n" +
                        "    <p><strong>Claimer's Note:</strong> %s</p>\n" +
                        "    <p><strong>Claimer's Images:</strong></p>\n" +
                        "    <p><img src=\"%s\" alt=\"Claimer's Image\" style=\"max-width: 100%%; height: auto;\"/></p>\n" +
                        "    <p>Best regards,</p>\n" +
                        "    <p>Patika Lost & Found Team</p>\n" +
                        "</body>\n" +
                        "</html>",
                itemName, claimedBy, claimerContact, claimerNote, claimerImages
        );

        sendEmail(finderEmail, subject, body);
    }

    public static void sendClaimantNotificationEmail(String claimantEmail, String itemName, String finderContact, String locationFound) throws MessagingException {
        String subject = "Your Claim Submission Details";

        String body = String.format(
                "<html>\n" +
                        "<body>\n" +
                        "    <p>Hello,</p>\n" +
                        "    <p>Thank you for submitting a claim for the item: <strong>'%s'</strong>.</p>\n" +
                        "    <p>The finder has been notified and will review your claim. Here are the details of the finder to help you follow up:</p>\n" +
                        "    <p><strong>Finder's Contact:</strong> %s</p>\n" +
                        "    <p><strong>Found Item Location:</strong> %s</p>\n" +
                        "    <p>Please wait for the finder to contact you or feel free to reach out to them directly if needed.</p>\n" +
                        "    <p>Best regards,</p>\n" +
                        "    <p>Patika Lost & Found Team</p>\n" +
                        "</body>\n" +
                        "</html>",
                itemName, finderContact, locationFound
        );

        sendEmail(claimantEmail, subject, body);
    }

    public static void sendFollowUpNotification(String claimantEmail, String itemName, Long itemId, String baseUrl) throws MessagingException {
        String subject = "Have you retrieved your item?";

        String body = String.format(
                "<html>\n" +
                        "<body>\n" +
                        "    <p>Dear User,</p>\n" +
                        "    <p>We noticed that the item you claimed (<strong>%s</strong>) is still marked as pending in our system.</p>\n" +
                        "    <p>Please let us know if you have retrieved it by clicking one of the buttons below:</p>\n" +
                        "    <p>\n" +
                        "        <a href=\"%s/found-items/claimStatus?id=%d&status=yes\" style=\"padding: 10px 20px; color: white; background-color: green; text-decoration: none; border-radius: 5px;\">Yes</a>\n" +
                        "        <a href=\"%s/found-items/claimStatus?id=%d&status=no\" style=\"padding: 10px 20px; color: white; background-color: red; text-decoration: none; border-radius: 5px;\">No</a>\n" +
                        "    </p>\n" +
                        "    <p>Thank you,</p>\n" +
                        "    <p>Patika Lost and Found Team</p>\n" +
                        "</body>\n" +
                        "</html>",
                itemName, baseUrl, itemId, baseUrl, itemId
        );

        sendEmail(claimantEmail, subject, body);
    }

}
