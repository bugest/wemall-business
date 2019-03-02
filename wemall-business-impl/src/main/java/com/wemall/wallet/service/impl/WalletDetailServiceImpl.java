package com.wemall.wallet.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public int insertWalletDetail(Integer wallet, String type, String name, BigDecimal amonut) {
		HashMap hashMap = new HashMap();
		hashMap.put("wallet", wallet);
		hashMap.put("type", type);
		hashMap.put("name", name);
		hashMap.put("amonut", amonut);
		return walletDetailDao.insertWalletDetail(hashMap);
	}

}
