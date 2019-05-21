package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offers_Details {


    @SerializedName("restaurantId")
    @Expose
    private String restaurantId;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("restaurantImage")
    @Expose
    private String restaurantImage;
    @SerializedName("restaurantLogo")
    @Expose
    private String restaurantLogo;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("countRating")
    @Expose
    private String countRating;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCountRating() {
        return countRating;
    }

    public void setCountRating(String countRating) {
        this.countRating = countRating;
    }

}
