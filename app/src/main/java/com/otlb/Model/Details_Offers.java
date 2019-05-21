package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details_Offers {


    @SerializedName("offerId")
    @Expose
    private Integer offerId;
    @SerializedName("menuDetailsId")
    @Expose
    private String menuDetailsId;
    @SerializedName("menuDetailsName")
    @Expose
    private String menuDetailsName;
    @SerializedName("originalPrice")
    @Expose
    private String originalPrice;
    @SerializedName("offerPrice")
    @Expose
    private String offerPrice;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("fromTime")
    @Expose
    private String fromTime;
    @SerializedName("toTime")
    @Expose
    private String toTime;
    @SerializedName("menuDetailsImage")
    @Expose
    private String menuDetailsImage;

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getMenuDetailsId() {
        return menuDetailsId;
    }

    public void setMenuDetailsId(String menuDetailsId) {
        this.menuDetailsId = menuDetailsId;
    }

    public String getMenuDetailsName() {
        return menuDetailsName;
    }

    public void setMenuDetailsName(String menuDetailsName) {
        this.menuDetailsName = menuDetailsName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getMenuDetailsImage() {
        return menuDetailsImage;
    }

    public void setMenuDetailsImage(String menuDetailsImage) {
        this.menuDetailsImage = menuDetailsImage;
    }
}
