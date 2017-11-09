package com.richer.smartbutler.fragment;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.fragment
* 文件名： UserFragment
* 创建者： RicherQiao
* 创建时间： ${DATA}0:11
* 描述： 个人中心
*/

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richer.smartbutler.R;

public class UserFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user,null);
        return view;
    }
}
