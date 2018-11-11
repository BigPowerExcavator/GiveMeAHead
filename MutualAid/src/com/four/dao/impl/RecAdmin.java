package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.four.javaBean.AdminBean;
import com.four.javaBean.AdminLoginBean;
import com.four.javaBean.UserRegisterBean;
import com.four.util.C3p0Utils;
import com.four.util.jdbcUtils;

public class RecAdmin {
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;
	
	public String getAdminPasswd(AdminLoginBean admin) {
		String password=null;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select passwd from admin where adminNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, admin.getPassword());
			rs=ps.executeQuery();
			if(rs.next()) {
				password=rs.getString(1);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return password;
	}
	
	public boolean checkAdminName(String trueName) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="select truename from admin where truename=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, trueName);
			rs=ps.executeQuery();
			if(rs.next()) {
				result=true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public boolean adminRegister(AdminBean admin) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="insert into admin(adminNum,truename,passwd,phone)values(?,?,?,?)";
			ps=ct.prepareStatement(sql);
			ps.setString(1, admin.getAdminNum());
			ps.setString(2, admin.getTrueName());
			ps.setString(3, admin.getPasswd());
			ps.setString(4, admin.getPhone());
			ps.executeUpdate();
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public boolean changeAdminHeadImg(String imgPart,String adminNum) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="UPDATE admin SET adminImg=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, adminNum);
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/*public Map<String, Object> Personal(String adminNum) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			ct=jdbcUtils.getConnection();
			String sql="select dormitory,trueName,phone from repairForm where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, adminNum);
			rs=ps.executeQuery();
			if(rs.next()) {
				map.put("userDomc",rs.getString(1));
				map.put("userName",rs.getString(2));
				map.put("phone",rs.getString(3));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}	
		return map;
	}*/
}
