package com.czg.xunlei.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseActivity;

import me.xiaopan.sketch.SketchImageView;

public class ImageDetailActivity extends BaseActivity {
    SketchImageView image;
    public static void startImageDetailActivity(Context context, String url) {
        context.startActivity(new Intent(context, ImageDetailActivity.class).putExtra("url", url));
    }

    @Override
    public void setContentView(View view) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.setContentView(view);
    }

    @Override
    protected void initData() {
        image.displayImage(getIntent().getStringExtra("url"));
    }

    @Override
    protected void initView() {
        image = (SketchImageView) findViewById(R.id.image);
        image.setZoomEnabled(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    protected boolean isHaveToolBar() {
        return false;
    }
}
