package com.wemall.activemq.service.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.activemq.service.MessageService;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsOperations;
	
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
	public void sendMessage(String destination ,final Object msg, final String name) {
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
		//User user = userService.selectByPrimaryKey(new Long(2));
		//user.setId(null);
		//user.setUserAccount("hello101");
		/*int i = userService.insert(user);*/
		i = 1;
		if (i > 0) {
			//jmsOperations.setPriority(priority);
			//jmsOperations.convertAndSend(destination, msg);
			//jmsOperations.getpr
			jmsOperations.send(destination, new MessageCreator() {

				public Message createMessage(Session session) throws JMSException {
                    //创建一个消息对象并返回
                    TextMessage textMessage = session.createTextMessage((String)msg);
                    //textMessage.setJMSPriority(priority);
                    textMessage.setStringProperty("JMSXGroupID", name);
                    //textMessage.setStringProperty("name", name);
                    return textMessage;
				}});
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
