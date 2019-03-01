package com.wemall.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.wallet.entity.Wallet;
import com.wemall.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletService walletService;
	public Wallet selectByPrimaryKey(Integer id) {
		return walletService.selectByPrimaryKey(id);
	}

}
