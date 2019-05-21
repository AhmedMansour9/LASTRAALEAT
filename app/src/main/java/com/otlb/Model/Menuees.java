package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Menuees {

    @SerializedName("data")
    @Expose
    private List<Menuu> data = null;
    @SerializedName("period")
    @Expose
    private String period;

    public List<Menuu> getData() {
        return data;
    }

    public void setData(List<Menuu> data) {
        this.data = data;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
