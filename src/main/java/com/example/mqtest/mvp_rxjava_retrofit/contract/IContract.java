package com.example.mqtest.mvp_rxjava_retrofit.contract;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/3/10.
 */

public interface IContract {
    interface IView {
        void showImage(Bitmap bitmap);

    }

    interface IPresenter {
        void getImage();
    }

}
