package com.richer.smartbutler;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String>mTitle;
    //Fragment
    private List<Fragment>mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initData();
        initView();
    }

    //初始化View
    private void initView() {
    }

    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getString(R.string.service_butler));
        mTitle.add(getString(R.string.wechat_selected));
        mTitle.add(getString(R.string.beauty_community));
        mTitle.add(getString(R.string.personal_center));
    }

}
