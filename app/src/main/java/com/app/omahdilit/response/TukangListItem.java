package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TukangListItem {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jenkel")
    @Expose
    private String jenkel;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("status")
    @Expose
    private String status;

    public TukangListItem(Integer id, String name, String jenkel, String photo, Double lat, Double lng, String status) {
        this.id = id;
        this.name = name;
        this.jenkel = jenkel;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJenkel() {
        return jenkel;
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

    public String getStatus() {
        return status;
    }
}
