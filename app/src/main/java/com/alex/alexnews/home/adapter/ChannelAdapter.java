package com.alex.alexnews.home.adapter;

import com.alex.alexnews.common.BaseHolder;
import com.alex.alexnews.common.MyBaseAdapter;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.home.viewholder.ChannelViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ChannelAdapter extends MyBaseAdapter<ResuleBeanData.ResultBean.ChannelInfoBean> {

    public ChannelAdapter(List<ResuleBeanData.ResultBean.ChannelInfoBean> list) {
        super(list);
    }

    @Override
    protected BaseHolder<ResuleBeanData.ResultBean.ChannelInfoBean> getHolder() {
        return new ChannelViewHolder();
    }
}
