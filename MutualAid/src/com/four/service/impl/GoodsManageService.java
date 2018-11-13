package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RecGoods;
import com.four.javaBean.GoodBean;

public class GoodsManageService {
	
	RecGoods recGoods=new RecGoods();;
	
	public boolean writeGoodsCard(GoodBean goodBean) {
		boolean result=false;
		if(goodBean!=null) {
			result=recGoods.writeGoodsCard(goodBean);
		}
		return result;
	}
	
	public boolean changeGoodImg(String goodsId,String url){
		boolean result=false;
		if(goodsId==null||goodsId.equals("")||url==null||url.equals("")) {
			return result;
		}else {
			result=recGoods.changeGoodsImgs(goodsId, url);			
		}
		return result;
	}
	
	public String[] getGoodImgUrl(String goodsId) {
		String[] url=null;
		if(goodsId!=null&&!goodsId.equals("")) {
			url=recGoods.getGoodsImgs(goodsId).split("###");
		}
		return url;
	}
	
	public boolean delectGoodCard(String goodsId) {
		boolean result=false;
		if(goodsId!=null&&!goodsId.equals("")) {
			result=recGoods.deleteGoodCard(goodsId);
		}
		return result;
	}
	
	public boolean changeGoodCard(GoodBean goodBean) {
		boolean result=false;
		if(goodBean!=null) {
			if(goodBean.getGoodsImg()==null||goodBean.getGoodsImg().equals("")) {
				goodBean.setGoodsImg(recGoods.getGoodsImgs(goodBean.getGoodsId()));
			}
			result=recGoods.changeGoodsInfo(goodBean);
		}
		return result;
	}
	
	public GoodBean getGoodInfo(String goodsId) {
		GoodBean goodBean=new GoodBean();
		if(goodsId!=null&&!goodsId.equals("")) {
			goodBean=recGoods.getGoodInfo(goodsId);
		}
		return goodBean;
	}
	
	public Map<String ,Object> findGoodsCard(String info,String beginNum,String num){
		String[] tempInfo=info.split("");
		String addSql="%";
		for(int i=0;i<tempInfo.length;i++) {
			addSql+=tempInfo[i]+"%";
		}
		int count=recGoods.titleFindCardsCount(addSql);
		ArrayList<GoodBean> arrayList=recGoods.titleFindCards(addSql,beginNum,num);
		Map<String, Object> cards=new HashMap<String,Object>();
		cards.put("count", count);
		for(int j=0;j<arrayList.size();j++) {			
			cards.put("cards"+(j+1), arrayList.get(j));
		}
		return cards;
	}
	
	public Map<String ,Object> getGoodsCard(String beginNum,String num){
		int count=recGoods.getAllGoodsCardsCount();
		ArrayList<GoodBean> arrayList=recGoods.getAllGoodsCards(beginNum, num);
		Map<String, Object> cards=new HashMap<String,Object>();
		cards.put("count", count);
		for(int j=0;j<arrayList.size();j++) {			
			cards.put("cards"+(j+1), arrayList.get(j));
		}
		return cards;
	}
	
}
