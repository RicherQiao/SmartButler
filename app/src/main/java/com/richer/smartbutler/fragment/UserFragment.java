package com.richer.smartbutler.fragment;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.fragment
* 文件名： UserFragment
* 创建者： RicherQiao
* 创建时间： 2017/11/09 0:11
* 描述： 个人中心
*/

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.richer.smartbutler.R;
import com.richer.smartbutler.entity.MyUser;
import com.richer.smartbutler.ui.LoginActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserFragment extends Fragment implements View.OnClickListener {

    private Button btnExit;
    private TextView editUser;

    private EditText etUsername;
    private EditText etSex;
    private EditText etAge;
    private EditText etDesc;

    private Button btnUpdate;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user,null);
        findView(view);
        return view;
    }

    //初始化View
    private void findView(View view){
        btnExit = (Button) view.findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(this);

        editUser = (TextView) view.findViewById(R.id.edit_user);
        editUser.setOnClickListener(this);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etSex = (EditText) view.findViewById(R.id.et_sex);
        etAge = (EditText) view.findViewById(R.id.et_old);
        etDesc = (EditText) view.findViewById(R.id.et_desc);

        btnUpdate = (Button) view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);

        //默认是不可输入/不可点击
        setEnabled(false);

        //设置具体的值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        etUsername.setText(userInfo.getUsername());
        etSex.setText(userInfo.isSex()?"男":"女");
        etAge.setText(userInfo.getAge()+"");
        etDesc.setText(userInfo.getDesc());

    }

    //控制焦点
    private void setEnabled(boolean is){
        etUsername.setEnabled(is);
        etSex.setEnabled(is);
        etAge.setEnabled(is);
        etDesc.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //退出登录
            case R.id.btn_exit:
                //清除缓存用户对象
                MyUser.logOut();
                //现在的currentUser是null
                BmobUser currentUser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            //编辑资料
            case R.id.edit_user:
                setEnabled(true);
                btnUpdate.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update:
                //1.拿到输入框的值
                String username = etUsername.getText().toString();
                String age = etAge.getText().toString();
                String sex = etSex.getText().toString();
                String desc = etDesc.getText().toString();

                //2.判断是否为空
                if (!TextUtils.isEmpty(username)&!TextUtils.isEmpty(age)&!TextUtils.isEmpty(sex)){
                    //3.更新属性
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    if (sex.equals("男")){
                        user.setSex(true);
                    }else{
                        user.setSex(false);
                    }
                    //简介
                    if (!TextUtils.isEmpty(desc)){
                        user.setDesc(desc);
                    }else{
                        user.setDesc("这个人很懒，什么都没有留下");
                    }

                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    user.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                //修改成功
                                setEnabled(false);
                                btnUpdate.setVisibility(View.GONE);
                                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(getActivity(),"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
