package com.example.mqtest.mvp_rxjava_retrofit.model;

import com.example.mqtest.mvp_rxjava_retrofit.contract.RetrofitServiceInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/3/5.
 */

public class Retrofitdownload {
    RetrofitServiceInterface serviceInterface;
    Retrofit retrofit;
    public Retrofitdownload() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.211:8080/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitServiceInterface getService() {
        serviceInterface = retrofit.create(RetrofitServiceInterface.class);
        return serviceInterface;
    }


}
