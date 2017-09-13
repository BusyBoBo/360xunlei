package com.czg.xunlei.base;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ：czg
 * @class ：BaseViewHolder.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup viewGroup, int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
    }

    public abstract void setData(M data);

    public int getLayoutId() {
        return 0;
    }

    ;

    public final <T extends View> T findViewById(@IdRes int id) {
        if (id < 0) {
            return null;
        }
        return (T) itemView.findViewById(id);
    }

}
