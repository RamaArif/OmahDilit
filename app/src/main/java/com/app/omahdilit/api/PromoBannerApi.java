package com.app.omahdilit.api;

import com.app.omahdilit.response.PromoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PromoBannerApi {
    @GET("promobanner")
    Call<PromoResponse> getPromoBanner();
}
