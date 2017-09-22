package com.czg.xunlei.utils;

import android.widget.ImageView;
import android.widget.TextView;

import com.czg.xunlei.R;
import com.czg.xunlei.utils.image.AlxPicassoUtils;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Picasso;

/**
 * Created by 78101 on 2017/9/13.
 */

public class ImageLoader {
    public static void setImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext()).load(url).placeholder(R.drawable.img_seat).into(imageView);

    }

    public static void setImage(ImageView imageView, String url, ProgressWheel progressWheel, TextView textView) {
        AlxPicassoUtils.displayImageProgress(url, imageView, progressWheel, textView);
    }
}
