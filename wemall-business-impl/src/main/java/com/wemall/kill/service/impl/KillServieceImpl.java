package com.wemall.kill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.good.entity.Good;
import com.wemall.kill.entity.KillInfo;
import com.wemall.kill.entity.KillInfoDetail;
import com.wemall.kill.service.KillService;
import com.wemall.order.entity.Order;
import com.wemall.order.entity.OrderDetail;
import com.wemall.pub.tools.Result;
import com.wemall.redis.service.impl.RedisTemplateUtil;

@Service
public class KillServieceImpl implements KillService {

	@Autowired
	private RedisTemplateUtil redisTemplateUtil;

	public Result<Order> killIt(Integer killInfoId, Integer killInfoDetailId, Integer killGoodId, Integer count,
			Integer customId) {
		Result<Order> result = new Result<Order>();
		// 判断是否有相关的秒杀活动
		if (!checkKill(killInfoId, killInfoDetailId, killGoodId, result, count)) {
			return result;
		}
		// 生成预订单
		KillInfoDetail killInfoDetail = new KillInfoDetail();
		killInfoDetail.setId(killInfoDetailId);
		Good good = new Good();
		good.setId(killGoodId);
		OrderDetail preOrderDetail = new OrderDetail();
		preOrderDetail.setGood(good);
		preOrderDetail.setKillInfoDetail(killInfoDetail);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		list.add(preOrderDetail);
		Order order = new Order();
		order.setOrderDetail(list);
		order.setCustom(customId);
		result.setResultCode(Result.SUCCESS);
		result.setData(order);
		return result;
		/*
		 * //生成订单，先排除重复 String uniqueKill = "killunique" + ":" + killInfoId + "-" +
		 * customId + "-" + killGoodId; if (redisTemplateUtil.setIfAbsent(uniqueKill,
		 * new Integer(1))) { PreOrder order = new PreOrder();
		 * order.setCustom(customId); result.setResultCode(Result.SUCCESS);
		 * result.setData(order); return result; } else {
		 * result.setResultCode(Result.FAIL); result.setMsg("不能重复秒杀一件商品"); return
		 * result; }
		 */
	}

	public boolean checkKill(Integer killInfoId, Integer killInfoDetailId, Integer killGoodId, Result<Order> result, Integer num, Integer customId) {
		boolean checkKill = checkKill(killInfoId, killInfoDetailId, killGoodId, result, num);
		if (!checkKill) {
			return false;
		}
		//生成订单，先排除重复
		String uniqueKill = "killunique" + ":" + killInfoId + "-" + killInfoDetailId + "-" + killGoodId + "-" + customId;
		if (redisTemplateUtil.setIfAbsent(uniqueKill, new Integer(1))) {
			return true;
		} else {
			result.setResultCode(Result.FAIL);
			result.setMsg("不能重复秒杀一件商品");
			return false;		
		}
	}

	private boolean checkKill(Integer killInfoId, Integer killInfoDetailId, Integer killGoodId, Result<Order> result, Integer num) {
		Object killInfoobject = redisTemplateUtil.get("killinfo:" + killInfoId);
		if (killInfoobject == null) {
			result.setResultCode(Result.FAIL);
			result.setMsg("没有找到秒杀活动信息");
			return false;
		}
		if (!(killInfoobject instanceof KillInfo)) {
			result.setResultCode(Result.FAIL);
			result.setMsg("找到的秒杀信息出现问题");
			return false;
		}
		KillInfo killInfo = (KillInfo) killInfoobject;
		Date begintime = killInfo.getBegintime();
		Date endtime = killInfo.getEndtime();
		if (begintime == null || begintime == null) {
			result.setResultCode(Result.FAIL);
			result.setMsg("秒杀活动时间有出现问题");
			return false;
		}
		Date now = new Date();
		if (!(now.before(endtime) && now.after(begintime))) {
			result.setResultCode(Result.FAIL);
			result.setMsg("不在秒杀活动时间范围内");
			return false;
		}
		// 判断秒杀是不是在秒杀范围内
		List<KillInfoDetail> killInfoDetails = killInfo.getKillInfoDetail();
		boolean isDetailInKill = false;
		KillInfoDetail killInfoDetailselected = null;
		for (KillInfoDetail killInfoDetail : killInfoDetails) {
			if (killInfoDetailId.equals(killInfoDetail.getId())) {
				isDetailInKill = true;
				killInfoDetailselected = killInfoDetail;
				break;
			}
		}
		if (!isDetailInKill) {
			result.setResultCode(Result.FAIL);
			result.setMsg("秒杀明细不属于秒杀");
			return false;
		}
		if (!killInfoDetailselected.getGood().getId().equals(killGoodId)) {
			result.setResultCode(Result.FAIL);
			result.setMsg("所选商品不属于本次秒杀");
			return false;
		}
		Integer canBuy = killInfoDetailselected.getNum();
		if ((num == null ? 1 : num) > canBuy) {
			result.setResultCode(Result.FAIL);
			result.setMsg("所买的商品数量大于可以秒杀的数量");
			return false;
		}
		return true;
	}

	public KillInfo setKillInfo(KillInfo killInfo) {
		redisTemplateUtil.set("killinfo:" + killInfo.getId(), killInfo);
		return (KillInfo) redisTemplateUtil.get("killinfo-" + killInfo.getId());
	}

}
