package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransaksiResponse {
    @SerializedName("body")
    @Expose
    String kodeTrans;
    @SerializedName("id_pesanan")
    @Expose
    Integer idTrans;
    @SerializedName("error")
    @Expose
    Boolean error;

    public TransaksiResponse(String kodeTrans, Integer idTrans, Boolean error) {
        this.kodeTrans = kodeTrans;
        this.idTrans = idTrans;
        this.error = error;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public String getKodeTrans() {
        return kodeTrans;
    }

    public Boolean getError() {
        return error;
    }
}
