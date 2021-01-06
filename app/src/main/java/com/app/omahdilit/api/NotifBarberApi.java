package com.app.omahdilit.api;

import com.app.omahdilit.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NotifBarberApi {
    @POST("notifbarber")
    Call<BaseResponse> sendNotif(@Query("id_transaksi") Integer kode_transaksi);
}
