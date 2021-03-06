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
 * Created by Administrator on 2017/3/12.
 */

public class RecommendGridViewHolder extends BaseHolder<ResuleBeanData.ResultBean.RecommendInfoBean> {
    @Bind(R.id.iv_recommend)
    ImageView mIvRecommend;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_price)
    TextView mTvPrice;

    @Override
    protected View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_recommend_grid_view, null);
    }

    @Override
    protected void refreshData() {
        ResuleBeanData.ResultBean.RecommendInfoBean data = this.getData();
        mTvName.setText(data.getName());
        mTvPrice.setText(data.getCover_price());
        Picasso.with(UIUtils.getContext()).load(AppNetConfig.IMAGE_URL+data.getFigure()).into(mIvRecommend);

    }
}
