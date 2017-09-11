package com.example.william_zhang.william_zhang.activity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.william_zhang.william_zhang.Fragment01;
import com.example.william_zhang.william_zhang.R;
import com.example.william_zhang.william_zhang.presenter.ArgbEvaluatorPresenter;
import com.example.william_zhang.william_zhang.util.DeviceUtil;
import com.example.william_zhang.william_zhang.view.MyNestScrollVIew;
import com.example.william_zhang.william_zhang.view.NestScrollViewPaper;
import com.example.william_zhang.william_zhang.viewlistener.ArgbEvaluatorListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by william_zhang on 2017/9/5.
 */

public class ArgbEvaluatorActivity extends AppCompatActivity implements ArgbEvaluatorListener{
    String TAG="ArgbEvaluatorActivity";
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.MyNestScrollVIew)
    MyNestScrollVIew mMyNestScrollVIew;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
   @BindView(R.id.toorbar)
    Toolbar mToorbar;
    @BindView(R.id.viewpaper)
    NestScrollViewPaper mViewPager;
    private ArgbEvaluatorPresenter mArgbEvaluatorPresenter;
    private ArrayList<LinearLayout> lists;
    private List<Fragment01> fragments;
    private PagerAdapter mPagerAdapter;
    private FragmentPagerAdpater adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_argbevaluator);
        ButterKnife.bind(this);

        setSupportActionBar(mToorbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        //mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            boolean isAnimtor=false;
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                ValueAnimator colorValueAnimtor;
                if(!isAnimtor) {
                    colorValueAnimtor = ValueAnimator.ofObject(new ArgbEvaluator(), 0xff555555, 0x999999);
                    colorValueAnimtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            mAppBarLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
                        }
                    });
                    colorValueAnimtor.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                                     isAnimtor=true;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                                     isAnimtor=false;
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    colorValueAnimtor.setDuration(150);
                    colorValueAnimtor.start();
                }

            }

        });
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(TAG,Integer.toString(scrollX));
            }
        });

//        mTabLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.e(TAG,Integer.toString(scrollX));
//            }
//        });

    }

    private void init() {
        mArgbEvaluatorPresenter=new ArgbEvaluatorPresenter(this,this);
        lists=new ArrayList<>();
        mArgbEvaluatorPresenter.initViews();
//        mMyNestScrollVIew.setMinimumHeight(DeviceUtil.getDeviderHeight(this)[1]-DeviceUtil.dp2px(56,this)-DeviceUtil.dp2px(36,this)-DeviceUtil.getStatusBarHeight(
//                this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void setViews(ArrayList<LinearLayout> list,ArrayList<String> values) {
       // lists=list;
        fragments=new LinkedList<>();
        int [] color={0xff222222,0x90666666,0x30999999};
        for(int i=0;i<3;i++){
            Fragment01 f=new Fragment01();
            f.setBackGroundColor(color[i]);
            fragments.add(f);
        }
        adapter=new FragmentPagerAdpater(getSupportFragmentManager(),fragments);
        //mPagerAdapter=new ViewPagerAdapter(lists);
        for (String s:values){
            mTabLayout.addTab(mTabLayout.newTab().setText(s));
        }
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        mTabLayout.setTabTextColors(0x99000000,getResources().getColor(R.color.colorAccent));
    }

    class ViewPagerAdapter extends PagerAdapter{
        ArrayList<LinearLayout> list;

        public ViewPagerAdapter(ArrayList<LinearLayout> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            Log.e(TAG,Integer.toString(list.size()));
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }


    class FragmentPagerAdpater extends FragmentPagerAdapter{
        List<Fragment01> mList;
        public FragmentPagerAdpater(FragmentManager fm, List<Fragment01> list) {
            super(fm);
            mList=list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
