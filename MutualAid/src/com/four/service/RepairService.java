package com.four.service;

import java.util.Map;

import com.four.javaBean.UserLoginBean;
import com.four.javaBean.RepairBean;

public interface RepairService {
	boolean writeRepairCard(RepairBean repair);
	Map<?,?> getCardJsonUnfinished(UserLoginBean user);
}
