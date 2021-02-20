package com.example.homework2;

public class Beer {
    // instance variables
    private String name;
    private String description;
    private String imageUrl;
    private Boolean favorite;

    // constructor
    public Beer(String name, String description, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.favorite = false;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
