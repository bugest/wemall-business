package com.wemall.activemq.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wemall.user.service.IUserService;
import com.wemall.wallet.service.WalletDetailService;
import com.wemall.wallet.service.WalletService;

public class HongBaoToWriteWallet implements MessageListener{
	 
	@Autowired
	private IUserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private WalletDetailService walletDetailService;
	
	@Transactional
	public void onMessage(Message message) {
		//try {
			try {
				ActiveMQTextMessage mqmsg = (ActiveMQTextMessage) message;
				String text = mqmsg.getText();
				if (text != null) {
					JSONObject msgjson = JSON.parseObject(text);
					String amount = msgjson.getString("amount");
					String scource_id = msgjson.getString("scource_id");
					String scource_type = msgjson.getString("scource_type");
					String wallet = msgjson.getString("wallet");
					//首先给给主表增加金额
					
					//给子表增加明细
					
					//记录消费的id和消息号
					walletService.walletAddMoney(amount, wallet, scource_id, scource_type);
				}
				SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String date=sDateFormat.format(new Date(message.getJMSTimestamp()));
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}