package com.four.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.four.dao.repairForm;
import com.four.javaBean.RepairBean;
import com.four.javaBean.UserLoginBean;
import com.four.util.C3p0Utils;
import com.four.util.jdbcUtils;

public class RepairFormImp implements repairForm{
	
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private CallableStatement cs=null;

	@Override
	public boolean saveRepair(RepairBean repair) {
		boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="insert into repairform(stuNum,dormitory,trueName,repairTime,phone,content,doorTime,repairState)"
					+ "values(?,?,?,?,?,?,?,?)";
			ps=ct.prepareStatement(sql);
			ps.setString(1, repair.getStuNum());
			ps.setString(2, repair.getDomc());
			ps.setString(3, repair.getName());
			repair.setRepairDate(repair.getDate());
			Timestamp t = new Timestamp(repair.getRepairDate().getTime());
			ps.setTimestamp(4, t);
			ps.setString(5, repair.getTel());
			ps.setString(6, repair.getContent());
			ps.setString(7, repair.getTime());
			ps.setString(8, "0");
			ps.executeUpdate();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return false;
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return result;
	}
	
	public ArrayList<RepairBean> getOtherCards(ArrayList<String[]> cardList,int state){
		if(cardList.size()!=0) {
			ArrayList<RepairBean> repairCard=new ArrayList<RepairBean>();
			String repairState=state+"";
			
			for(int i=0;i<cardList.size();i++) {
				String[] card=cardList.get(i);
				if(!card[2].equals(repairState)) {
					//System.out.println(card[2]+"   "+repairState);
					continue;
				}
				try {
					ct=C3p0Utils.getConnection();
					String sql="select stuNum,dormitory,truename,	phone,	content,repairTime,doorTime,repairState ,formId from " + 
							"	 repairform where repairState=? and stuNum=? and formId=?";
					ps=ct.prepareStatement(sql);
					ps.setString(1, repairState);
					ps.setString(2, card[3]);
					ps.setString(3, card[0]);
					rs=ps.executeQuery();
					if(rs.next()) {
						RepairBean reBean=new RepairBean();
						reBean.setStuNum(rs.getString(1));
						reBean.setDomc(rs.getString(2));
						reBean.setName(rs.getString(3));
						reBean.setTel(rs.getString(4));
						reBean.setContent(rs.getString(5));
						reBean.setRepairDate(new Date(rs.getTimestamp(6).getTime()));
						reBean.setTime(rs.getString(7));
						reBean.setState(rs.getString(8));
						reBean.setFormId(rs.getString(9));
						repairCard.add(reBean);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					repairCard=null;
				}finally {
					C3p0Utils.close(ct, ps, rs);
				}
			}
			//System.out.println(repairCard.size());
			return repairCard;
		}
		return null;
	}

	@Override
	public ArrayList<RepairBean> getRanking(ArrayList<String[]> cardList,int state) {
		/*for(int i=0;i<cardList.size();i++) {
			System.out.println(cardList.get(i)[2]);
		}*/
		if(cardList.size()!=0) {
			ArrayList<RepairBean> repairCard=new ArrayList<RepairBean>();
			String repairState=state+"";
			for(int i=0;i<cardList.size();i++) {
				String[] card=cardList.get(i);
				if(!card[2].equals(repairState)) {
					continue;
				}
				try {
					ct=C3p0Utils.getConnection();
					String sql="select * from(" + 
							"	select (@curRank := @curRank + 1)as ranking , stuNum,dormitory,truename,	phone,	content,repairTime,doorTime,repairState ,formId from (" + 
							"		select *,@curRank := 0 from repairform where doorTime=? and repairState=?" + 
							"	)as a order by repairTime" + 
							")as b where stuNum=? and formId=?";
					ps=ct.prepareStatement(sql);
					ps.setString(1, card[1]);
					ps.setString(2, repairState);
					ps.setString(3, card[3]);
					ps.setString(4, card[0]);
					rs=ps.executeQuery();
					if(rs.next()) {
						RepairBean reBean=new RepairBean();
						reBean.setRanking(rs.getInt(1)+"");
						reBean.setStuNum(rs.getString(2));
						reBean.setDomc(rs.getString(3));
						reBean.setName(rs.getString(4));
						reBean.setTel(rs.getString(5));
						reBean.setContent(rs.getString(6));
						reBean.setRepairDate(new Date(rs.getTimestamp(7).getTime()));
						reBean.setTime(rs.getString(8));
						reBean.setState(rs.getString(9));
						reBean.setFormId(rs.getString(10));
						repairCard.add(reBean);
					}					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					repairCard=null;
				}finally {
					C3p0Utils.close(ct, ps, rs);
				}
			}
			return repairCard;
			
		}
		return null;
	}

	@Override
	public ArrayList<String[]> getFromNum(UserLoginBean user) {
		ArrayList<String[]> cardList=new ArrayList<String[]>();
		String stuNum=user.getStuId();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select formId,doorTime,repairState from repairform where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			rs=ps.executeQuery();
			while(rs.next()) {
				/* 
				 * card[0]=formId,
				 * card[1]=doorTime,
				 * card[2]=repairState,
				 * card[3]=stuNum
				 */
				String[] card=new String[4];
				card[0]=rs.getString(1);
				card[1]=rs.getString(2);
				card[2]=rs.getString(3);
				card[3]=stuNum;
				//System.out.println(card[0]+" "+card[1]+" "+card[2]+" "+card[3]);
				cardList.add(card);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		//System.out.println(cardList.size());
		// TODO Auto-generated method stub
		return cardList;
	}

	@Override
	public int getRepairFormNum(UserLoginBean user) {
		String stuNum=user.getStuId();
		int count=-1;
		try {
			ct=C3p0Utils.getConnection();
			String sql="select count(*)as count from repairform where stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			rs=ps.executeQuery();
			if(rs.next()) {
				count=Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return count;
	}

	@Override
	public boolean deleteRepairForm(UserLoginBean user, String formId) {
		boolean result=false;
		String stuNum=user.getStuId();
		try {
			ct=C3p0Utils.getConnection();
			String sql="delete from repairform where stuNum=? and formId=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, stuNum);
			ps.setInt(2, Integer.parseInt(formId));
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
	
	public boolean changeState(String stuNum,String formId,String state) {
		Boolean result=false;
		try {
			ct=C3p0Utils.getConnection();
			String sql="UPDATE repairform set repairState=? where formId=? and stuNum=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, state);
			ps.setString(2, formId);
			ps.setString(3, stuNum);
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
	
	public ArrayList<RepairBean> getAllCard(String state){
		ArrayList<RepairBean> cardList=new ArrayList<RepairBean>();
		try {
			ct=C3p0Utils.getConnection();
			String sql="select truename,phone,dormitory,repairTime,content,doorTime from repairform where repairState=?";
			ps=ct.prepareStatement(sql);
			ps.setString(1, state);
			rs=ps.executeQuery();
			while(rs.next()) {
				RepairBean repairBean=new RepairBean();
				repairBean.setName(rs.getString(1));
				repairBean.setTel(rs.getString(2));
				repairBean.setDomc(rs.getString(3));
				repairBean.setRepairDate(new Date(rs.getTimestamp(4).getTime()));
				repairBean.setContent(rs.getString(5));
				repairBean.setTime(rs.getString(6));
				//System.out.println(card[0]+" "+card[1]+" "+card[2]+" "+card[3]);
				cardList.add(repairBean);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			C3p0Utils.close(ct, ps, rs);
		}
		return cardList;
	}
}
