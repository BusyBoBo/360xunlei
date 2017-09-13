package com.czg.xunlei.search;

import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.base.SearchViewHolder;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.SearchRequest;
import com.czg.xunlei.model.XunLeiModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import okhttp3.OkHttpClient;

public class SearchActivity extends AppCompatActivity implements XRecyclerView.LoadingListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener, OnItemClickListener {
    private EditText ed_search;
    private XRecyclerView rec;
    private Button btn_search;

    private OkHttpClient mOkHttpClient;
    private BaseAdapter<XunLeiModel> mSearchAdapter;
    private RadioGroup radio;
    private String mSearch = "";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ed_search = (EditText) findViewById(R.id.ed_search);
        rec = (XRecyclerView) findViewById(R.id.rec);
        radio = (RadioGroup) findViewById(R.id.radio);
        btn_search = (Button) findViewById(R.id.btn_search);
        radio.setOnCheckedChangeListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rec.setLayoutManager(layoutManager);
        rec.setLoadingListener(this);
        mSearchAdapter = new BaseAdapter(this, SearchViewHolder.class, R.layout.item_search);
        mSearchAdapter.setOnItemClickListener(this);
        mOkHttpClient = new OkHttpClient.Builder().build();
        btn_search.setOnClickListener(this);
        rec.setAdapter(mSearchAdapter);
    }


    @Override
    public void onRefresh() {
        loadData();
    }

    private int page = 1;
    private int type = 1;

    public void loadData() {
        if (TextUtils.isEmpty(mSearch)) return;
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("loading...");
        }
        mProgressDialog.show();

        page = 1;
        HttpClient.getInstances().send(new SearchRequest(mSearch, page, type), lodaDataCallBack);
    }

    public void loadMoreData() {
        if (TextUtils.isEmpty(mSearch)) return;
        HttpClient.getInstances().send(new SearchRequest(mSearch, page, type), lodaMoreDataCallBack);
    }

    @Override
    public void onLoadMore() {
        page++;
        loadMoreData();
    }

    @Override
    public void onClick(View v) {
        mSearch = ed_search.getText().toString();
        if (!TextUtils.isEmpty(mSearch)) {
            loadData();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_rele:
                type = 4;
                loadData();
                break;
            case R.id.rb_size:
                type = 3;
                loadData();
                break;
            case R.id.rb_time:
                type = 2;
                loadData();
                break;
            case R.id.rb_defult:
                type = 1;
                loadData();
                break;
        }
    }

    private CallBack<List<XunLeiModel>> lodaDataCallBack = new CallBack<List<XunLeiModel>>() {
        @Override

        public void onSuccess(List<XunLeiModel> response) {
            mSearchAdapter.setData(response);
            rec.refreshComplete();
            rec.setNoMore(false);
            mProgressDialog.dismiss();

        }

        @Override
        public void onFail(Throwable throwable) {
            mProgressDialog.dismiss();
        }
    };
    private CallBack<List<XunLeiModel>> lodaMoreDataCallBack = new CallBack<List<XunLeiModel>>() {
        @Override

        public void onSuccess(List<XunLeiModel> response) {
            if (response.isEmpty()) {
                rec.setNoMore(true);
                return;
            }
            mSearchAdapter.addData(response);
            rec.loadMoreComplete();
        }

        @Override
        public void onFail(Throwable throwable) {

        }
    };

    @Override
    public void onItemClick(View view, int position) {
        XunLeiModel xunLeiModel = mSearchAdapter.getItem(position);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(xunLeiModel.getUrl());
        intent.setData(content_url);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClipboardManager systemService = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (systemService != null && systemService.getText() != null) {
            ed_search.setText(systemService.getText());
            loadData();
        }
    }
}