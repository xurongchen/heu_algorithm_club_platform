package com.heuacm.service;

import com.heuacm.pojo.OrderInfo;

public interface OrderInfoService {
	void orderInfoAdd(OrderInfo orderInfo);
	void OrderInfoDelete(String ordernum);
	void OrderInfoUpdate(OrderInfo orderInfo);
	OrderInfo orderInfoGet(String ordernum);
}
