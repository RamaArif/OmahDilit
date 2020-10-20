package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TukangListResponse {
    @SerializedName("body")
    @Expose
    private List<TukangListItem> tukangItems;

    public TukangListResponse(List<TukangListItem> tukangItems) {
        this.tukangItems = tukangItems;
    }

    public List<TukangListItem> getTukangItems() {
        return tukangItems;
    }
}
