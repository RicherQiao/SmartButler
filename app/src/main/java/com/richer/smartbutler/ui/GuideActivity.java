package com.richer.smartbutler.ui;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.ui
* 文件名： GuideActivity
* 创建者： RicherQiao
* 创建时间： 2017/11/11 上午12:33
* 描述： 引导页
*/

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.richer.smartbutler.R;

public class GuideActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_guide);
    }
}
