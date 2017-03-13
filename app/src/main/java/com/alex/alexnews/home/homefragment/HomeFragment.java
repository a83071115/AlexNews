package com.alex.alexnews.home.homefragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alex.alexnews.R;
import com.alex.alexnews.common.AppNetConfig;
import com.alex.alexnews.common.BaseFragment;
import com.alex.alexnews.home.adapter.HomeFragmentAdapter;
import com.alex.alexnews.home.bean.ResuleBeanData;
import com.alex.alexnews.utils.UIUtils;
import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/10.
 */

public class HomeFragment extends BaseFragment {


    @Bind(R.id.tv_search_home)
    TextView mTvSearchHome;
    @Bind(R.id.tv_message_home)
    TextView mTvMessageHome;
    @Bind(R.id.rv_home)
    RecyclerView mRvHome;
    @Bind(R.id.ib_top)
    ImageButton mIbTop;
    /**
     * 返回的数据
     */
    private ResuleBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;

    @Override
    protected RequestParams getParams() {

        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.HOME_URL;
    }

    @Override
    protected void initData(String content) {
        //解析数据
        processData(content);
        
        
    }

    private void processData(String content) {
        ResuleBeanData resuleBeanData = JSON.parseObject(content,ResuleBeanData.class);
        resultBean = resuleBeanData.getResult();
        if(resultBean!=null){
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(getActivity(),resultBean);
            mRvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(getActivity(),1);
            //设置跨度大小的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position<=4){
                        //隐藏
                        mIbTop.setVisibility(View.INVISIBLE);
                    }else{
                        //显示
                        mIbTop.setVisibility(View.VISIBLE);
                    }
                    return 1;
                }
            });
            //设置布局管理者
            mRvHome.setLayoutManager(manager);
        }else{
            //没有数据

        }

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
    @OnClick({R.id.ib_top,R.id.tv_search_home,R.id.tv_message_home})
    public void showTab(View view){
        switch (view.getId()){
            case R.id.ib_top:
                mRvHome.scrollToPosition(0);
                UIUtils.toast("回到了顶部",false);
                break;
            case R.id.tv_search_home:
                UIUtils.toast("搜索",false);
                break;
            case R.id.tv_message_home:
                UIUtils.toast("进入消息中心",false);
                break;
        }
    }

}
