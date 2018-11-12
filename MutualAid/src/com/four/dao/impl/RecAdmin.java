package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.admin;
import com.four.javaBean.AdminBean;
import com.four.javaBean.AdminLoginBean;
import com.four.javaBean.UserRegisterBean;
import com.four.util.C3p0Utils;
import com.four.util.jdbcUtils;

public class RecAdmin implements admin {
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private CallableStatement cs = null;

	// 以前的代码
	/*
	 * public String getAdminPasswd(AdminLoginBean admin) { String password=null;
	 * try { ct=C3p0Utils.getConnection(); String
	 * sql="select passwd from admin where adminNum=?"; ps=ct.prepareStatement(sql);
	 * ps.setString(1, admin.getPassword()); rs=ps.executeQuery(); if(rs.next()) {
	 * password=rs.getString(1); } } catch (Exception e) { // TODO: handle exception
	 * e.printStackTrace(); }finally { C3p0Utils.close(ct, ps, rs); } return
	 * password; }
	 */
	/**
	 * @author jinner
	 */
	@Override
	public boolean LoginIn(String stuNum, String passwd) {
		boolean flag = false;
		try {
			ct = C3p0Utils.getConnection();
			String sql = "select passwd from admin where adminNum=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				String password = rs.getString(1);
				if (password.equals(passwd)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}

	@Override
	public Map<String, Object> getAdminInfo(String adminNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ct = jdbcUtils.getConnection();
			String sql = "select adminNum,grade,trueName,adminName,sex,phone from admin where adminNum=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, adminNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				int index = 0;
				map.put("adminNum", String.valueOf(rs.getInt(++index)));
				map.put("grade", rs.getString(++index));
				map.put("trueName", rs.getString(++index));
				map.put("userName", rs.getString(++index));
				map.put("sex", rs.getString(++index));
				map.put("phone", rs.getString(++index));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return map;
	}

	@Override
	public boolean ProveAdmin(String adminNum) {
		boolean flag = false;
		int adminSum = 0; // 用于记录数据库中有没该用户
		try {
			ct = jdbcUtils.getConnection();
			String sql = "select count(*) from admin where adminNum=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, adminNum);
			rs = ps.executeQuery();
			if (rs.next()) {
				adminSum = rs.getInt(1);
			}
			if (adminSum == 0) {
				// 可以注册 或 可以修改
				flag = true;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}

	@Override
	public boolean upadteAdminInfo(String adminNum, String userName, String trueName, String sex, String grade,
			String phone, String adminId) {
		boolean flag = false;
		try {
			ct = jdbcUtils.getConnection();
			String sql = "update admin set adminNum=?, adminName=?,trueName=?,sex=?,grade=?,phone=? where adminNum=?";
			ps = ct.prepareStatement(sql);
			int index = 0;
			ps.setString(++index, adminNum);
			ps.setString(++index, userName);
			ps.setString(++index, trueName);
			ps.setString(++index, sex);
			ps.setString(++index, grade);
			ps.setString(++index, phone);
			ps.setString(++index, adminId);
			int num = ps.executeUpdate();
			if (num == 1) {
				// 修改成功
				flag = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}

	@Override
	public boolean UpdatePasswd(String adminNum, String passwd) {
		boolean flag = false;
		try {
			ct = jdbcUtils.getConnection();
			String sql = "update admin set passwd=? where adminNum=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, passwd);
			ps.setString(2,adminNum);

			int num = ps.executeUpdate();
			if (num == 1) {
				// 修改成功
				flag = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}

	/*
	 * public boolean adminRegister(AdminBean admin) { boolean result=false; try {
	 * ct=jdbcUtils.getConnection(); String
	 * sql="insert into admin(adminNum,truename,passwd,phone)values(?,?,?,?)";
	 * ps=ct.prepareStatement(sql); ps.setString(1, admin.getAdminNum());
	 * ps.setString(2, admin.getTrueName()); ps.setString(3, admin.getPasswd());
	 * ps.setString(4, admin.getPhone()); ps.executeUpdate(); result=true; } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }finally { C3p0Utils.close(ct, ps, rs); } return result; }
	 * 
	 * public boolean changeAdminHeadImg(String imgPart,String adminNum) { boolean
	 * result=false; try { ct=jdbcUtils.getConnection(); String
	 * sql="UPDATE admin SET adminImg=?"; ps=ct.prepareStatement(sql);
	 * ps.setString(1, adminNum); ps.executeUpdate(); result=true; } catch
	 * (Exception e) { // TODO: handle exception e.printStackTrace(); } return
	 * result; }
	 */

	/*
	 * public Map<String, Object> Personal(String adminNum) { // TODO Auto-generated
	 * method stub Map<String,Object> map = new HashMap<String,Object>(); try {
	 * ct=jdbcUtils.getConnection(); String
	 * sql="select dormitory,trueName,phone from repairForm where stuNum=?";
	 * ps=ct.prepareStatement(sql); ps.setString(1, adminNum); rs=ps.executeQuery();
	 * if(rs.next()) { map.put("userDomc",rs.getString(1));
	 * map.put("userName",rs.getString(2)); map.put("phone",rs.getString(3)); } }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { C3p0Utils.close(ct, ps, rs); } return map; }
	 */
}
