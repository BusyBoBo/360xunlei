package com.czg.xunlei.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 78101 on 2017/9/13.
 */

@SuppressLint("AppCompatCustomView")
public class RatioImageView extends ImageView {
    private float ratio;
    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽度的模式和尺寸
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        float height = widthSize / ratio;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) height, MeasureSpec.EXACTLY);
        //必须调用下面的两个方法之一完成onMeasure方法的重写，否则会报错
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
        measure(0,0);
    }
}
