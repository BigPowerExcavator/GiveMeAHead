package com.four.dao;

import java.util.Map;

public interface admin {
	// 验证登录
	public abstract boolean LoginIn(String stuNum, String passwd);

	// 返回个人中心所需要的信息
	public abstract Map<String, Object> getAdminInfo(String adminNum);

	// 判断该学号存不存在（相当于判断用户存不存在）
	public abstract boolean ProveAdmin(String adminNum);

	// 修改用户信息
	public abstract boolean upadteAdminInfo(String adminNum, String userName, String trueName, String sex,
			String phone, String adminId);

	// 修改密码
	public abstract boolean UpdatePasswd(String adminNum, String passwd);
}
