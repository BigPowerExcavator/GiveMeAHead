package com.four.dao;

import java.util.ArrayList;
import java.util.Map;

import com.four.javaBean.UserLoginBean;
import com.four.javaBean.RepairBean;

public interface repairForm {
	
	public abstract boolean saveRepair(RepairBean repair);
	
	/*
	 * 获得该学生报修申请的所有信息
	 * state==true 时，代表以处理完毕的表
	 * state==false 时，代表未处理的表
	 */
	public abstract ArrayList<RepairBean> getRanking(ArrayList<String[]> cardList,int state);
	
	//获得该学生的所有报修申请（包括已近处理完毕的）
	public abstract ArrayList<String[]> getFromNum(UserLoginBean user);
	
	//获得该学生所申请报表的数量(回复为负值为获取失败)
	public abstract int getRepairFormNum(UserLoginBean user);
	
	//删除学生单个表
	public abstract boolean deleteRepairForm(UserLoginBean user,String formId);
	
	
}
