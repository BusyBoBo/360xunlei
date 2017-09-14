package com.czg.xunlei.http.client;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.ApiRequest;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * @author ：czg
 * @class ：HttpClient.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class HttpClient {
    private static HttpClient instances = new HttpClient();
    private static Handler uiHandler = new Handler(Looper.getMainLooper());
    private  OkHttpClient mOkHttpClient;

    public static HttpClient getInstances() {
        return instances;
    }

    private HttpClient() {
    }

    public void init(Context context){
        mOkHttpClient = new OkHttpClient
                .Builder()
                .cache(new Cache(new File(context.getCacheDir(), "okhttpcache"), 10 * 1024 * 1024)).build();
    }

    public <T> void send(final ApiRequest<T> request, final CallBack<T> callBack) {
        mOkHttpClient.newCall(request.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                uiHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        callBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                if (response.isSuccessful()) {
                    com.czg.xunlei.http.response.Response<T> requestResponse = request.getResponse(response.body());
                    final T body = requestResponse.getBody();
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(body);
                        }
                    });
                } else {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFail(new RuntimeException("http error " + response.code()));
                        }
                    });
                }
            }
        });
    }
}
