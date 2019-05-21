package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart_Details {

    @SerializedName("customers_basket_id")
    @Expose
    private String customersBasketId;
    @SerializedName("customers_id")
    @Expose
    private String customersId;
    @SerializedName("products_id")
    @Expose
    private String productsId;
    @SerializedName("customers_basket_quantity")
    @Expose
    private String customersBasketQuantity;
    @SerializedName("final_price")
    @Expose
    private String finalPrice;
    @SerializedName("customers_basket_date_added")
    @Expose
    private String customersBasketDateAdded;
    @SerializedName("is_order")
    @Expose
    private String isOrder;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("products_type")
    @Expose
    private String productsType;
    @SerializedName("min_order")
    @Expose
    private String minOrder;
    @SerializedName("max_order")
    @Expose
    private String maxOrder;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("products_slug")
    @Expose
    private String productsSlug;

    @SerializedName("products_quantity")
    @Expose
    private String productsQuantity;

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }
    public String getCustomersBasketId() {
        return customersBasketId;
    }

    public void setCustomersBasketId(String customersBasketId) {
        this.customersBasketId = customersBasketId;
    }

    public String getCustomersId() {
        return customersId;
    }

    public void setCustomersId(String customersId) {
        this.customersId = customersId;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getCustomersBasketQuantity() {
        return customersBasketQuantity;
    }

    public void setCustomersBasketQuantity(String customersBasketQuantity) {
        this.customersBasketQuantity = customersBasketQuantity;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCustomersBasketDateAdded() {
        return customersBasketDateAdded;
    }

    public void setCustomersBasketDateAdded(String customersBasketDateAdded) {
        this.customersBasketDateAdded = customersBasketDateAdded;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductsType() {
        return productsType;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
    }

    public String getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(String minOrder) {
        this.minOrder = minOrder;
    }

    public String getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(String maxOrder) {
        this.maxOrder = maxOrder;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductsSlug() {
        return productsSlug;
    }

    public void setProductsSlug(String productsSlug) {
        this.productsSlug = productsSlug;
    }

}
