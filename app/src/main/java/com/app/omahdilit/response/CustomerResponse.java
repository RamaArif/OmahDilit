package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addres")
    @Expose
    private String addres;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public CustomerResponse(Integer id, String name, String addres, String number, String email, String uid, String photo, Double lat, Double lng) {
        this.id = id;
        this.name = name;
        this.addres = addres;
        this.number = number;
        this.email = email;
        this.uid = uid;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddres() {
        return addres;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getPhoto() {
        return photo;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
