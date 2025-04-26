package com.example.lostandfound.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet("/found-items/save-claim/*")
public class SaveClaimServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo(); // e.g., "/123"
        Long itemId = Long.parseLong(pathInfo.substring(1));

        String note = request.getParameter("claimerNote");

        Part imagePart = request.getPart("image");
        Part receiptPart = request.getPart("receipt");

        // Handle and validate the claim...
        // Save to DB or forward to admin review, etc.

        // Redirect or forward back with a success message:
        request.setAttribute("success", "Your claim has been submitted!");
        request.getRequestDispatcher("/claimFoundItem.jsp").forward(request, response);
    }
}
