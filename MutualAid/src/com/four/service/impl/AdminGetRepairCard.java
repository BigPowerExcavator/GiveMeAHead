package com.four.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.RepairBean;
import com.four.util.GetTrueDoorTime;

public class AdminGetRepairCard {
	
	private RepairFormImp repairFormImp=new RepairFormImp();
	private ArrayList<Map<String, String>> cards;
	
	public ArrayList<Map<String, String>> getRepairCards(String state){
		cards=new ArrayList<Map<String, String>>();
		ArrayList<RepairBean> cardList=repairFormImp.getAllCard(state);
		//System.out.println(state+"   "+cardList.size());
		for(int i=0;i<cardList.size();i++) {
			RepairBean repairBean=cardList.get(i);
			Map<String, String> map=new HashMap<String,String>();
			map.put("userName", repairBean.getName());
			map.put("userTel", repairBean.getTel());
			map.put("dormtory", repairBean.getDomc());
			map.put("date", repairBean.getRepairDate().getTime()+"");
			map.put("content", repairBean.getContent());
			//System.out.println(repairBean.getTime());
			map.put("doorTime", new GetTrueDoorTime().getDoorTime(repairBean.getTime())+"");
			map.put("state", repairBean.getState());
			cards.add(map);
		}
		return cards;
	}
	
	public Map<String,Object> sortAllCards(ArrayList<Map<String, String>> cards){
		Map<String,Object> map=new HashMap<String,Object>();
		//根据time来对CardBean进行排序（降序）
		Collections.sort(cards, new Comparator<Map<String, String>>() {
    
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				long long1=Long.parseLong(o1.get("date"));
				long long2=Long.parseLong(o2.get("date"));
				if(long1<long2){  
		              return 1;  
				}
				if(long1==long2){  
		              return 0;  
		        }
				return -1;
			}}
		);
		map.put("count", cards.size()+"");
		for(int i=0;i<cards.size();i++) {
			map.put("card"+(i+1), cards.get(i));
		}
		return map;
	}
	
}
