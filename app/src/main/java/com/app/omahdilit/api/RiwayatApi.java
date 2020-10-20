package com.app.omahdilit.api;

import com.app.omahdilit.response.RiwayatResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RiwayatApi {
    @POST("listriwayat")
    Call<RiwayatResponse> getRiwayatList(@Query("idcustomer") String idcustomer);
}
