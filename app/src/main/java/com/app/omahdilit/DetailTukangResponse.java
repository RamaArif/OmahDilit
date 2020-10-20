package com.app.omahdilit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailTukangResponse {

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
    @SerializedName("jenkel")
    @Expose
    private String jenkel;
    @SerializedName("uid")
    @Expose
    private Object uid;
    @SerializedName("pushtoken")
    @Expose
    private Object pushtoken;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public DetailTukangResponse(Integer id, String name, String addres, String number, String email, String jenkel, Object uid, Object pushtoken, Object photo, String status, Double lat, Double lng, String createdAt) {
        this.id = id;
        this.name = name;
        this.addres = addres;
        this.number = number;
        this.email = email;
        this.jenkel = jenkel;
        this.uid = uid;
        this.pushtoken = pushtoken;
        this.photo = photo;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
        this.createdAt = createdAt;
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

    public String getJenkel() {
        return jenkel;
    }

    public Object getUid() {
        return uid;
    }

    public Object getPushtoken() {
        return pushtoken;
    }

    public Object getPhoto() {
        return photo;
    }

    public String getStatus() {
        return status;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
