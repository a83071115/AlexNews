package com.alex.alexnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * 作用:缓存软件的一些参数和数据
 * Created by Administrator on 2016/12/3.
 */


public class CacheUtils {
    /**
     * 得到缓存值
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }

    /**
     * 保存软件的参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context  context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 缓存文本的数据
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    /**
     * 读取文本的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sp.getString(key,"");

    }
}
