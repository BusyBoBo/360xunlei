package com.czg.xunlei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.ResourceRequest;
import com.czg.xunlei.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private EditText ed_search;
    private RecyclerView rec;
    private Button btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpClient.getInstances().send(new ResourceRequest(), new CallBack<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtils.xml(response);
            }

            @Override
            public void onFail(Throwable throwable) {
                LogUtils.e(throwable);
                throwable.printStackTrace();
            }
        });


    }


}
