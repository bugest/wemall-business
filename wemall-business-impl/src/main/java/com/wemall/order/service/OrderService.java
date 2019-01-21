package com.wemall.order.service;

import com.wemall.order.entity.Order;
import com.wemall.pub.tools.Result;

public interface OrderService {
	public Result<Order> commitOrder(Order order);
}
