package com.wemall.activemq.service;

public interface MessageService {
	void sendMessage(String destination ,Object msg);
	Object receive(String destination);
}
