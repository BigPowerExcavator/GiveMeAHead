package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.four.javaBean.GoodBean;
import com.four.util.C3p0Utils;

public class RecGoods {
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;
	
	public ArrayList<GoodBean> getAllGoodsCards(String beginNum,String num){
		ArrayList<GoodBean> cards=new ArrayList<GoodBean>();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select title,goodsImg,userName,goodsId from idlegoods order by time DESC limit ?,?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, beginNum);
			ps.setString(2, num);
			rs=ps.executeQuery();
			while(rs.next()) {
				GoodBean goodBean=new GoodBean();
				goodBean.setTitle(rs.getString(1));
				goodBean.setGoodsImg(rs.getString(2));
				goodBean.setUserName(rs.getString(3));
				goodBean.setGoodsId(rs.getString(4));
				cards.add(goodBean);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return cards;
	}
	
	public GoodBean getGoodInfo(String goodId) {
		GoodBean goodBean=new GoodBean();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select * from idlegoods where goodsId=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, goodId);
			rs=ps.executeQuery();
			if(rs.next()) {
				goodBean.setGoodsId(rs.getString(1));
				goodBean.setStuNum(rs.getString(2));
				goodBean.setGoodsName(rs.getString(3));
				goodBean.setGoodsImg(rs.getString(4));
				goodBean.setGoodsType(rs.getString(5));
				goodBean.setGoodsPrice(rs.getString(6));
				goodBean.setTitle(rs.getString(7));
				goodBean.setTime(rs.getString(8));
				goodBean.setGoodsIntro(rs.getString(9));
				goodBean.setUserName(rs.getString(10));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return goodBean;
	}
	
	public boolean changeGoodsInfo(GoodBean goodBean) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="update idlegoods set goodsId=?,stuNum=?,goodsName=?,goodsImg=?,goodsType=?,goodsPrice=?,titile=?,time=?,goodsIntro=?,userName=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, goodBean.getGoodsId());
			ps.setString(2, goodBean.getStuNum());
			ps.setString(3, goodBean.getGoodsName());
			ps.setString(4, goodBean.getGoodsImg());
			ps.setString(5, goodBean.getGoodsType());
			ps.setString(6, goodBean.getGoodsPrice());
			ps.setString(7, goodBean.getTitle());
			ps.setString(8, goodBean.getTime());
			ps.setString(9, goodBean.getGoodsIntro());
			ps.setString(10, goodBean.getUserName());
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
}
