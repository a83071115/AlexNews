package com.alex.alexnews.home.adapter;

import com.alex.alexnews.common.BaseHolder;
import com.alex.alexnews.common.MyBaseAdapter;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.home.viewholder.HotGridViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */

public class HotGirdViewAdapter extends MyBaseAdapter<ResuleBeanData.ResultBean.HotInfoBean>{

    public HotGirdViewAdapter(List<ResuleBeanData.ResultBean.HotInfoBean> list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new HotGridViewHolder();
    }
}
