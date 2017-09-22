package com.czg.xunlei.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.czg.xunlei.R;
import com.czg.xunlei.adapter.CarToonDetailViewHolder;
import com.czg.xunlei.base.BaseActivity;
import com.czg.xunlei.base.BaseAdapter;
import com.czg.xunlei.http.callback.CallBack;
import com.czg.xunlei.http.request.CarToonDetailRequest;
import com.czg.xunlei.model.CarToonDetail;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import butterknife.Bind;

public class CarToonDetailActivity extends BaseActivity implements RecyclerViewPager.OnPageChangedListener {

    @Bind(R.id.vp_cartoon_detail)
    RecyclerViewPager vp_cartoon_detail;

    @Bind(R.id.tv_count)
     TextView tv_count;
    private String mApi;
    private BaseAdapter<String> mDetailViewPager;

    public static void startCarToonDetailActivity(Context context, String api) {
        context.startActivity(new Intent(context, CarToonDetailActivity.class).putExtra("api", api));
    }

    @Override
    public void setContentView(View view) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.setContentView(view);
    }

    @Override
    protected void initData() {
        mApi = getIntent().getStringExtra("api");
        sendHttp(new CarToonDetailRequest(mApi), new CallBack<CarToonDetail>() {
            @Override
            public void onSuccess(CarToonDetail response) {
                if (response != null) {
                    parseUrl(response);
                }

            }


            @Override
            public void onFail(Throwable throwable) {
                Toast.makeText(CarToonDetailActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseUrl(CarToonDetail response) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < response.getPage(); i++) {
            String url = response.getUrl() + (i + 1) + ".jpg";
            list.add(url);

        }
        tv_count.setText(1 + "/" + list.size());
        mDetailViewPager.setData(list);
    }

    @Override
    protected void initView() {
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        vp_cartoon_detail.setLayoutManager(layout);
        mDetailViewPager = new BaseAdapter<String>(this, CarToonDetailViewHolder.class, R.layout.adapter_item);
        vp_cartoon_detail.setAdapter(mDetailViewPager);
        vp_cartoon_detail.addOnPageChangedListener(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_toon;
    }

    @Override
    protected boolean isHaveToolBar() {
        return false;
    }

    @Override
    public void OnPageChanged(int i, int index) {
        tv_count.setText((index + 1) + "/" + mDetailViewPager.getItemCount());
    }
}
