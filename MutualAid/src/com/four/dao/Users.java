package com.four.dao;

import java.util.Map;

import com.four.javaBean.UserRegisterBean;
import com.four.javaBean.UsersBean;

public interface Users {
	public abstract UsersBean getPasswd(int stuId) ;
	public abstract boolean checkUserName(String userName);
	public abstract boolean userRegister(UserRegisterBean user);
	public abstract boolean changeUserHeadImg(String imgPart,String stuId);
	//修改用户信息
	public abstract boolean upadteInfo(int stuNum,String userName,String trueName,String sex,String dormitory,String phone,int stuId);   
	//判断该学号存不存在（相当于判断用户存不存在）
	public abstract boolean ProveUser(int stuNum);
	//修改密码
	public abstract boolean updatePasswd(int stuNum,String passwd);
	//返回个人中心所需要的信息
	public abstract Map<String, String> Personal(String stuNum);  
}
