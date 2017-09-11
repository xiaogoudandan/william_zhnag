package com.example.william_zhang.william_zhang.presenter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.william_zhang.william_zhang.util.DeviceUtil;
import com.example.william_zhang.william_zhang.viewlistener.ArgbEvaluatorListener;

import java.util.ArrayList;

/**
 * Created by william_zhang on 2017/9/5.
 */

public class ArgbEvaluatorPresenter {
    private ArgbEvaluatorListener mArgbEvaluatorListener;
    private Context mContext;

    public ArgbEvaluatorPresenter(ArgbEvaluatorListener mArgbEvaluatorListener,Context context) {
        this.mArgbEvaluatorListener = mArgbEvaluatorListener;
        mContext=context;
    }

    public void setAapter(){

    }

    public void initViews(){
        ArrayList<LinearLayout> list=new ArrayList<>();
        ArrayList<String> value=new ArrayList<>();
        int [] color={0xff000000,0x9000000,0x3000000};
        for (int i=0;i<3;i++)
        {
            LinearLayout view=new LinearLayout(mContext);
            LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    DeviceUtil.getDeviderHeight(mContext)[1]-DeviceUtil.dp2px(56,mContext)-DeviceUtil.dp2px(48,mContext)-DeviceUtil.getStatusBarHeight(mContext));
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(color[i]);
            list.add(view);
            value.add(Integer.toString(i));
        }
        mArgbEvaluatorListener.setViews(list,value);
    }
}
