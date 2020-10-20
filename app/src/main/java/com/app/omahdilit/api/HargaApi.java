package com.app.omahdilit.api;

import com.app.omahdilit.response.HargaResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface HargaApi {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("hitungharga")
    Call<HargaResponse> hitungHarga(@Field("jarak") Integer jarak,
                                    @Field("idpromo") Integer idPromo);

}
