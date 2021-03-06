package com.richer.smartbutler.entity;
/*
* 项目名： SmartButler 
* 包名： com.richer.smartbutler.entity
* 文件名： MyUser
* 创建者： RicherQiao
* 创建时间： 2017/11/13 0:59
* 描述： 用户属性
*/

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser{

    private int age;
    private boolean sex;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
