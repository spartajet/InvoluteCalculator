package com.ptb.length.involutecalculator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> mViewList = new ArrayList<>();
    private LayoutInflater inflater;
    private View valueFixedView;
    private View contradictionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
    }

    private void initViews() {
        this.inflater = getLayoutInflater();
        this.valueFixedView = inflater.inflate(R.layout.value_fixed_activity, null);
        this.contradictionView = inflater.inflate(R.layout.contradiction_activity, null);
        this.mViewList.add(valueFixedView);
        this.mViewList.add(contradictionView);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setAdapter(new MyPagerAdapter());
        this.viewPager.setCurrentItem(0);
    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }
    }

}
