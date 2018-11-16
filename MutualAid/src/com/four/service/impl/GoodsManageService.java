package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.impl.RecGoods;
import com.four.javaBean.GoodBean;

public class GoodsManageService {
	
	RecGoods recGoods=new RecGoods();
	
	//每页显示的卡片数
	int pageCount=12;
	
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
	
	public Map<String ,Object> findGoodsCard(String info,String beginNum,String num,String type,String sort){
		String[] tempInfo=info.split("");
		String addSql="%";
		for(int i=0;i<tempInfo.length;i++) {
			addSql+=tempInfo[i]+"%";
		}
		String addSort=null;
		switch (sort) {
		case "timeUp":
			addSort="order by time DESC";
			break;
		case "timeDown":
			addSort="order by time";
			break;
		case "priceUp":
			addSort="order by goodsPrice DESC";
			break;
		case "priceDown":
			addSort="order by goodsPrice";
			break;
		default:
			break;
		}
		//int count=recGoods.titleFindCardsCount(addSql);
		ArrayList<GoodBean> arrayList=recGoods.titleFindCards(addSql,beginNum,num,type,addSort);
		Map<String, Object> cards=new HashMap<String,Object>();
		//cards.put("count", count);
		for(int j=0;j<arrayList.size();j++) {	
			GoodBean goodBean=arrayList.get(j);
			Map<String, String> good=new HashMap<String ,String>();
			if(goodBean.getGoodsImg()==null) {
				good.put("goodsImg", "");
			}else {
				good.put("goodsImg", (goodBean.getGoodsImg()).split("###")[0]);
			}
			good.put("title", goodBean.getTitle());
			good.put("userName", goodBean.getUserName());
			if(goodBean.getGoodsPrice()==null) {
				good.put("goodsPrice", "");
			}else {
				good.put("goodsPrice", Float.parseFloat(goodBean.getGoodsPrice()+"")+"");
			}
			cards.put("card"+(j+1), good);
		}
		return cards;
	}
	
	public Map<String ,Object> findGoodsCard(String info,String beginNum){
		return this.findGoodsCard(info, beginNum, pageCount+"",null,null);
	}
	
	public Map<String ,Object> getGoodsCard(String beginNum,String num,String type,String sort){
		//int count=recGoods.getAllGoodsCardsCount();
		String addSort=null;
		switch (sort) {
		case "timeUp":
			addSort="order by time";
			break;
		case "timeDown":
			addSort="order by time DESC";
			break;
		case "priceUp":
			addSort="order by goodsPrice";
			break;
		case "priceDown":
			addSort="order by goodsPrice DESC";
			break;
		default:
			break;
		}
		ArrayList<GoodBean> arrayList=recGoods.getAllGoodsCards(beginNum, num,type,addSort);
		Map<String, Object> cards=new HashMap<String,Object>();
		//cards.put("count", count);
		for(int j=0;j<arrayList.size();j++) {	
			GoodBean goodBean=arrayList.get(j);
			Map<String, String> good=new HashMap<String ,String>();
			if(goodBean.getGoodsImg()==null) {
				good.put("goodsImg", "");
			}else {
				good.put("goodsImg", (goodBean.getGoodsImg()).split("###")[0]);
			}
			good.put("title", goodBean.getTitle());
			good.put("userName", goodBean.getUserName());
			System.out.println(goodBean.getGoodsPrice());
			if(goodBean.getGoodsPrice()==null) {
				good.put("goodsPrice", "");
			}else {
				good.put("goodsPrice", Float.parseFloat(goodBean.getGoodsPrice()+"")+"");
			}
			good.put("goodsId", goodBean.getGoodsId());
			cards.put("card"+(j+1), good);
		}
		return cards;
	}
	
	public Map<String ,Object> getGoodsCard(String beginNum){
		return this.getGoodsCard(beginNum, pageCount+"",null,null);
	}
	
	public int getLimitCardsCount(String type,String sort) {
		String addSort=null;
		switch (sort) {
		case "timeUp":
			addSort="order by time DESC";
			break;
		case "timeDown":
			addSort="order by time";
			break;
		case "priceUp":
			addSort="order by goodsPrice DESC";
			break;
		case "priceDown":
			addSort="order by goodsPrice";
			break;
		default:
			break;
		}
		return recGoods.getLimitCardCount(type, addSort);
	}
	
	public int getTitleCardsCount(String info,String type,String sort) {
		String[] tempInfo=info.split("");
		String addSql="%";
		for(int i=0;i<tempInfo.length;i++) {
			addSql+=tempInfo[i]+"%";
		}
		String addSort=null;
		switch (sort) {
		case "timeUp":
			addSort="order by time DESC";
			break;
		case "timeDown":
			addSort="order by time";
			break;
		case "priceUp":
			addSort="order by goodsPrice DESC";
			break;
		case "priceDown":
			addSort="order by goodsPrice";
			break;
		default:
			break;
		}
		return recGoods.titleFindCardsCount(addSql,type,addSort);
	}
}
