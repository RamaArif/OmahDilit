package com.app.omahdilit.api;

import com.app.omahdilit.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @POST("logingoogle?email=")
    Call<LoginResponse> getLogin(@Query("email") String emailGoogle);
}
