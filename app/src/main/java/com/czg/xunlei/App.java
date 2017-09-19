package com.czg.xunlei;

import android.app.Application;

import com.czg.xunlei.http.client.HttpClient;

/**
 * @author ：czg
 * @class ：App.class
 * @date ：2017/9/15.
 * @describe ：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient.getInstances().init(this);
    }
}
