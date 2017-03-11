package com.alex.alexnews.faxian.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alex.alexnews.R;
import com.alex.alexnews.common.BaseFragment;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/10.
 */

public class FaXianFragment extends BaseFragment {


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
        return R.layout.fragment_faxian;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
