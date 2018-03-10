package com.example.mqtest.mvp_rxjava_retrofit.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mqtest.mvp_rxjava_retrofit.R;
import com.example.mqtest.mvp_rxjava_retrofit.contract.IContract;
import com.example.mqtest.mvp_rxjava_retrofit.contract.RetrofitServiceInterface;
import com.example.mqtest.mvp_rxjava_retrofit.model.Retrofitdownload;
import com.example.mqtest.mvp_rxjava_retrofit.presenter.PresenterCompel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements IContract.IView {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_image)
    Button btnImage;

    public static String APP_IMAGE_DIR;
    IContract.IPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PresenterCompel(this);
        APP_IMAGE_DIR = this.getExternalCacheDir().getPath() + File.separator;
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getImage();
            }
        });
    }


    @Override
    public void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);

    }
}
