package com.richer.smartbutler.fragment;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.fragment
* 文件名： WechatFragment
* 创建者： RicherQiao
* 创建时间： 2017/11/09 0:11
* 描述： 微信精选
*/

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richer.smartbutler.R;

public class WechatFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat,null);
        return view;
    }
}
