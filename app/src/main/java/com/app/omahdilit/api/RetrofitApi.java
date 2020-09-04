package com.app.omahdilit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private static final String base_URL = "http://omahdilit.my.id/public/api/";

    public RetrofitApi(){
    }

    private static Retrofit getRetrofitClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return  new Retrofit.Builder().baseUrl(base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static PromoApi getApiPromo(){
        return getRetrofitClient().create(PromoApi.class);
    }

    public static ModelApi getApiModel(){return getRetrofitClient().create(ModelApi.class);}

    public static PromoBannerApi getApiPromoBaner(){return getRetrofitClient().create(PromoBannerApi.class);}

    public static DetailPromoApi getDetailPromo(){return  getRetrofitClient().create(DetailPromoApi.class);}

    public static LoginApi getLogin(){return getRetrofitClient().create(LoginApi.class);}

    public static RegisterApi register(){return  getRetrofitClient().create(RegisterApi.class);}
}
