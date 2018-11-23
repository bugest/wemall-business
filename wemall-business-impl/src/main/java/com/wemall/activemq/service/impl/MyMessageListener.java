package com.wemall.activemq.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

public class MyMessageListener implements MessageListener{
	 
	@Autowired
	private IUserService userService;
	
	@Transactional
	public void onMessage(Message message) {
		//try {
			try {
				Map<String, Object> map = ((ActiveMQMapMessage) message).getContentMap();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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