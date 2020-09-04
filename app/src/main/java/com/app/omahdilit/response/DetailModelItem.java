package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailModelItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_model")
    @Expose
    private String namaModel;
    @SerializedName("photo1")
    @Expose
    private String photo1;
    @SerializedName("photo2")
    @Expose
    private String photo2;
    @SerializedName("photo3")
    @Expose
    private String photo3;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("jenis_model")
    @Expose
    private String jenisModel;
    @SerializedName("detail")
    @Expose
    private String detail;

    public DetailModelItem(Integer id, String namaModel, String photo1, String photo2, String photo3, String kategori, String jenisModel, String detail) {
        this.id = id;
        this.namaModel = namaModel;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.kategori = kategori;
        this.jenisModel = jenisModel;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public String getNamaModel() {
        return namaModel;
    }

    public String getPhoto1() {
        return photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJenisModel() {
        return jenisModel;
    }

    public String getDetail() {
        return detail;
    }
}
