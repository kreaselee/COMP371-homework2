package com.example.homework2;

import java.util.ArrayList;

public class Beer {
    // instance variables
    private String name;
    private String description;
    private String imageUrl;
    private Boolean favorite;

    private String abv;
    private String firstBrewed;
    private String foodPairings;
    private String tips;


    // constructor
    public Beer(String name, String description, String imageUrl,
                String abv, String firstBrewed, String foodPairings, String tips) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.abv = abv;
        this.firstBrewed = firstBrewed;
        this.foodPairings = foodPairings;
        this.tips = tips;
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

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
