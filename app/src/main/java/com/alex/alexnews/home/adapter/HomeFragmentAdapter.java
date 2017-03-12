package com.alex.alexnews.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.alexnews.R;
import com.alex.alexnews.common.AppNetConfig;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.utils.UIUtils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {


    private LayoutInflater mLayoutInflater;
    /**
     * 当前的类型
     */
    private int currentType = 0;
    /**
     * banner类型
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀新品
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    private Context mContext;

    private ResuleBeanData.ResultBean resuleBean;


    public HomeFragmentAdapter(Context context, ResuleBeanData.ResultBean bean) {
        this.mContext = context;
        this.resuleBean = bean;
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    /**
     * 相当于getView 创建ViewHolder代码
     * 创建ViewHolder
     *
     * @param parent   父类的容器
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }else if (viewType ==CHANNEL){
            return new CHANNELViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType == ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType == SECKILL){
            return new SeckillViewHolder(mContext,mLayoutInflater.inflate(R.layout.seckill_item,null));
        }
        return null;
    }

    /**
     * 得到类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 相当于getView中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(currentType) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resuleBean.getBanner_info());
        } else if (getItemViewType(currentType) == CHANNEL) {
            CHANNELViewHolder channelViewHolder = (CHANNELViewHolder) holder;
            channelViewHolder.setData(resuleBean.getChannel_info());
        }else if (getItemViewType(currentType) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resuleBean.getAct_info());
        }else if (getItemViewType(currentType) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resuleBean.getSeckill_info());
        }
    }

    /**
     * 总共有多少条item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->2
        return 4;
    }

    /**
     * banner的ViewHolder
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }


        public void setData(final List<ResuleBeanData.ResultBean.BannerInfoBean> data) {
            //设置banner的数据
            ArrayList<String> imagesUrl = new ArrayList<>();
            //得到图片地址并添加到集合中
            for (int i = 0;i<data.size();i++){
                String imageUrl = data.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            Log.e("imageUrl",""+imagesUrl);

            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Picasso.with(mContext).load(AppNetConfig.IMAGE_URL+url).into(view);
                }
            });
            banner.setIndicatorGravity(BannerConfig.RIGHT);
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    UIUtils.toast(""+position,false);
                }
            });
        }
    }


    /**
     * CHANNEL的ViewHolder
     */
    private class CHANNELViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView mGridView;
        private ChannelAdapter mAdapter;

        public CHANNELViewHolder(Context context, View view) {
            super(view);
            this.mContext = context;
            mGridView = (GridView) view.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResuleBeanData.ResultBean.ChannelInfoBean> data) {
            //得到数据
            //设置GirdView的适配器
            mAdapter = new ChannelAdapter(data);
            mGridView.setAdapter(mAdapter);
            //设置item的点击事件
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                    UIUtils.toast("position"+i,false);
                }
            });
        }
    }

    /**
     * Act的ViewHolder
     */
    private class ActViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        private ViewPager viewPager;
        public ActViewHolder(Context context, View view) {
            super(view);
            this.mContext = context;
            viewPager = (ViewPager) view.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResuleBeanData.ResultBean.ActInfoBean> data) {
            viewPager.setPageMargin(UIUtils.dp2px(20));
            viewPager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            viewPager.setPageTransformer(true, new AlphaPageTransformer());

            //接收数据
            //设置viewpager适配器
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return data.size();
                }

                /**
                 * 判断这两个参数 是否相同
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view==object;
                }

                /**
                 *
                 * @param container viewpager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //加载图片
                    Picasso.with(mContext).load(AppNetConfig.IMAGE_URL+data.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);
                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UIUtils.toast("viewpager图片的位置"+position,false);
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }
    public class SeckillViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        SeckillRecyclerViewAdapter mAdapter ;

        public SeckillViewHolder(Context context, View view) {
            super(view);
            this.mContext = context;
            tv_time_seckill = (TextView) view.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) view.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) view.findViewById(R.id.rv_seckill);
        }

        public void setData(ResuleBeanData.ResultBean.SeckillInfoBean data) {
            //1.得到数据
            //2.设置数据
            //3.设置适配器RecyclerView
            mAdapter = new SeckillRecyclerViewAdapter(mContext,data.getList());
            rv_seckill.setAdapter(mAdapter);
            //设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            //设置item的点击事件
            mAdapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    UIUtils.toast("秒杀position"+position,false);
                }
            });
        }
    }
}
