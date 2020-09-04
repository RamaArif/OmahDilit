package com.app.omahdilit.api;

import com.app.omahdilit.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterApi {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("daftaruser")
    Call<BaseResponse> register(
            @Field("name") String name,
            @Field("addres") String addres,
            @Field("number") String number,
            @Field("email") String email,
            @Field("uid") String uid,
            @Field("photo") String photo,
            @Field("pushtoken") String pushtoken);
}
