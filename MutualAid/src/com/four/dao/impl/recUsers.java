package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.Users;
import com.four.javaBean.UserRegisterBean;
import com.four.javaBean.UsersBean;
import com.four.util.C3p0Utils;
import com.four.util.jdbcUtils;

public class recUsers implements Users{
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;

	@Override
	public UsersBean getPasswd(int stuId) {
		UsersBean user=new UsersBean();
		String passwd=null;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select passwd from users where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuId+"");
			rs=ps.executeQuery();
			if(rs.next()) {
				passwd=rs.getString(1);
				user.setPassword(passwd);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return user;
	}

	@Override
	public boolean checkUserName(String trueName) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="select truename from users where truename=?";
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

	@Override
	public boolean userRegister(UserRegisterBean user) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="insert into users(stuNum,truename,passwd,phone)values(?,?,?,?)";
			ps=ct.prepareStatement(sql);
			ps.setString(1, user.getStuId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getTel());
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

	@Override
	public boolean changeUserHeadImg(String imgPart,String stuId) {
		boolean result=false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="UPDATE users SET userImg=? where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, imgPart);
			ps.setString(2, stuId);
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 修改用户信息
	 * @author 76429
	 * 
	 */
	@Override
	public boolean upadteInfo(int stuNum, String userName, String trueName, String sex,String dormitory,String phone,int stuId) {
		// TODO Auto-generated method stub
		boolean flag= false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="update users set stuNum=?, userName=?,trueName=?,sex=?,dormitory=?,phone=? where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setInt(1, stuNum);
			ps.setString(2, userName);
			ps.setString(3, trueName);
			ps.setString(4, sex);
			ps.setString(5,dormitory );
			ps.setString(6, phone);
			ps.setInt(7, stuId);
			int num=ps.executeUpdate();
			if(num==1){
				//修改成功
				flag=true;	
			}			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}

	@Override
	public boolean ProveUser(int i) {
		boolean flag = false;
		int numUser=0;  //用于记录数据库中有没该用户
		try {
			ct=jdbcUtils.getConnection();
			String sql="select count(*) from users where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
			if(rs.next()) {
				numUser= rs.getInt(1);
			}
			if(numUser==0){
				//可以注册 或  可以修改
				flag=true;								
			}			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}
	
	@Override
	public boolean updatePasswd(int stuNum, String passwd) {
		boolean flag= false;
		try {
			ct=jdbcUtils.getConnection();
			String sql="update users set passwd=? where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, passwd);
			ps.setInt(2, stuNum);
			
			int num=ps.executeUpdate();
			if(num==1){
				//修改成功
				flag=true;	
			}			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return flag;
	}
	
	@Override
	public Map<String, String> Personal(String stuNum) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select stuNum,dormitory,trueName,userName,sex,phone from users where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			rs=ps.executeQuery();
			if(rs.next()) {
				map.put("stuNum", rs.getString(1));
				map.put("dormitory", rs.getString(2));
				map.put("userDomc",rs.getString(2));
				map.put("trueName",rs.getString(3));
				map.put("userName",rs.getString(4));
				map.put("sex", rs.getString(5));
				map.put("phone",rs.getString(6));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}	
		return map;
	}
	
	public String getUserImg(String stuNum) {
		String url=null;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select userImg from users where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
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
	/**
	 * @author 76429
	 * 限制交易
	 * 根据学号获取用户名
	 */
	public String getName(String stuNum) {
		String userName="";
		try {
			ct=C3p0Utils.getConnection();
			String sql="select userName from users where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			rs=ps.executeQuery();
			if(rs.next()) {
				userName=rs.getString(1);
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return userName;
		
	}
	
}
