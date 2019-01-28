package com.wemall.shiro.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wemall.redis.service.impl.RedisTemplateUtil;

@Component
public class MySessionDao extends AbstractSessionDAO {

	private String myKey= "shareSessionMap";
	
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    
    
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        redisTemplateUtil.boundHashOps(myKey).delete(session.getId().toString());
    }

    public Collection<Session> getActiveSessions() {
    	List<Session> list = new ArrayList<Session>();
    	List<Object> values = redisTemplateUtil.boundHashOps(myKey).values();
    	for (Object object : values) {
            list.add((Session) object);
        }
        return list;
    }

    public void update(Session session) throws UnknownSessionException {
        if(session == null || session.getId() == null){
            System.out.println("Session is null");
            return;
        }
        Serializable sessionId = session.getId();
        redisTemplateUtil.boundHashOps(myKey).put(sessionId, session);
    }

    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        //添加进redis
        redisTemplateUtil.boundHashOps(myKey).put(sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) redisTemplateUtil.boundHashOps(myKey).get(sessionId.toString());
    }


    
}