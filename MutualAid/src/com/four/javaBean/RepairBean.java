package com.four.javaBean;

import java.util.Date;
import java.sql.Timestamp;

public class RepairBean {
    private String domc;
    private String Name;
    private String date;
    private Date repairDate;
	private String  time;
	private String tel;
	private String content;
	private String stuNum;
	private String ranking;
	private String state;
	private String formId;
    public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public Date getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	public void setRepairDate(String date) {
		Date repairDate_temp=new Date(Long.parseLong(date));
		this.repairDate=repairDate_temp;
	}
	
	public String getDomc() {
		return domc;
	}
	public void setDomc(String domc) {
		this.domc = domc;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
