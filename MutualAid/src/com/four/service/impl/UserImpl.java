package com.four.service.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.dao.impl.recUsers;
import com.four.javaBean.UserLoginBean;
import com.four.javaBean.UserRegisterBean;
import com.four.javaBean.UsersBean;
import com.four.javaBean.RepairBean;
import com.four.service.userService;
import com.four.servlet.contral.UserLogin;

public class UserImpl implements userService{

	@Override
	public boolean checkLogin(UserLoginBean user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String passwd=user.getPassword();
		int stuId=Integer.parseInt(user.getStuId());
		System.out.println("stuId:"+user.getStuId()+"password:"+user.getPassword());
		UsersBean userCheck=new recUsers().getPasswd(stuId);
		String password =userCheck.getPassword();
		if(password==null) {
			return false;
		}
		if(password.equals(passwd)) {
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean userRegister(UserRegisterBean user) {
		if(new recUsers().userRegister(user)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean cheakRegisterRename(String userName) {
		if(new recUsers().checkUserName(userName)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean writeCard(RepairBean repair) {
		
		return false;
	}
	
	public Map<String, String> Personal(String stuNum){
		Map<String,String> map = new recUsers().Personal(stuNum);
		if(map.get("userDomc")==null||map.get("userDomc").equals("")) {
			map.put("userDomc","…–Œ¥ÃÓ–¥");
		}
		return map;
	}
	
	/**
	 * @author 76429
	  * ∆•≈‰ «∑Ò”Î‘≠√‹¬Îœ‡µ»
	 */
	public boolean checkPasswd(int stuNum,String oldPasswd) {
		// TODO Auto-generated method stub
		boolean flag = false;

		UsersBean userCheck=new recUsers().getPasswd(stuNum);
		String password =userCheck.getPassword();
		System.out.println(password);
		if(password==null) {
			return false;
		}
		if(password.equals(oldPasswd)) {
			flag=true;
		}
		return flag;
	}
}
