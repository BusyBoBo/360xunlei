package com.czg.xunlei.activity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.DetailRequest;
import com.czg.xunlei.model.CastModel;
import com.czg.xunlei.model.TypeModel;
import com.czg.xunlei.model.VideoDetailModel;
import com.czg.xunlei.utils.ImageLoader;
import com.czg.xunlei.widget.FlowLayout;
import com.czg.xunlei.widget.RatioImageView;

import butterknife.Bind;

public class DetailActivity extends BaseActivity {

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

    @Override
    protected void initData() {
        String ID = getIntent().getStringExtra("ID");
        sendHttp(new DetailRequest(ID), new CallBack<VideoDetailModel>() {
            @Override
            public void onSuccess(VideoDetailModel response) {
                if (response != null) {
                    loadData(response);
                }

            }

            @Override
            public void onFail(Throwable throwable) {
                Toast.makeText(DetailActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData(VideoDetailModel response) {
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
        for (TypeModel model : response.getTypes()) {
            View flow_type_view = LayoutInflater.from(this).inflate(R.layout.item_flow_text, type, false);
            TextView tv_item_flow = (TextView) flow_type_view.findViewById(R.id.item_flow);
            tv_item_flow.setText(model.getName());
            type.addView(flow_type_view);

        }
        castText.setText("演员：");
        for (CastModel model : response.getCasts()) {
            View flow_type_view = LayoutInflater.from(this).inflate(R.layout.item_flow_text, type, false);
            TextView tv_item_flow = (TextView) flow_type_view.findViewById(R.id.item_flow);
            tv_item_flow.setText(model.getName());
            if (!TextUtils.isEmpty(model.getName())) {
                cast.addView(flow_type_view);
            }


        }

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }


}
