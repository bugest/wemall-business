package com.wemall.activemq.service;

public interface MessageService {
	void sendMessage(String destination ,Object msg, int priority);
	Object receive(String destination);
}
