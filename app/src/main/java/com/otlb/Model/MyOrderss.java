package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrderss {


    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("amountDelivery")
    @Expose
    private String amountDelivery;
    @SerializedName("typeShift")
    @Expose
    private String typeShift;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("totalPrice")
    @Expose
    private Double totalPrice;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountDelivery() {
        return amountDelivery;
    }

    public void setAmountDelivery(String amountDelivery) {
        this.amountDelivery = amountDelivery;
    }

    public String getTypeShift() {
        return typeShift;
    }

    public void setTypeShift(String typeShift) {
        this.typeShift = typeShift;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
