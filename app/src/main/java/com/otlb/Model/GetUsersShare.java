package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUsersShare {


    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("money")
    @Expose
    private String money;
    @SerializedName("accept")
    @Expose
    private String accept;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
