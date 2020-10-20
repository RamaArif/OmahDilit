package com.app.omahdilit.api;

import com.app.omahdilit.response.DetailTransaksiResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DetailTransaksiApi {
    @POST("detailtransaksi")
    Call<DetailTransaksiResponse> getDetailTransaksi(@Query("codetransaksi") String codetransaksi);
}
