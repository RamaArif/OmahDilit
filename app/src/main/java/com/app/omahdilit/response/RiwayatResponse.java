package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatResponse {
    @SerializedName("body")
    @Expose
    private List<RiwayatItem> body;

    public RiwayatResponse(List<RiwayatItem> body) {
        this.body = body;
    }

    public List<RiwayatItem> getBody() {
        return body;
    }
}
