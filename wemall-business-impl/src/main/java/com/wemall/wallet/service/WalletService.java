package com.wemall.wallet.service;

import com.wemall.wallet.entity.Wallet;

public interface WalletService {
	Wallet selectByPrimaryKey(Integer id);
}
