package com.wemall.activemq.service;

public interface MessageService {
	void sendMessage(String destination ,Object msg, String name);
	Object receive(String destination);
}
