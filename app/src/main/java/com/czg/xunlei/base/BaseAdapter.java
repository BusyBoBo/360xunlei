package com.czg.xunlei.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：czg
 * @class ：BaseAdapter.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private final List<T> data = new ArrayList<>();
    protected Context context;
    protected Class<? extends BaseViewHolder<T>> clazz;
    protected int layoutId;

    public BaseAdapter(Context context, Class<? extends BaseViewHolder<T>> clazz, int layoutId) {
        this.context = context;
        this.clazz = clazz;
        this.layoutId = layoutId;
    }

    public void setData(List<T> data) {
        this.data.clear();
        this.addData(data);
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            Constructor<? extends BaseViewHolder<T>> constructor = clazz.getConstructor(ViewGroup.class, int.class);
            final BaseViewHolder<T> baseViewHolder = constructor.newInstance(parent, layoutId);
            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, baseViewHolder.getAdapterPosition());
                    }

                }
            });
            baseViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(v, baseViewHolder.getAdapterPosition());
                    }
                    return false;
                }
            });

            return baseViewHolder;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    ;

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
