package com.app.omahdilit.api;

import com.app.omahdilit.response.CustomerResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerApi {
    @POST("getcustomer")
    Call<CustomerResponse> getCustomer(@Query("uid") String uid);
}
