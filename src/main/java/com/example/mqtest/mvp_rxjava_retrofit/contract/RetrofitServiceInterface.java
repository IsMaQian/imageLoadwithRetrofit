package com.example.mqtest.mvp_rxjava_retrofit.contract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/3/5.
 */

public interface RetrofitServiceInterface {
    String BASE_URL = "http://192.168.1.211:8080/picture/s0.png";

    @GET("picture/{ip_address}")
    Observable<ResponseBody> getPicturename(@Path("ip_address") String name);
    //Call<ResponseBody> getPicturename(@Path("ip_address") String name);

}

