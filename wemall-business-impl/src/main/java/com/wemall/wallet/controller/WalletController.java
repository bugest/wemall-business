package com.wemall.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.wallet.entity.WalletDetail;
import com.wemall.wallet.service.WalletDetailService;
import com.wemall.wallet.service.WalletService;

@Controller
@RequestMapping("wallet")
public class WalletController {
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletDetailService walletDetailService;
	
	@ResponseBody
	@RequestMapping("walletDetail")
	public WalletDetail selectWalletDetailById(Integer id) {
		return walletDetailService.selectByPrimaryKey(id);
	}
}
