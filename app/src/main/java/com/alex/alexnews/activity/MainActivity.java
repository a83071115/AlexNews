package com.alex.alexnews.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alex.alexnews.R;
import com.alex.alexnews.common.BaseActivity;
import com.alex.alexnews.faxian.fragment.FaXianFragment;
import com.alex.alexnews.home.homefragment.HomeFragment;
import com.alex.alexnews.shoppingcart.fragment.ShoppingcartFragment;
import com.alex.alexnews.type.fragment.TypeFragment;
import com.alex.alexnews.user.fragment.UserFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int WHAT_RESET_BACK = 1;
    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @Bind(R.id.rb_home)
    RadioButton mRbHome;
    @Bind(R.id.rb_type)
    RadioButton mRbType;
    @Bind(R.id.rb_community)
    RadioButton mRbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton mRbCart;
    @Bind(R.id.rb_user)
    RadioButton mRbUser;
    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    private FragmentTransaction transaction;
    @Override
    protected void initData() {
        setSelect(0);
        mRgMain.check(R.id.rb_home);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @OnClick({R.id.rb_home,R.id.rb_type,R.id.rb_community,R.id.rb_cart,R.id.rb_user})
    public void showTab(View view){
//        Toast.makeText(MainActivity.this,"选择了具体的Tab",Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.rb_home://首页
                setSelect(0);
                break;
            case R.id.rb_type://分类
                setSelect(1);

                break;
            case R.id.rb_community://发现
                setSelect(2);

                break;
            case R.id.rb_cart: //购物车
                setSelect(3);
                break;
            case R.id.rb_user: //用户中心
                setSelect(4);
                break;
        }
    }
    private HomeFragment homeFragment;
    private TypeFragment mTypeFragment;
    private FaXianFragment mFaXianFragment;
    private ShoppingcartFragment mShoppingcartFragment;
    private UserFragment mUserFragment;

    public void setSelect(int i) {
        FragmentManager manager = this.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        hideFragment(); //隐藏所有Fragment
        switch (i){
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.frameLayout, homeFragment);
                }
                transaction.show(homeFragment);
                break;
            case 1:
                if (mTypeFragment == null) {
                    mTypeFragment = new TypeFragment();
                    transaction.add(R.id.frameLayout, mTypeFragment);
                }
                transaction.show(mTypeFragment);
                break;
            case 2:
                if (mFaXianFragment == null) {
                    mFaXianFragment = new FaXianFragment();
                    transaction.add(R.id.frameLayout, mFaXianFragment);
                }
                transaction.show(mFaXianFragment);
                break;
            case 3:
                if (mShoppingcartFragment == null) {
                    mShoppingcartFragment = new ShoppingcartFragment();
                    transaction.add(R.id.frameLayout, mShoppingcartFragment);
                }
                transaction.show(mShoppingcartFragment);
                break;
            case 4:
                if (mUserFragment == null) {
                    mUserFragment = new UserFragment();
                    transaction.add(R.id.frameLayout, mUserFragment);
                }
                transaction.show(mUserFragment);
                break;
        }
        transaction.commit();
    }

    private void hideFragment() {
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(mTypeFragment != null){
            transaction.hide(mTypeFragment);
        }
        if(mFaXianFragment != null){
            transaction.hide(mFaXianFragment);
        }
        if(mShoppingcartFragment != null){
            transaction.hide(mShoppingcartFragment);
        }
        if(mUserFragment != null){
            transaction.hide(mUserFragment);
        }
    }
    //重写onKeyUp(),实现连续两次点击方可退出当前应用

    private boolean flag = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WHAT_RESET_BACK:
                    flag = true;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && flag){
            Toast.makeText(MainActivity.this,"再点击一次,退出当前应用",Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }

        return super.onKeyUp(keyCode, event);

    }
    //为了避免出现内存的泄露,需要在onDestroy()中,移除所有未被执行的消息

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //方式一://移除指定id的所有的消息
        handler.removeMessages(WHAT_RESET_BACK);
        //方式二:移除所有的未被执行的消息
        handler.removeCallbacksAndMessages(null);
    }
}
