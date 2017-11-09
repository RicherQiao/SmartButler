package com.richer.smartbutler.ui;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.ui
* 文件名： SettingActivity
* 创建者： RicherQiao
* 创建时间： ${DATA}1:07
* 描述： 设置
*/

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.richer.smartbutler.R;

public class SettingActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_setting);
    }
}
