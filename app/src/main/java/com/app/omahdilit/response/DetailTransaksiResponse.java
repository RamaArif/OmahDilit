package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTransaksiResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code_transaksi")
    @Expose
    private String codeTransaksi;
    @SerializedName("idtukang")
    @Expose
    private String idtukang;
    @SerializedName("idcustomer")
    @Expose
    private String idcustomer;
    @SerializedName("idmodel")
    @Expose
    private String idmodel;
    @SerializedName("totalharga")
    @Expose
    private Integer totalharga;
    @SerializedName("promo")
    @Expose
    private Integer promo;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jarak")
    @Expose
    private Integer jarak;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public DetailTransaksiResponse(Integer id, String codeTransaksi, String idtukang, String idcustomer, String idmodel, Integer totalharga, Integer promo, String alamat, Integer jarak, Double lat, Double lng, Integer status, String createdAt) {
        this.id = id;
        this.codeTransaksi = codeTransaksi;
        this.idtukang = idtukang;
        this.idcustomer = idcustomer;
        this.idmodel = idmodel;
        this.totalharga = totalharga;
        this.promo = promo;
        this.alamat = alamat;
        this.jarak = jarak;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getCodeTransaksi() {
        return codeTransaksi;
    }

    public String getIdtukang() {
        return idtukang;
    }

    public String getIdcustomer() {
        return idcustomer;
    }

    public String getIdmodel() {
        return idmodel;
    }

    public Integer getTotalharga() {
        return totalharga;
    }

    public Integer getPromo() {
        return promo;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getJarak() {
        return jarak;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
