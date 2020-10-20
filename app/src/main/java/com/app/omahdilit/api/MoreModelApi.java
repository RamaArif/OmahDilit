package com.app.omahdilit.api;

import com.app.omahdilit.response.ModelItem;
import com.app.omahdilit.response.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoreModelApi {
    @GET("moremodel")
    Call<ModelResponse> getMoreModel();
}
