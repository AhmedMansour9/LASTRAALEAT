package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsersShare_Response {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<GetUsersShare> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GetUsersShare> getData() {
        return data;
    }

    public void setData(List<GetUsersShare> data) {
        this.data = data;
    }
}
