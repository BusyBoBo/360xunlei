package com.czg.xunlei.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseViewHolder;
import com.czg.xunlei.model.CarToonModel;
import com.czg.xunlei.utils.ImageLoader;
import com.czg.xunlei.widget.RatioImageView;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：CartoonListViewHolder.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CartoonListViewHolder extends BaseViewHolder<CarToonModel.CarToonBean> {

    @Bind(R.id.tv_image)
    RatioImageView mTvImage;
    @Bind(R.id.tv_title)
    TextView mTvTitle;


    public CartoonListViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
        mTvImage.setRatio(150f/185f);
    }

    @Override
    public void setData(CarToonModel.CarToonBean data) {
        ImageLoader.setImage(mTvImage,data.getImage());
        mTvTitle.setText(data.getTitle());
    }
}
