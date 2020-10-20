package com.app.omahdilit.api;

import com.app.omahdilit.response.TukangListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TukangListApi {
    @GET("tukang")
    Call<TukangListResponse> getTukangList();
}
