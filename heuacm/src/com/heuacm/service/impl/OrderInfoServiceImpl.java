package com.heuacm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.OrderInfoMapper;
import com.heuacm.pojo.OrderInfo;
import com.heuacm.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	OrderInfoMapper orderInfoMapper;
	public void orderInfoAdd(OrderInfo orderInfo) {
		orderInfoMapper.add(orderInfo);
	}

	public void OrderInfoDelete(String ordernum) {
		orderInfoMapper.delete(ordernum);
	}

	public void OrderInfoUpdate(OrderInfo orderInfo) {
		orderInfoMapper.update(orderInfo);
	}

	public OrderInfo orderInfoGet(String ordernum) {
		return orderInfoMapper.get(ordernum);
	}

}
