package com.four.dao;

import java.util.Map;

public interface admin {
	// ��֤��¼
	public abstract boolean LoginIn(String stuNum, String passwd);

	// ���ظ�����������Ҫ����Ϣ
	public abstract Map<String, Object> getAdminInfo(String adminNum);

	// �жϸ�ѧ�Ŵ治���ڣ��൱���ж��û��治���ڣ�
	public abstract boolean ProveAdmin(String adminNum);

	// �޸��û���Ϣ
	public abstract boolean upadteAdminInfo(String adminNum, String userName, String trueName, String sex,
			String phone, String adminId);

	// �޸�����
	public abstract boolean UpdatePasswd(String adminNum, String passwd);
}
