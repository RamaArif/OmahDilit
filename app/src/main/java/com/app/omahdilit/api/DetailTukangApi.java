package com.app.omahdilit.api;

import com.app.omahdilit.DetailTukangResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DetailTukangApi {

    @POST("detailtukang")
    Call<DetailTukangResponse> getDetailTukang(@Query("idTukang") String idTukang);
}
