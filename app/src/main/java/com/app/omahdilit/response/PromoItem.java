package com.app.omahdilit.response;

import com.google.gson.annotations.SerializedName;

public class PromoItem {

    @SerializedName("id")
    private String idPromo;

    @SerializedName("nama")
    private String namaPromo;

    @SerializedName("potongan")
    private String potongan;

    @SerializedName("photo")
    private String urlPromo;

    @SerializedName("tglakhir")
    private String tglAkhirPromo;

    public PromoItem(String idPromo, String namaPromo, String potongan, String urlPromo, String tglAkhirPromo) {
        this.idPromo = idPromo;
        this.namaPromo = namaPromo;
        this.potongan = potongan;
        this.urlPromo = urlPromo;
        this.tglAkhirPromo = tglAkhirPromo;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public String getNamaPromo() {
        return namaPromo;
    }

    public String getPotongan() {
        return potongan;
    }

    public String getUrlPromo() {
        return urlPromo;
    }

    public String getTglAkhirPromo() {
        return tglAkhirPromo;
    }
}
