package com.alex.alexnews.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.alexnews.R;
import com.alex.alexnews.common.AppNetConfig;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {
    private final List<ResuleBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private Context mContext;
    private ResuleBeanData.ResultBean.SeckillInfoBean.ListBean listBean;


    public SeckillRecyclerViewAdapter(Context context, List<ResuleBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1根据位置得到对应的数据
        listBean = list.get(position);
        //2绑定数据
        Picasso.with(mContext).load(AppNetConfig.IMAGE_URL+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIUtils.toast("秒杀position"+getLayoutPosition(),false);
                    if(mOnSeckillRecyclerView!=null){
                        mOnSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    public interface OnSeckillRecyclerView{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        void onItemClick(int position);
    }
    private OnSeckillRecyclerView mOnSeckillRecyclerView;

    /**
     * 设置item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        mOnSeckillRecyclerView = onSeckillRecyclerView;
    }
}
