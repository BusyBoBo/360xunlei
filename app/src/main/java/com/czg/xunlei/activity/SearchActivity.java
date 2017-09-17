package com.czg.xunlei.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.SearchRequest;
import com.czg.xunlei.model.XunLeiModel;
import com.czg.xunlei.viewholder.SearchViewHolder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

import static com.czg.xunlei.R.id.btn_search;
import static com.czg.xunlei.R.id.ed_search;

public class SearchActivity extends BaseActivity implements XRecyclerView.LoadingListener, RadioGroup.OnCheckedChangeListener, OnItemClickListener {
    @Bind(ed_search)
    EditText edSearch;
    @Bind(btn_search)
    Button btnSearch;
    @Bind(R.id.radio)
    RadioGroup radio;
    @Bind(R.id.rec)
    XRecyclerView rec;

    private OkHttpClient mOkHttpClient;
    private BaseAdapter<XunLeiModel> mSearchAdapter;
    private String mSearch = "";

    @Override
    protected void initData() {
        mSearch = getIntent().getStringExtra("SEARCH");
        if (mSearch != null) {
            edSearch.setText(mSearch);
            loadData();
        }
    }

    @Override
    protected void initView() {
        radio.setOnCheckedChangeListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rec.setLayoutManager(layoutManager);
        rec.setLoadingListener(this);
        mSearchAdapter = new BaseAdapter(this, SearchViewHolder.class, R.layout.item_search);
        mSearchAdapter.setOnItemClickListener(this);
        mOkHttpClient = new OkHttpClient.Builder().build();
        rec.setAdapter(mSearchAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


    @Override
    public void onRefresh() {
        loadData();
    }

    private int page = 1;
    private int type = 1;

    public void loadData() {
        if (TextUtils.isEmpty(mSearch)) return;
        page = 1;
        HttpClient.getInstances().send(new SearchRequest(mSearch, page, type), lodaDataCallBack);
        showLoadingView();
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

    @OnClick(R.id.btn_search)
    public void onClick(View v) {
        mSearch = edSearch.getText().toString();
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
            if (response.isEmpty()) {
                rec.setEmptyView(LayoutInflater.from(SearchActivity.this).inflate(R.layout.empty_thumb, null));
                return;
            }
            mSearchAdapter.setData(response);
            rec.refreshComplete();
            rec.setNoMore(false);
            showDataView();

        }

        @Override
        public void onFail(Throwable throwable) {
           showErrorView();
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
            if (TextUtils.isEmpty(mSearch)) {
                edSearch.setText(systemService.getText());
            }

        }
    }


}