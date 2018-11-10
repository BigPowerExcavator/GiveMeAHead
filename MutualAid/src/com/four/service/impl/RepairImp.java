package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.RepairBean;
import com.four.javaBean.UserLoginBean;
import com.four.service.RepairService;

public class RepairImp implements RepairService{

	@Override
	public boolean writeRepairCard(RepairBean repair) {
		// TODO Auto-generated method stub
		RepairFormImp repairFormImp=new RepairFormImp();
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

}
