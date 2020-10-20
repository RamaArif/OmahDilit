package com.app.omahdilit.api;

import com.app.omahdilit.response.DetailModelItem;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DetailModelApi {

    @POST("detailrambut?=id")
    Call<DetailModelItem> getDetailModel(@Query("id")String id);
}
