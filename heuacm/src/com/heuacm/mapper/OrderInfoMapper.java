package com.heuacm.mapper;

import com.heuacm.pojo.OrderInfo;

public interface OrderInfoMapper {
	public void add(OrderInfo orderInfo);
	public void delete(String ordernum);
	public void update(OrderInfo orderInfo);
	OrderInfo get(String ordernum);
}
