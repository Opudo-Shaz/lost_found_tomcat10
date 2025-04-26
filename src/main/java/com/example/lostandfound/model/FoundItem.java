package com.example.lostandfound.model;

public class FoundItem extends Item {
    public enum Status {
        UNCLAIMED,
        PENDING,
        CLAIMED;
    }


    private Long id;
    private String foundDate;          // You can replace with LocalDateTime if you want
    private String itemHolderName;
    private String locationFound;
    private String finderContact;
    private String claimedBy;
    private String finderEmail;
    private String claimerContact;
    private String claimantEmail;
    private String claimerNote;
    private String finderImages;
    private String claimerImages;
    private Status status;
    private String disputeReason;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    //  Constructor
    public FoundItem(Long id, String name, String description, String category, String currentLocation,
                     String foundDate, String itemHolderName, String locationFound, String finderContact,
                     String claimedBy, String finderEmail, String claimerContact, String claimantEmail,
                     String claimerNote, String finderImages, String claimerImages, String status, String disputeReason) {

        super(name, description, category, currentLocation);  // Calling the parent Item constructor

        this.id = id;
        this.foundDate = foundDate;
        this.itemHolderName = itemHolderName;
        this.locationFound = locationFound;
        this.finderContact = finderContact;
        this.claimedBy = claimedBy;
        this.finderEmail = finderEmail;
        this.claimerContact = claimerContact;
        this.claimantEmail = claimantEmail;
        this.claimerNote = claimerNote;
        this.finderImages = finderImages;
        this.claimerImages = claimerImages;
        this.disputeReason = disputeReason;
    }

    // ✅ Empty Constructor (Optional but useful for DAO)
    public FoundItem() {}

    // ✅ Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getItemHolderName() {
        return itemHolderName;
    }

    public void setItemHolderName(String itemHolderName) {
        this.itemHolderName = itemHolderName;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public String getFinderContact() {
        return finderContact;
    }

    public void setFinderContact(String finderContact) {
        this.finderContact = finderContact;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(String claimedBy) {
        this.claimedBy = claimedBy;
    }

    public String getFinderEmail() {
        return finderEmail;
    }

    public void setFinderEmail(String finderEmail) {
        this.finderEmail = finderEmail;
    }

    public String getClaimerContact() {
        return claimerContact;
    }

    public void setClaimerContact(String claimerContact) {
        this.claimerContact = claimerContact;
    }

    public String getClaimantEmail() {
        return claimantEmail;
    }

    public void setClaimantEmail(String claimantEmail) {
        this.claimantEmail = claimantEmail;
    }

    public String getClaimerNote() {
        return claimerNote;
    }

    public void setClaimerNote(String claimerNote) {
        this.claimerNote = claimerNote;
    }

    public String getFinderImages() {
        return finderImages;
    }

    public void setFinderImages(String finderImages) {
        this.finderImages = finderImages;
    }

    public String getClaimerImages() {
        return claimerImages;
    }

    public void setClaimerImages(String claimerImages) {
        this.claimerImages = claimerImages;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public String getDisputeReason() {
        return disputeReason;
    }

    public void setDisputeReason(String disputeReason) {
        this.disputeReason = disputeReason;
    }
}
