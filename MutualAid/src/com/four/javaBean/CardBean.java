package com.four.javaBean;

import java.util.Date;

public class CardBean<T>{
	Date time;
	T card;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public T getCard() {
		return card;
	}
	public void setCard(T card) {
		this.card = card;
	}
	
}
