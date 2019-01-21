package com.wemall.kill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.kill.entity.KillInfo;
import com.wemall.kill.service.KillService;
import com.wemall.order.entity.Order;
import com.wemall.pub.tools.Result;;

@Controller
@RequestMapping("kill")
public class KillController {
	
	@Autowired
	private KillService killService;
	
	@RequestMapping("killit")
	@ResponseBody
	public Result<Order> killIt(@RequestParam(required = true)Integer killInfoId, @RequestParam(required = true)Integer killInfoDetailId, @RequestParam(required = true)Integer killGoodId, Integer count, @RequestParam(required = true)Integer customId) {
		Result<Order> result = killService.killIt(killInfoId, killInfoDetailId, killGoodId, count, customId);
		return result;
	} 
	@RequestMapping("killInfo")
	@ResponseBody
	public void setKillInfo(@RequestBody KillInfo killInfo) {
		killService.setKillInfo(killInfo);
	} 
}
