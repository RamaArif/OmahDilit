package com.app.omahdilit.api;

import com.app.omahdilit.response.BaseResponse;
import com.app.omahdilit.response.TransaksiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransaksiApi {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("transaksi")
    Call<TransaksiResponse> createTransaksi(@Field("tukang") Integer tukang,
                                            @Field("customer") String customer,
                                            @Field("model") Integer model,
                                            @Field("totalharga") Integer totalharga,
                                            @Field("promo") Integer promo,
                                            @Field("alamat") String alamat,
                                            @Field("jarak") Integer jarak,
                                            @Field("lat") Double lat,
                                            @Field("lng") Double lng);
}
