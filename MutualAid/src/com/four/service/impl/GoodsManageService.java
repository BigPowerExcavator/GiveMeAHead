package com.four.service.impl;

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
			if(goodBean.getGoodsImg()==null) {
				goodBean.setGoodsImg(recGoods.getGoodsImgs(goodBean.getGoodsId()));
			}
		}
		return result;
	}
}
