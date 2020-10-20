package com.app.omahdilit.api;

import com.app.omahdilit.response.CekTransaksiResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CekTransaksiApi {

    @POST("cekorder")
    Call<CekTransaksiResponse> cekTransaksi(@Query("idcustomer") String idcustomer);
}
