package com.example.mqtest.mvp_rxjava_retrofit.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.mqtest.mvp_rxjava_retrofit.contract.IContract;
import com.example.mqtest.mvp_rxjava_retrofit.model.ModelDateSource;
import com.example.mqtest.mvp_rxjava_retrofit.model.Retrofitdownload;

import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;

import static com.example.mqtest.mvp_rxjava_retrofit.view.MainActivity.APP_IMAGE_DIR;


/**
 * Created by Administrator on 2018/3/10.
 */

public class PresenterCompel implements IContract.IPresenter {
    IContract.IView view;
    Retrofitdownload retrofitdownload = new Retrofitdownload();
    private static final String TAG = "tesss";

    public PresenterCompel(IContract.IView view) {
        this.view = view;
    }

    @Override
    public void getImage() {
        retrofitdownload.getService().getPicturename("s0.png")
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, Bitmap>() {

                    @Override
                    public Bitmap apply(ResponseBody body) throws Exception {
                        Bitmap bm = null;
                        String imageName = "test.png";
                        if (body != null) {
                            boolean isSave = ModelDateSource.ins().saveFileToDisc(imageName, body);
                            if (isSave) {
                                bm = BitmapFactory.decodeFile(APP_IMAGE_DIR + imageName);
                            }
                        }
                        return bm;
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<Bitmap>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super Bitmap> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        view.showImage(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
