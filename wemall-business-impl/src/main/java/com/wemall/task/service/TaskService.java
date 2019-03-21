package com.wemall.task.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	
	public TaskService() {
		System.out.println("--------------------------------");
	}
	 @Scheduled(cron = "*/5 * * * * ?")//每隔5秒执行一次
    public void cacheUpdate(){
    	System.out.println("--------------------------------");
    }

}
