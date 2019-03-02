package com.wemall.wallet.service;

import java.math.BigDecimal;

import com.wemall.wallet.entity.WalletDetail;

public interface WalletDetailService {
	WalletDetail selectByPrimaryKey(Integer id);
	
	int insertWalletDetail(Integer wallet, String type, String name, BigDecimal amonut);
}
