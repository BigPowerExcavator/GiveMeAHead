package com.four.util;

import java.util.Date;

/*
 * һ����Ƭ�࣬�������洴����Ƭ��ʱ��data�Ϳ�Ƭ��Ӧ��bean
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
