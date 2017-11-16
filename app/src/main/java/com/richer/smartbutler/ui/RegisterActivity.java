package com.richer.smartbutler.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.richer.smartbutler.R;
import com.richer.smartbutler.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private EditText etUser;
    private EditText etAge;
    private EditText etDesc;
    private RadioGroup mRadioGroup;
    private EditText etPass;
    private EditText etPassword;
    private EditText etEmail;
    private Button btnRegister;

    //性别
    private boolean isGender = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
    }

    private void initView() {
        etUser = (EditText) findViewById(R.id.et_user);
        etAge = (EditText) findViewById(R.id.et_age);
        etDesc = (EditText) findViewById(R.id.et_desc);
        etPass = (EditText) findViewById(R.id.et_pass);
        etPassword = (EditText) findViewById(R.id.et_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        mRadioGroup = (RadioGroup) findViewById(R.id.m_radio_group);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                //获取到输入框的值
                String name = etUser.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                //判断是否为空
                if (!TextUtils.isEmpty(name)&!TextUtils.isEmpty(age)&
                        !TextUtils.isEmpty(pass)&
                        !TextUtils.isEmpty(password)&
                        !TextUtils.isEmpty(email)){

                    //判断两次输入的密码是否一致
                    if (pass.equals(password)){

                        //先判断性别
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_boy){
                                    isGender = true;
                                }else if(checkedId == R.id.rb_girl){
                                    isGender = false;
                                }
                            }
                        });

                        //判断简介是否为空
                        if (TextUtils.isEmpty(desc)){
                            desc = getString(R.string.desc_null);
                        }

                        //注册
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setPassword(pass);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);
                        user.setDesc(desc);

                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e == null){
                                    Toast.makeText(RegisterActivity.this,getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this,getString(R.string.register_fail)+e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(this,getString(R.string.pass_password),Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(this,getString(R.string.et_null),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
