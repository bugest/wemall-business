package com.wemall.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wemall.kill.service.KillService;
import com.wemall.order.entity.Order;
import com.wemall.order.entity.OrderDetail;
import com.wemall.order.service.OrderService;
import com.wemall.pub.tools.Result;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired 
	private KillService killService;
	public Result<Order> commitOrder(@RequestBody Order order) {
		List<OrderDetail> orderDetails = order.getOrderDetail();
		Integer custom = order.getCustom();
		Result<Order> result = new Result<Order>();
		for (OrderDetail orderDetail :orderDetails) {
			//有秒杀信息 就得判断秒杀是不是有问题
			if (orderDetail != null && orderDetail.getKillInfoDetail() != null) {
				Integer killInfoId = orderDetail.getKillInfoDetail().getKillInfo().getId();
				Integer killInfoDetailId = orderDetail.getId();
				Integer foodId = orderDetail.getKillInfoDetail().getGood().getId();
				Integer buynum = orderDetail.getNum();
				if (!killService.checkKill(killInfoId, killInfoDetailId, foodId, result, buynum, custom)) {
					return result;
				}
			}
			
		}
		result.setResultCode(Result.SUCCESS);
		result.setData(order);
		return result;
	}
}
