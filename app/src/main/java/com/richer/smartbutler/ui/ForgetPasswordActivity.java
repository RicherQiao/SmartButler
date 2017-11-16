package com.richer.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.richer.smartbutler.R;
import com.richer.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private Button btnForgetPassword;
    private EditText etEmail;

    private EditText etOld;
    private EditText etNew;
    private EditText etAgain;
    private Button btnRevise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initView();
    }

    //初始化View
    private void initView() {
        btnForgetPassword = (Button) findViewById(R.id.btn_forget_password);
        etEmail = (EditText) findViewById(R.id.et_email);

        etOld = (EditText) findViewById(R.id.et_old);
        etNew = (EditText) findViewById(R.id.et_new);
        etAgain = (EditText) findViewById(R.id.et_again);
        btnRevise = (Button) findViewById(R.id.btn_revise);

        btnForgetPassword.setOnClickListener(this);
        btnRevise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forget_password:
                //1.获取输入框的邮箱
                final String email = etEmail.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(email)){
                    //判断输入是否为邮箱  todo
                    //3.发送邮件
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                Toast.makeText(ForgetPasswordActivity.this,"邮件已经发送至："+email,Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(ForgetPasswordActivity.this,"邮件发送失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_revise:
                //1.获取输入框的值
                String old = etOld.getText().toString().trim();
                String newPassword = etNew.getText().toString().trim();
                String again = etAgain.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(old) & !TextUtils.isEmpty(newPassword) & !TextUtils.isEmpty(again)){
                    //3.判断两次新密码是否一致
                    if (newPassword.equals(again)){
                        //4.重置密码
                        MyUser.updateCurrentUserPassword(old, newPassword, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null){
                                    Toast.makeText(ForgetPasswordActivity.this,"重置密码成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ForgetPasswordActivity.this,"重置密码失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
