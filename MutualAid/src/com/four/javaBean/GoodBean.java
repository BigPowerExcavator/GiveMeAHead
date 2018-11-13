package com.four.javaBean;

public class GoodBean {
	private String goodsId;
	private String stuNum;
	private String goodsName;
	private String goodsImg;
	private String goodsType;
	private String goodsPrice;
	private String title;
	private String time;
	private String goodsIntro;
	private String userName;
	//该属性为商品类型为代号的真实类别
	private String variety;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getVariety() {
		switch (goodsType) {
		case "1":
			this.variety="生活用品";
			break;
		case "2":
			this.variety="书籍文具";
			break;
		case "3":
			this.variety="鞋服配饰";
			break;
		case "4":
			this.variety="美妆用品";
			break;
		case "5":
			this.variety="食品药品";
			break;
		case "6":
			this.variety="数码用品";
			break;
		case "7":
			this.variety="其他";
			break;
		}
		return variety;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGoodsIntro() {
		return goodsIntro;
	}
	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}
	
	
	
}
