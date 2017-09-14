package com.czg.xunlei.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.czg.xunlei.R;
import com.czg.xunlei.base.BaseViewHolder;
import com.czg.xunlei.model.TypeModel;

import butterknife.Bind;

/**
 * @author ：czg
 * @class ：TypeMenuViewHolder.class
 * @date ：2017/9/14.
 * @describe ：TODO(input describe)
 */
public class TypeMenuViewHolder extends BaseViewHolder<TypeModel> {

    @Bind(R.id.tv_name)
    TextView tv_name;

    public TypeMenuViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }
    @Override
    public void setData(TypeModel data) {
        tv_name.setText(data.getName());
    }
}
