package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.RepairBean;
import com.four.util.GetTrueDoorTime;

public class AdminGetRepairCard {
	
	private RepairFormImp repairFormImp=new RepairFormImp();
	private Map<String , Object> cards;
	
	public Map<String , Object> getRepairCards(String state){
		ArrayList<RepairBean> cardList=repairFormImp.getAllCard(state);
		for(int i=0;i<cardList.size();i++) {
			RepairBean repairBean=cardList.get(i);
			Map<String, String> map=new HashMap<String,String>();
			map.put("name", repairBean.getName());
			map.put("phone", repairBean.getTel());
			map.put("dormitory", repairBean.getDomc());
			map.put("time", repairBean.getRepairDate()+"");
			map.put("content", repairBean.getContent());
			map.put("doorTime", new GetTrueDoorTime().getDoorTime(repairBean.getTime()));
			cards.put("card"+(i+1), map);
		}
		return cards;
	}
	
}
