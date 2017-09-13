package com.czg.xunlei.base;

import android.view.ViewGroup;
import android.widget.TextView;

import com.czg.xunlei.R;
import com.czg.xunlei.model.XunLeiModel;

/**
 * @author ：czg
 * @class ：SearchViewHolder.class
 * @date ：2017/9/12.
 * @describe ：TODO(input describe)
 */
public class SearchViewHolder extends BaseViewHolder<XunLeiModel> {
    private final TextView mTv_tittle;
    private final TextView mSubtitles;
    private final TextView mType;
    private final TextView mSize;
    private final TextView mDownloadCount;
    private final TextView mCreateTime;

    public SearchViewHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
        mTv_tittle = (TextView) itemView.findViewById(R.id.tv_tittle);
        mSubtitles = (TextView) itemView.findViewById(R.id.subtitles);
        mType = (TextView) itemView.findViewById(R.id.type);
        mSize = (TextView) itemView.findViewById(R.id.size);
        mDownloadCount = (TextView) itemView.findViewById(R.id.downloadCount);
        mCreateTime = (TextView) itemView.findViewById(R.id.createTime);
    }

    @Override
    public void setData(XunLeiModel data) {
        mTv_tittle.setText("标题：\n" + data.getTittle());
        mType.setText("类型:" + data.getType());
        mSubtitles.setText("包含文件:" + data.getType());

        mDownloadCount.setText("下载量:" + data.getDownloadCount());
        mCreateTime.setText("时间:" + data.getCreateTime());
        mSize.setText("大小:" + data.getSize());
    }


}
