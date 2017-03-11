package com.alex.alexnews.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.loopj.android.http.AsyncHttpClient;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/12/6 0006.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        //将当前的activity添加到ActivityManager中
        ActivityManager.getInstance().add(this);

        initTitle();

        initData();

    }

    protected abstract void initData();

    protected abstract void initTitle();

    protected abstract int getLayoutId();

    public AsyncHttpClient client = new AsyncHttpClient();

    //启动新的activity
    public void goToActivity(Class Activity,Bundle bundle){
        Intent intent = new Intent(this,Activity);
        //携带数据
        if(bundle != null && bundle.size() != 0){
            intent.putExtra("data",bundle);
        }

        startActivity(intent);
    }

    //销毁当前的Activity
    public void removeCurrentActivity(){
        ActivityManager.getInstance().removeCurrentA();
    }

    //销毁所有的activity
    public void removeAll(){
        ActivityManager.getInstance().removeAll();
    }



}
