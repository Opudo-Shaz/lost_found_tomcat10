package com.example.lostandfound.model;

public class LostItem extends Item {

    private Long id;
    private String ownerContact;
    private String dateLost;          // You can use String for simplicity, or LocalDate
    private String locationLost;
    private String ownerEmail;


    public LostItem(Long id, String name, String description, String category, String currentLocation,
                    String ownerContact, String dateLost, String locationLost, String ownerEmail) {

        super(name, description, category, currentLocation);

        this.id = id;
        this.ownerContact = ownerContact;
        this.dateLost = dateLost;
        this.locationLost = locationLost;
        this.ownerEmail = ownerEmail;
    }

    public LostItem() {
        super();
    }

    // Getters and setters below

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getDateLost() {
        return dateLost;
    }

    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }

    public String getLocationLost() {
        return locationLost;
    }

    public void setLocationLost(String locationLost) {
        this.locationLost = locationLost;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
