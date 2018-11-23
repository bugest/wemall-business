package com.wemall.activemq.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.activemq.service.MessageService;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsOperations jmsOperations;
	
	@Autowired
	private IUserService userService;
	
	private int i = 0;
	
	/* (非 Javadoc) 
	* <p>Title: sendMessage</p> 
	* <p>Description: </p> 
	* @param msg 
	* @see com.wemall.activemq.service.MessageService#sendMessage(java.lang.String) 
	*/
	@Transactional
	public void sendMessage(String destination ,Object msg) {
		/*
		 * jmsOperations.send("biz1.queue", new MessageCreator() { public Message
		 * createMessage(Session session) throws JMSException { return
		 * session.createTextMessage(msg); } });
		 */
		// 发消息 比上边的方法简单一些
		//User user = new User();
		if (i == 0) {
			i++;
		}
		User user = userService.selectByPrimaryKey(new Long(2));
		user.setId(null);
		user.setUserAccount("hello101");
		/*int i = userService.insert(user);*/
		i = 1;
		if (i > 0) {
			jmsOperations.convertAndSend(destination, msg);
		}
		//throw new RuntimeException();
	}

	/* (非 Javadoc) 
	* <p>Title: receive</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.wemall.activemq.service.MessageService#receive() 
	*/
	public Object receive(String destination) {
		/*try {
			ObjectMessage message = (ObjectMessage) jmsOperations.receive();
			return (String) message.getObject();
		} catch (JMSException e) {
			e.printStackTrace();
			throw JmsUtils.convertJmsAccessException(e);
		}*/
		return jmsOperations.receiveAndConvert(destination);
	}

}
