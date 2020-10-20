package com.app.omahdilit.api;

import com.app.omahdilit.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("logingoogle")
    Call<BaseResponse> getLogin(@Query("email") String email);

}
