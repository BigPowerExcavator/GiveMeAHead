package com.four.dao;

import java.util.ArrayList;
import java.util.Map;

import com.four.javaBean.UserLoginBean;
import com.four.javaBean.RepairBean;

public interface repairForm {
	
	public abstract boolean saveRepair(RepairBean repair);
	
	/*
	 * ��ø�ѧ�����������������Ϣ
	 * state==true ʱ�������Դ�����ϵı�
	 * state==false ʱ������δ����ı�
	 */
	public abstract ArrayList<RepairBean> getRanking(ArrayList<String[]> cardList,int state);
	
	//��ø�ѧ�������б������루�����ѽ�������ϵģ�
	public abstract ArrayList<String[]> getFromNum(UserLoginBean user);
	
	//��ø�ѧ�������뱨�������(�ظ�Ϊ��ֵΪ��ȡʧ��)
	public abstract int getRepairFormNum(UserLoginBean user);
	
	//ɾ��ѧ��������
	public abstract boolean deleteRepairForm(UserLoginBean user,String formId);
	
	
}
