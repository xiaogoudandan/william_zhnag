package com.example.william_zhang.william_zhang;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.william_zhang.william_zhang.util.DeviceUtil;
import com.example.william_zhang.william_zhang.view.NestedListView;
import com.example.william_zhang.william_zhang.view.NoScrollListView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by william_zhang on 2017/9/5.
 */

public class Fragment01 extends Fragment {
    Integer color;
    View  mView;
    private NoScrollListView mListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment01,container,false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(color!=null)
        mView.setBackgroundColor(color);
//        LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                DeviceUtil.getDeviderHeight(getContext())[1]-DeviceUtil.dp2px(56,getContext())-DeviceUtil.dp2px(36,getContext())-DeviceUtil.getStatusBarHeight(
//                        getContext()
//                ));
//        mView.findViewById(R.id.fragment).setLayoutParams(layoutParams);
        mListView=(NoScrollListView)mView.findViewById(R.id.listview);
        List<String> list=new LinkedList<>();
        for(int i=0;i<20;i++){
             list.add(Integer.toString(i));
        }
        mListView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list));
    }

    public void setBackGroundColor(int color){
       this.color=color;
    }

}
