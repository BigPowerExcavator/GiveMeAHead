package com.four.dao;

import java.util.Map;

import com.four.javaBean.UserRegisterBean;
import com.four.javaBean.UsersBean;

public interface Users {
	public abstract UsersBean getPasswd(int stuId) ;
	public abstract boolean checkUserName(String userName);
	public abstract boolean userRegister(UserRegisterBean user);
	public abstract boolean changeUserHeadImg(String imgPart,String stuId);
	//�޸��û���Ϣ
	public abstract boolean upadteInfo(int stuNum,String userName,String trueName,String sex,String dormitory,String phone,int stuId);   
	//�жϸ�ѧ�Ŵ治���ڣ��൱���ж��û��治���ڣ�
	public abstract boolean ProveUser(int stuNum);
	//�޸�����
	public abstract boolean updatePasswd(int stuNum,String passwd);
	//���ظ�����������Ҫ����Ϣ
	public abstract Map<String, String> Personal(String stuNum);  
}
