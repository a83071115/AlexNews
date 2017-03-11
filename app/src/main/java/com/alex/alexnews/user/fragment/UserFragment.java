package com.alex.alexnews.user.fragment;

import com.alex.alexnews.R;
import com.alex.alexnews.common.BaseFragment;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2017/3/10.
 */

public class UserFragment extends BaseFragment {

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {

    }

    @Override
    protected void initTitle() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }


}
