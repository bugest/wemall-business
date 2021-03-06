package com.wemall.activemq.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.user.service.IUserService;

public class MyMessageListener implements MessageListener{
	 
	@Autowired
	private IUserService userService;
	
	@Transactional
	public void onMessage(Message message) {
		//try {
			try {
				SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				String date=sDateFormat.format(new Date(message.getJMSTimestamp()));
				ActiveMQTextMessage a = new ActiveMQTextMessage();
				String text = ((ActiveMQTextMessage) message).getText();
				String text1 = ((ActiveMQTextMessage) message).getText();
				//(ActiveMQTextMessage) message
				//Map<String, Object> map = ((ActiveMQTextMessage) message).getContentMap();
				//Serializable map1 = ((ActiveMQObjectMessage) message).getObject();
				//List<Shop> shop = (List<Shop>) map1;
				//System.out.println(shop.get(0).getName());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//throw new RuntimeException(); 
			
			//throw new RuntimeException(); 
/*			User user = new User();
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setId(null);
			user.setUserAccount("hello3");
			userService.insert(user);
			System.out.println(message);
			message.getPropertyNames();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//message.acknowledge();
			System.out.println("---------------------------------" + message.getObjectProperty("key"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*throw new RuntimeException();*/
	}
}