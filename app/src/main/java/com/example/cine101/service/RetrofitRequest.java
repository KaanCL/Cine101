package com.example.cine101.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.cine101.util.Credentials.BASE_URL_TMBD;
import static com.example.cine101.util.Credentials.BASE_URL_YOUTUBE;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RetrofitRequest {

    private static Retrofit retrofit_tmbd , retrofit_youtube;


    public static Retrofit getRetrofitInstance(){

        Gson gson = new GsonBuilder().setLenient().create();

        if(retrofit_tmbd == null){

            retrofit_tmbd = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TMBD)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    return retrofit_tmbd;
    }
  public static Retrofit getRetrofitInstance2() {
        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit_youtube == null) {
            retrofit_youtube = new Retrofit.Builder()
                    .baseUrl(BASE_URL_YOUTUBE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit_youtube;
    }


}
