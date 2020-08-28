package com.app.omahdilit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static final String base_URL = "http://omahdilit.my.id/public/api/";

    public RetrofitApi(){
    }

    private static Retrofit getRetrofitClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder().baseUrl(base_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static PromoApi getApiPromo(){
        return getRetrofitClient().create(PromoApi.class);
    }

    public static ModelApi getApiModel(){return getRetrofitClient().create(ModelApi.class);}
}
