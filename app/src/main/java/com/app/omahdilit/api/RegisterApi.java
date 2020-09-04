package com.app.omahdilit.api;

import com.app.omahdilit.response.LoginResponse;
import com.app.omahdilit.response.RegisterRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterApi {

    @FormUrlEncoded
    @POST("daftaruser")
    Call<LoginResponse> register(@Body RegisterRequestBody request);
//            @Field("name") String name,
//            @Field("addres") String address,
//            @Field("number") String phone,
//            @Field("email") String email,
//            @Field("uid") String uid,
//            @Field("photo") String photo,
//            @Field("pushtoken") String pushtoken);
}
