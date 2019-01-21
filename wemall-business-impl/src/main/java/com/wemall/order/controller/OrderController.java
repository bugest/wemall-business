package com.wemall.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wemall.order.entity.Order;
import com.wemall.order.service.OrderService;
import com.wemall.pub.tools.Result;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService OrderService;
	@RequestMapping("commitOrder")
	public Result<Order> commitOrder(@RequestBody Order order) {
		Result<Order> commitOrder = OrderService.commitOrder(order);
		return commitOrder;
	}
}
