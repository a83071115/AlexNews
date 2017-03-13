package com.alex.alexnews.home.adapter;

import com.alex.alexnews.common.BaseHolder;
import com.alex.alexnews.common.MyBaseAdapter;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.home.viewholder.RecommendGridViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */

public class RecommendAdapter  extends MyBaseAdapter<ResuleBeanData.ResultBean.RecommendInfoBean>{

    public RecommendAdapter(List<ResuleBeanData.ResultBean.RecommendInfoBean> list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new RecommendGridViewHolder();
    }
}
