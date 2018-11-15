package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
	
	public int getAllGoodsCardsCount() {
		int count=-1;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select count(*) from idlegoods";
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return count;
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
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public boolean writeGoodsCard(GoodBean goodBean) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="intsert into idlegoods(stuNum,goodsName,goodsImg,goodsType,goodsPrice,title,time,goodsIntro,userName)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			ps=ct.prepareStatement(sql);
			ps.setString(1, goodBean.getStuNum());
			ps.setString(2, goodBean.getGoodsName());
			ps.setString(3, goodBean.getGoodsImg());
			ps.setString(4, goodBean.getGoodsType());
			ps.setString(5, goodBean.getGoodsPrice());
			ps.setString(6, goodBean.getTitle());
			@SuppressWarnings("deprecation")
			Timestamp t = new Timestamp(new Long(goodBean.getTime()));
			ps.setTimestamp(7, t);
			ps.setString(8, goodBean.getGoodsIntro());
			ps.setString(9, goodBean.getUserName());
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	/*
	 * @jinner
	 * 修改添加闲置卡片
	 * 加上的
	 * */
	public boolean ApplyIdleCard(String stuNum,String goodsName,String goodsImg,String goodsType,String goodsPrice,String title,String time,String goodsIntro,String userName) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="intsert into idlegoods(stuNum,goodsName,goodsImg,goodsType,goodsPrice,title,time,goodsIntro,userName)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			ps=ct.prepareStatement(sql);
			int index=0;
			ps.setString(++index,stuNum);
			ps.setString(++index, goodsName);
			ps.setString(++index, goodsImg);
			ps.setString(++index, goodsType);
			ps.setString(++index, goodsPrice);
			ps.setString(++index, title);
			ps.setString(++index, time);
			ps.setString(++index, goodsIntro);
			ps.setString(++index,userName);
			ps.executeUpdate();
			//加判断
			int num=ps.executeUpdate();
			if(num==1) {
				result = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public String getGoodsImgs(String goodsId) {
		String url=null;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select goodsImg from idlegoods where goodsId=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, goodsId);
			rs=ps.executeQuery();
			if(rs.next()) {
				url=rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return url;
	}
	
	public boolean changeGoodsImgs(String goodsId,String url) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="update idlegoods set goodsImg=? where goodsId=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, url);
			ps.setString(2, goodsId);
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public boolean deleteGoodCard(String goodsId) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="delete from idlegoods where goodsId=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, goodsId);
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public ArrayList<GoodBean> titleFindCards(String addSql,String beginNum,String num){
		ArrayList<GoodBean> cards=new ArrayList<GoodBean>();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select title,goodsImg,userName,goodsId from idlegoods where title like ? order by time DESC limit ?,?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, addSql);
			ps.setString(2, beginNum);
			ps.setString(3, num);
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
	
	public int titleFindCardsCount(String addSql) {
		int count=-1;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select count(*) from idlegoods where title like ?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, addSql);
			rs=ps.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return count;
	}
	
	
}
