package com.czg.xunlei.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.base.OnItemClickListener;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.client.HttpClient;
import com.czg.xunlei.http.request.DetailRequest;
import com.czg.xunlei.http.request.SearchRequest;
import com.czg.xunlei.model.CastModel;
import com.czg.xunlei.model.TypeModel;
import com.czg.xunlei.model.VideoDetailModel;
import com.czg.xunlei.model.XunLeiModel;
import com.czg.xunlei.utils.ImageLoader;
import com.czg.xunlei.viewholder.SearchViewHolder;
import com.czg.xunlei.widget.FlowLayout;
import com.czg.xunlei.widget.RatioImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements OnItemClickListener {

    @Bind(R.id.image)
    RatioImageView image;
    @Bind(R.id.tittle)
    TextView tittle;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.length)
    TextView length;
    @Bind(R.id.marker)
    TextView marker;
    @Bind(R.id.label)
    TextView label;
    @Bind(R.id.director)
    TextView director;
    @Bind(R.id.type_text)
    TextView typeText;
    @Bind(R.id.type)
    FlowLayout type;
    @Bind(R.id.cast_text)
    TextView castText;
    @Bind(R.id.cast)
    FlowLayout cast;

    @Bind(R.id.recyclerView)
     RecyclerView recyclerView;
    private VideoDetailModel mDetailModel;

    @Override
    protected void initData() {
        String ID = getIntent().getStringExtra("ID");
        showLoadingView();
        sendHttp(new DetailRequest(ID), new CallBack<VideoDetailModel>() {
            @Override
            public void onSuccess(VideoDetailModel response) {
                if (response != null) {
                    loadData(response);
                }
                showDataView();
            }

            @Override
            public void onFail(Throwable throwable) {
                Toast.makeText(DetailActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                showErrorView();
            }
        });

    }

    private void loadData(VideoDetailModel response) {
        mDetailModel = response;
        initSearch(response.getSearchId());
        setTitle(response.getSearchId());
        image.setRatio(response.getRatio());
        ImageLoader.setImage(image, response.getImage());
        tittle.setText("标题：" + response.getTitle());
        date.setText("日期：" + response.getDate());
        length.setText("时长：" + response.getLength() + "分钟");
        marker.setText("制作商：" + (response.getMaker() != null ? response.getMaker().getName() : ""));
        label.setText("发行商：" + (response.getLabel() != null ? response.getLabel().getName() : ""));
        director.setText("作者：" + response.getDirector().getName());
        typeText.setText("类型：");
        for (final TypeModel model : response.getTypes()) {
            View flow_type_view = LayoutInflater.from(this).inflate(R.layout.item_flow_text, type, false);
            TextView tv_item_flow = (TextView) flow_type_view.findViewById(R.id.item_flow);
            tv_item_flow.setText(model.getName());
            type.addView(flow_type_view);
            flow_type_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThumbActivity.startThumbActivity(DetailActivity.this, model.getApi(), model.getName());
                }
            });

        }
        castText.setText("演员：");
        for (final CastModel model : response.getCasts()) {
            View flow_type_view = LayoutInflater.from(this).inflate(R.layout.item_flow_text, type, false);
            TextView tv_item_flow = (TextView) flow_type_view.findViewById(R.id.item_flow);
            tv_item_flow.setText(model.getName());
            if (!TextUtils.isEmpty(model.getName())) {
                cast.addView(flow_type_view);
            }
            flow_type_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThumbActivity.startThumbActivity(DetailActivity.this, model.getApi(), "演员：" + model.getName());
                }
            });


        }


    }

    private void initSearch(String searchId) {
        HttpClient.getInstances().send(new SearchRequest(searchId, 1, 1), new CallBack<List<XunLeiModel>>() {
            @Override
            public void onSuccess(List<XunLeiModel> response) {
                if (response.isEmpty()) {
                    Toast.makeText(DetailActivity.this, "暂无资源！", Toast.LENGTH_SHORT).show();
                } else {
                    mSearchAdapter.setData(response);

                }
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });

    }

    private BaseAdapter<XunLeiModel> mSearchAdapter;

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mSearchAdapter = new BaseAdapter(this, SearchViewHolder.class, R.layout.item_search);
        mSearchAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mSearchAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }


    @OnClick({R.id.image, R.id.tittle, R.id.marker, R.id.label, R.id.director})
    public void onViewClicked(View view) {
        if (mDetailModel == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.image:
                ImageDetailActivity.startImageDetailActivity(this, mDetailModel.getImage());
                break;
            case R.id.tittle:
                startActivity(new Intent(this, SearchActivity.class).putExtra("SEARCH", mDetailModel.getSearchId()));
                break;
            case R.id.marker:
                ThumbActivity.startThumbActivity(this, mDetailModel.getMaker().getApi(), "制作商：" + mDetailModel.getMaker().getName());
                break;
            case R.id.label:
                ThumbActivity.startThumbActivity(this, mDetailModel.getLabel().getApi(), "发行商：" + mDetailModel.getLabel().getName());
                break;
            case R.id.director:
                ThumbActivity.startThumbActivity(this, mDetailModel.getDirector().getApi(), "作者：" + mDetailModel.getDirector().getName());

                break;

        }
    }

    @Override
    public void onItemClick(View view, int position) {
        XunLeiModel xunLeiModel = mSearchAdapter.getItem(position);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(xunLeiModel.getUrl());
        intent.setData(content_url);
        startActivity(intent);
    }
}
