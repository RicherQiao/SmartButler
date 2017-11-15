package com.richer.smartbutler.ui;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.ui
* 文件名： SplashActivity
* 创建者： RicherQiao
* 创建时间： 2017/11/10 下午11:46
* 描述： 闪屏页
*/

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.richer.smartbutler.MainActivity;
import com.richer.smartbutler.R;
import com.richer.smartbutler.utils.ShareUtils;
import com.richer.smartbutler.utils.StaticClass;
import com.richer.smartbutler.utils.UtilTools;

public class SplashActivity extends AppCompatActivity{

    /*
    * 1.延时2000毫秒
    * 2.判断程序是否第一次运行
    * 3.自定义字体
    * 4.Activity全屏主题
    * */

    private TextView tvSplash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否第一次运行
                    if (isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else{
                        //startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    //初始化view
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);

        tvSplash = (TextView) findViewById(R.id.tv_splash);
        //设置字体
        UtilTools.setFont(this,tvSplash);
    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        if (isFirst){
            //标记我们已经启动过App
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            //是第一次运行
            return true;
        }else {
            return false;
        }
    }

    //禁止返回键

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
