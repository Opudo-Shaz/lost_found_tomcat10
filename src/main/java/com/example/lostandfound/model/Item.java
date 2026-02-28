package com.example.lostandfound.model;

public class Item {

    protected String name;
    protected String description;
    protected String category;
    protected String currentLocation;

    // Constructor
    public Item(String name, String description, String category, String currentLocation) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.currentLocation = currentLocation;
    }

    // Default constructor
    public Item() {}

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
