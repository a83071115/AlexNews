package com.alex.alexnews.home.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.alexnews.R;
import com.alex.alexnews.common.AppNetConfig;
import com.alex.alexnews.common.BaseHolder;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.utils.UIUtils;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ChannelViewHolder extends BaseHolder<ResuleBeanData.ResultBean.ChannelInfoBean> {
    @Bind(R.id.iv_channel)
    ImageView mIvChannel;
    @Bind(R.id.tv_channel)
    TextView mTvChannel;

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_channel, null);
    }

    @Override
    protected void refreshData() {
        ResuleBeanData.ResultBean.ChannelInfoBean data = this.getData();
        Picasso.with(UIUtils.getContext()).load(AppNetConfig.IMAGE_URL+data.getImage()).into(mIvChannel);
        mTvChannel.setText(data.getChannel_name());
    }
}
