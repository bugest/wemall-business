package com.wemall.activemq.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class MyMessageListener implements MessageListener{
	 
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			message.getPropertyNames();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("---------------------------------" + message.getObjectProperty("key"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}