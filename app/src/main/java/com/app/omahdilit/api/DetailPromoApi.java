package com.app.omahdilit.api;

import com.app.omahdilit.response.DetailModelItem;
import com.app.omahdilit.response.DetailPromoItem;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DetailPromoApi {

    @POST("detailpromo?id=")
    Call<DetailPromoItem> getDetailPromo(@Query("id") Integer idPromo);
}
