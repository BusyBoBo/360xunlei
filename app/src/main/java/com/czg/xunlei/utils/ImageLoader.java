package com.czg.xunlei.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by 78101 on 2017/9/13.
 */

public class ImageLoader {
    public static void setImage(ImageView imageView,String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
