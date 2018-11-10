package com.four.service;

import com.four.javaBean.UserLoginBean;
import com.four.javaBean.UserRegisterBean;
import com.four.javaBean.RepairBean;

public interface userService {
	boolean checkLogin(UserLoginBean user);
	boolean writeCard(RepairBean repair);
	boolean userRegister(UserRegisterBean user);
	boolean cheakRegisterRename(String userName);
	boolean checkPasswd(int stuNum,String oldPasswd);
}
