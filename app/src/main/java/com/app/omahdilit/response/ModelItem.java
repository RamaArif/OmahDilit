package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_model")
    @Expose
    private String namaModel;
    @SerializedName("photo1")
    @Expose
    private String photo1;
    @SerializedName("kategori")
    @Expose
    private String kategori;

    public ModelItem(Integer id, String namaModel, String photo1, String kategori) {
        this.id = id;
        this.namaModel = namaModel;
        this.photo1 = photo1;
        this.kategori = kategori;
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

    public String getKategori() {
        return kategori;
    }
}
