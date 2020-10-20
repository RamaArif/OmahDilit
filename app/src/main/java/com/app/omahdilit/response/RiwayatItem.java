package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatItem {
    @SerializedName("id")
    @Expose
    private Integer id;
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
    private String totalharga;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("code_transaksi")
    @Expose
    private String codeTransaksi;
    @SerializedName("promo")
    @Expose
    private Integer promo;
    @SerializedName("hargacukur")
    @Expose
    private Integer hargacukur;
    @SerializedName("penanganan")
    @Expose
    private Integer penanganan;
    @SerializedName("jarak")
    @Expose
    private Integer jarak;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public RiwayatItem(Integer id, String idtukang, String idcustomer, String idmodel, String totalharga, Double lat, Double lng, String alamat, String codeTransaksi, Integer promo, Integer hargacukur, Integer penanganan, Integer jarak, String status, String createdAt) {
        this.id = id;
        this.idtukang = idtukang;
        this.idcustomer = idcustomer;
        this.idmodel = idmodel;
        this.totalharga = totalharga;
        this.lat = lat;
        this.lng = lng;
        this.alamat = alamat;
        this.codeTransaksi = codeTransaksi;
        this.promo = promo;
        this.hargacukur = hargacukur;
        this.penanganan = penanganan;
        this.jarak = jarak;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
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

    public String getTotalharga() {
        return totalharga;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getCodeTransaksi() {
        return codeTransaksi;
    }

    public Integer getPromo() {
        return promo;
    }

    public Integer getHargacukur() {
        return hargacukur;
    }

    public Integer getPenanganan() {
        return penanganan;
    }

    public Integer getJarak() {
        return jarak;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
