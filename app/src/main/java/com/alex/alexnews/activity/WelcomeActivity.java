package com.alex.alexnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alex.alexnews.R;

import butterknife.Bind;

public class WelcomeActivity extends Activity {

    @Bind(R.id.iv_welcome_icon)
    ImageView mIvWelcomeIcon;
    @Bind(R.id.rl_welcome)
    RelativeLayout mRlWelcome;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //将当前的activity添加到ActivityManager中
//        ActivityManager.getInstance().add(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程中
                startMainActivity();
            }
        },3000);
    }

    /**
     * 是否启动过
     */
    private boolean isStart = false;

    private void startMainActivity() {
        if(!isStart){
            isStart = true;
            startActivity(new Intent(this,MainActivity.class));
//            //结束activity的显示,并从栈空间移除
//            ActivityManager.getInstance().remove(WelcomeActivity.this);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent(event);
    }
}
