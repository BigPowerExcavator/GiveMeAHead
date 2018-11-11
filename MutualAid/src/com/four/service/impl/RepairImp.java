package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.RepairBean;
import com.four.javaBean.UserLoginBean;
import com.four.service.RepairService;

public class RepairImp implements RepairService{
	RepairFormImp repairFormImp;

	@Override
	public boolean writeRepairCard(RepairBean repair) {
		// TODO Auto-generated method stub
		repairFormImp=new RepairFormImp();
		if(repairFormImp.saveRepair(repair)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public Map<String,Object> getCardJsonUnfinished(UserLoginBean user) {
		try {
			RepairFormImp repairFormImp=new RepairFormImp();
			ArrayList<String[]> stuCard=repairFormImp.getFromNum(user);
			ArrayList<RepairBean> stuCardInf=repairFormImp.getRanking(stuCard, 0);
			Map<String ,Object>map=new HashMap<>();
			RepairBean repairBean;
			map.put("num", stuCardInf.size()+"");
			for(int i=0;i<stuCardInf.size();i++) {
				repairBean=stuCardInf.get(i);
				Map<String,Object> cardInfo=new HashMap<String, Object>();
				cardInfo.put("domc", repairBean.getDomc());
				cardInfo.put("content", repairBean.getContent());
				cardInfo.put("rank", repairBean.getRanking());
				map.put("fix"+(i+1), cardInfo);
			}
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public int checkRepairState(String state,String afterState,String formId) {
		int result=0;
		switch (state) {
		case "0":
			if(afterState.equals("2")||afterState.equals("3")) {
				boolean re=new RepairFormImp().changeState(formId, afterState);
				if(re) {result=1;}
			}else {
				result=2;
			}
			break;
		case "2":
			if(afterState.equals("3")||afterState.equals("1")) {
				boolean re=new RepairFormImp().changeState(formId, afterState);
				if(re) {result=1;}
			}else {
				result=2;
			}
			break;
		case "3":
			if(afterState.equals("1")) {
				boolean re=new RepairFormImp().changeState(formId, afterState);
				if(re) {result=1;}
			}else {
				result=2;
			}
			break;
		case "1":
			result=2;
			break;
		default:
			break;
		}
		return result;
	}

}
