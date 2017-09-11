package com.example.william_zhang.william_zhang.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

import static java.security.AccessController.getContext;

/**
 * Created by william_zhang on 2017/9/5.
 */

public class DeviceUtil {
    /**
     * @param context 上下文
     * @return 屏幕的宽高 单位px
     */
    public static int[] getDeviderHeight(Context context) {
        int[] a = new int[2];
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        a[0] = dm.widthPixels;
        a[1] = dm.heightPixels;
        return a;
    }


    /**
     * //dp*scale+0.5f=px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * //dp*scale+0.5f=px
     *
     * @param px
     * @param context
     * @return
     */
    //dp*scale+0.5f=px
    public static int px2dp(int px, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     *
     * @param context
     * @return 返回状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
}
