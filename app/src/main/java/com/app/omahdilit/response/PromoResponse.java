package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoResponse {

    @SerializedName("body")
    @Expose
    private List<PromoItem> promoItem;

    public PromoResponse(List<PromoItem> promoItem) {
        this.promoItem = promoItem;
    }

    public List<PromoItem> getPromoItem() {
        return promoItem;
    }
}
