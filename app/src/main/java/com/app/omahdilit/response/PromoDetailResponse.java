package com.app.omahdilit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoDetailResponse {

    @SerializedName("body")
    @Expose
    private List<PromoDetail> promoDetail;

    public List<PromoDetail> getPromoDetail() {
        return promoDetail;
    }

    public PromoDetailResponse(List<PromoDetail> promoDetail) {
        this.promoDetail = promoDetail;
    }
}
