package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransaksiResponse {
    @SerializedName("body")
    @Expose
    String kodeTrans;
    @SerializedName("error")
    @Expose
    Boolean error;

    public TransaksiResponse(String kodeTrans, Boolean error) {
        this.kodeTrans = kodeTrans;
        this.error = error;
    }

    public String getKodeTrans() {
        return kodeTrans;
    }

    public Boolean getError() {
        return error;
    }
}
