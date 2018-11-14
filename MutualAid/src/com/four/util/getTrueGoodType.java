package com.four.util;

public class getTrueGoodType {
	
	String variety=null;
	public String getGoodType(String goodsType) {
		if(goodsType!=null||!("").equals(goodsType)) {
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
		}
		return variety;
	}
}
