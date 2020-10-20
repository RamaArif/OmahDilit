package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JarakResponse {
    @SerializedName("jarak")
    @Expose
    private Integer jarak;

    public JarakResponse(Integer jarak) {
        this.jarak = jarak;
    }

    public Integer getJarak() {
        return jarak;
    }
}
