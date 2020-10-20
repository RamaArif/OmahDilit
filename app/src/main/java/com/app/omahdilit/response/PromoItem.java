package com.app.omahdilit.response;

import com.google.gson.annotations.SerializedName;

public class PromoItem {

    @SerializedName("id")
    private Integer idPromo;

    @SerializedName("nama")
    private String namaPromo;

    @SerializedName("potongan")
    private Integer potongan;

    @SerializedName("photo")
    private String urlPromo;

    @SerializedName("tglakhir")
    private String tglAkhirPromo;

    public PromoItem(Integer idPromo, String namaPromo, Integer potongan, String urlPromo, String tglAkhirPromo) {
        this.idPromo = idPromo;
        this.namaPromo = namaPromo;
        this.potongan = potongan;
        this.urlPromo = urlPromo;
        this.tglAkhirPromo = tglAkhirPromo;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public String getNamaPromo() {
        return namaPromo;
    }

    public Integer getPotongan() {
        return potongan;
    }

    public String getUrlPromo() {
        return urlPromo;
    }

    public String getTglAkhirPromo() {
        return tglAkhirPromo;
    }
}
