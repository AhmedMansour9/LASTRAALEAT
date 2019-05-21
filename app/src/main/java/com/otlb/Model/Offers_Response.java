package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.otlb.Fragments.Offers;

import java.util.List;

public class Offers_Response {

    @SerializedName("data")
    @Expose
    private List<Offers_Details> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public List<Offers_Details> getData() {
        return data;
    }

    public void setData(List<Offers_Details> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
