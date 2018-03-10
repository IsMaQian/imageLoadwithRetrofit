package com.example.mqtest.mvp_rxjava_retrofit.model;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

import static com.example.mqtest.mvp_rxjava_retrofit.view.MainActivity.APP_IMAGE_DIR;

/**
 * Created by Administrator on 2018/3/10.
 */

public class ModelDateSource {
    private volatile static ModelDateSource modelDateSource = null;

    private static final String TAG = "tesss";

    private ModelDateSource() {
    }

    public static ModelDateSource ins() {
        if (modelDateSource == null) {
            synchronized (ModelDateSource.class) {
                if (modelDateSource == null) {
                    modelDateSource = new ModelDateSource();
                }
            }
        }
        return modelDateSource;
    }

    //下载图片
    public boolean saveFileToDisc(String imagename, ResponseBody body) {
        if (body == null) {
            Log.d(TAG, "saveFileToDisc: 图片源错误");
            return false;
        }
        long downloadLength = 0;
        long contentLength = body.contentLength();
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        InputStream is = body.byteStream();
        try {
            if (is == null) {
                return false;
            }
            File fileDir = new File(APP_IMAGE_DIR);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            File file = new File(APP_IMAGE_DIR, imagename);
            if (file.exists()) {
                downloadLength = file.length();
            }
            if (downloadLength == contentLength) {
                return true;
            }
            fos = new FileOutputStream(file);
            bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024 * 5];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
