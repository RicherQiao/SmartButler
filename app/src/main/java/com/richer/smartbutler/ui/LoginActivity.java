package com.richer.smartbutler.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.richer.smartbutler.MainActivity;
import com.richer.smartbutler.R;
import com.richer.smartbutler.entity.MyUser;
import com.richer.smartbutler.utils.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //注册按钮
    private Button btnRegisiered;
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox ckKeepPassword;
    private TextView tvForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btnRegisiered = (Button) findViewById(R.id.btn_registered);
        btnRegisiered.setOnClickListener(this);
        etName = (EditText) findViewById(R.id.et_login_name);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        ckKeepPassword = (CheckBox) findViewById(R.id.keep_password);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        tvForget.setOnClickListener(this);

        //设置选中的状态
        boolean isCheck = ShareUtils.getBoolean(this,"keepPass",false);
        ckKeepPassword.setChecked(isCheck);
        if (isCheck){
            //设置密码
            etName.setText(ShareUtils.getString(this,"name",""));
            etPassword.setText(ShareUtils.getString(this,"password",""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                //1.获取输入框的值
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)){
                    //登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //判断结果
                            if (e == null){
                                //判断邮箱是否验证
                                if (user.getEmailVerified()){
                                   //跳转
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(LoginActivity.this,"请前往邮箱验证",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(LoginActivity.this,"登录失败："+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }
    }

    //假设输入用户名密码后不点击登录而是直接退出  不保存密码
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this,"keepPass",ckKeepPassword.isChecked());

        //是否记住密码
        if (ckKeepPassword.isChecked()){
            //记住用户名和密码
            ShareUtils.putString(this,"name",etName.getText().toString().trim());
            ShareUtils.putString(this,"password",etPassword.getText().toString().trim());
        }else {
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}
