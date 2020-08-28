package com.app.omahdilit.api;

import com.app.omahdilit.response.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelApi {
    @GET("modelrambut")
    Call<ModelResponse> getModel();
}
