package com.four.util;

public class getTrueGoodType {
	
	String variety=null;
	public String getGoodType(String goodsType) {
		if(goodsType!=null||!("").equals(goodsType)) {
			switch (goodsType) {
			case "1":
				this.variety="������Ʒ";
				break;
			case "2":
				this.variety="�鼮�ľ�";
				break;
			case "3":
				this.variety="Ь������";
				break;
			case "4":
				this.variety="��ױ��Ʒ";
				break;
			case "5":
				this.variety="ʳƷҩƷ";
				break;
			case "6":
				this.variety="������Ʒ";
				break;
			case "7":
				this.variety="����";
				break;
			}
		}
		return variety;
	}
}
