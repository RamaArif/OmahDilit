package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CekTransaksiResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("code_transaksi")
    @Expose
    private String kodeTransaksi;

    public CekTransaksiResponse(Integer status, String kodeTransaksi) {
        this.status = status;
        this.kodeTransaksi = kodeTransaksi;
    }

    public Integer getStatus() {
        return status;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }
}
