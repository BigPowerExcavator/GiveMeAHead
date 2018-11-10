package com.four.util;

import java.util.Date;

/*
 * 一个卡片类，用来储存创建卡片的时间data和卡片相应的bean
 */
public class CardNode<T> {
	
	private Date date;
	private T data;
	
	public CardNode(){
		this(null,null);
	}
	public CardNode(Date date,T data){
		this.date=date;
		this.data=data;
	}
}
