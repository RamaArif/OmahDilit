package com.app.omahdilit.response;

import com.google.gson.annotations.SerializedName;

public class DetailPromoItem {

    @SerializedName("id")
    private String idPromo;

    @SerializedName("nama")
    private String namaPromo;

    @SerializedName("potongan")
    private String potongan;

    @SerializedName("photo")
    private String urlPromo;

    @SerializedName("detail")
    private String detailPromo;

    @SerializedName("tglawal")
    private String tglAwalPromo;

    @SerializedName("tglakhir")
    private String tglAkhirPromo;

    public DetailPromoItem(String idPromo, String namaPromo, String potongan, String urlPromo, String detailPromo, String tglAwalPromo, String tglAkhirPromo) {
        this.idPromo = idPromo;
        this.namaPromo = namaPromo;
        this.potongan = potongan;
        this.urlPromo = urlPromo;
        this.detailPromo = detailPromo;
        this.tglAwalPromo = tglAwalPromo;
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

    public String getDetailPromo() {
        return detailPromo;
    }

    public String getTglAwalPromo() {
        return tglAwalPromo;
    }

    public String getTglAkhirPromo() {
        return tglAkhirPromo;
    }
}
