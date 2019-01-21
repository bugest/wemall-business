package com.wemall.kill.service;

import com.wemall.kill.entity.KillInfo;
import com.wemall.order.entity.Order;
import com.wemall.pub.tools.Result;

public interface KillService {

	Result<Order> killIt(Integer killInfoId, Integer killInfoDetailId, Integer killGoodId, Integer count, Integer customId);

	KillInfo setKillInfo(KillInfo killInfo);
	public boolean checkKill(Integer killInfoId, Integer killInfoDetailId, Integer killGoodId, Result<Order> result, Integer num, Integer customId);
}
