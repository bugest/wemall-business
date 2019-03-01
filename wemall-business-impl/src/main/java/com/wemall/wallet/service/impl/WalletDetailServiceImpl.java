package com.wemall.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.wallet.dao.WalletDetailDao;
import com.wemall.wallet.entity.WalletDetail;
import com.wemall.wallet.service.WalletDetailService;

@Service
public class WalletDetailServiceImpl implements WalletDetailService {

	@Autowired
	private WalletDetailDao walletDetailDao; 
	public WalletDetail selectByPrimaryKey(Integer id) {
		return walletDetailDao.selectByPrimaryKey(id);
	}

}
