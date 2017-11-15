package com.richer.smartbutler.application;

/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.application
* 文件名： BaseApplication
* 创建者： RicherQiao
* 创建时间： 2017/11/09 21:47
* 描述： Application
*/

import android.app.Application;

import com.richer.smartbutler.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application{

    //创建

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
    }
}
