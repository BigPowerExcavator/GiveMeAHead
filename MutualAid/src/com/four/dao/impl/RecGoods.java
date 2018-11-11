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
			String sql="select * from idlegoods order by time DESC limit ?,?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, beginNum);
			ps.setString(2, num);
			rs=ps.executeQuery();
			while(rs.next()) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cards;
	}
}
