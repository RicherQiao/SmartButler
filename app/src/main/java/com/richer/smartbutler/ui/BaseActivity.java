package com.richer.smartbutler.ui;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.ui
* 文件名： BaseActivity
* 创建者： RicherQiao
* 创建时间： 2017/11/09 21:49
* 描述： Activity基类
*/

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


/*
*  主要做的事情：
*  1.统一的属性
*  2.统一的接口
*  3.统一的方法
* */
public class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            //home  返回键的id
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
