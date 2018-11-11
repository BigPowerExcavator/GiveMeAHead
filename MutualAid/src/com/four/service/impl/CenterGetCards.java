package com.four.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RepairFormImp;
import com.four.javaBean.RepairBean;
import com.four.javaBean.UserLoginBean;

public class CenterGetCards {
	ArrayList<Map<String, String>> cardList;
	
	
	//专门获取该用户的所有维修表信息（已弃用）
	public Map<String,Object> getRepairCards(UserLoginBean user){
		Map<String,Object> map=new HashMap<String,Object>();
		cardList=new ArrayList<Map<String, String>>();
		cardList.addAll(this.getRepairCardsList(user));
		Collections.sort(cardList, new Comparator<Map<String, String>>() {
		    
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
		map.put("count", cardList.size()+"");
		for(int i=0;i<cardList.size();i++) {
			map.put((i+1)+"", cardList.get(i));
		}
		return map;
	}
	
	//返回该用户的所有卡片
	public Map<String,Object> getAllCards(UserLoginBean user){
		Map<String,Object> map=new HashMap<String,Object>();
		cardList=new ArrayList<Map<String, String>>();
		cardList.addAll(this.getRepairCardsList(user));
		//根据time来对CardBean进行排序（降序）
		Collections.sort(cardList, new Comparator<Map<String, String>>() {
    
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
		map.put("count", cardList.size()+"");
		for(int i=0;i<cardList.size();i++) {
			map.put("card"+(i+1), cardList.get(i));
		}
		return map;
	}
	
	//返回一个以CardBean<RepairBean>为类
	public ArrayList<Map<String, String>> getRepairCardsList(UserLoginBean user){
		ArrayList<Map<String, String>> repairCards=new ArrayList<Map<String, String>>();
		ArrayList<RepairBean> repairList=new ArrayList<RepairBean>();
		RepairFormImp repairFormImp=new RepairFormImp();
		ArrayList<String[]> tempRepairList=repairFormImp.getFromNum(user);
		repairList.addAll(new RepairFormImp().getRanking(tempRepairList, true));
		repairList.addAll(new RepairFormImp().getRanking(tempRepairList, false));
		
		for(RepairBean card:repairList) {
			Map<String, String> map=new HashMap<String, String>();
			
			map.put("content", card.getContent());
			map.put("date", card.getRepairDate().getTime()+"");
			map.put("state", card.getState());
			map.put("formId", card.getFormId());
			map.put("type", "fix");
			repairCards.add(map);
		}
		return repairCards;		
	}
	
	//返回最新的RepairCard
	public Map<String, String>getNewestRepairCard(String stuNum){
		ArrayList<Map<String, String>> repairCards=new ArrayList<Map<String, String>>();
		ArrayList<RepairBean> repairList=new ArrayList<RepairBean>();
		RepairFormImp repairFormImp=new RepairFormImp();
		UserLoginBean user=new UserLoginBean();
		user.setStuId(stuNum);
		ArrayList<String[]> tempRepairList=repairFormImp.getFromNum(user);
		System.out.println(tempRepairList.toString());
		repairList.addAll(new RepairFormImp().getRanking(tempRepairList, false));
		for(RepairBean card:repairList) {
			Map<String, String> mapTemp=new HashMap<String, String>();			
			mapTemp.put("content", card.getContent());
			mapTemp.put("date", card.getRepairDate().getTime()+"");
			mapTemp.put("Docm", card.getDomc());
			//mapTemp.put("formId", card.getFormId());
			mapTemp.put("ranking", card.getRanking());
			repairCards.add(mapTemp);
		}
		//System.out.println("size="+repairCards.size());
		if(repairCards.size()==1) {
			return repairCards.get(0);
		}else if(repairCards.size()==0){
			return null;
		}else {
			Collections.sort(repairCards, new Comparator<Map<String, String>>() {
			    
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
			Map<String, String> map=repairCards.get(0);
			return map;
		}
		
	}
}

