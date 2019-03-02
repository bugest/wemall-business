package com.wemall.wallet.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.wallet.dao.WalletDao;
import com.wemall.wallet.entity.Wallet;
import com.wemall.wallet.service.WalletDetailService;
import com.wemall.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletDao walletDao;
	@Autowired
	private WalletDetailService walletDetailService;
	public Wallet selectByPrimaryKey(Integer id) {
		return walletDao.selectByPrimaryKey(id);
	}
	@Transactional
	public Wallet walletAddMoney(String money, String wallet, String scourceID, String scourceType) {
		HashMap hashMap = new HashMap();
		hashMap.put("id", new Integer(wallet));
		hashMap.put("money", new BigDecimal(money));
		walletDao.walletAddMoney(hashMap);
		walletDetailService.insertWalletDetail(new Integer(wallet), scourceType, "红包" + scourceID, new BigDecimal(money));
		Wallet selectByPrimaryKey = walletDao.selectByPrimaryKey(new Integer(wallet));
		return selectByPrimaryKey;
	}

}
