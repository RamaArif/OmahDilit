package com.app.omahdilit.api;

import com.app.omahdilit.response.JarakResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JarakTukangApi {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("jarakharga")
    Call<JarakResponse> getJarakTukang(@Field("lat1") Double lat1,
                                       @Field("long1") Double long1,
                                       @Field("lat2") Double lat2,
                                       @Field("long2") Double long2);
}
