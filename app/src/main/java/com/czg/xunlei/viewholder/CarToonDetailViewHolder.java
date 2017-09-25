package com.czg.xunlei.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseViewHolder;
import com.czg.xunlei.utils.ImageLoader;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * @author ：czg
 * @class ：DetailViewPager.class
 * @date ：2017/9/22.
 * @describe ：TODO(input describe)
 */
public class CarToonDetailViewHolder extends BaseViewHolder<String>{
    @Bind(R.id.sk_image)
    PhotoView sketchImageView;
    @Bind(R.id.progress_wheel)
    ProgressWheel progress_wheel;
    @Bind(R.id.tv_progress)
    TextView tv_progress;

    public CarToonDetailViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }
    @Override
    public void setData(String data) {
        ImageLoader.setImage(sketchImageView,data,progress_wheel,tv_progress);

    }
}
