package com.wemall.redissession.tool;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wemall.redis.service.impl.RedisTemplateUtil;

@Component
public class DispacherSessionImmpl implements HttpSession{
	

	private RedisTemplateUtil redisTemplateUtil;
	public RedisTemplateUtil getRedisTemplateUtil() {
		ServletContext context = getServletContext();
		ApplicationContext ac = WebApplicationContextUtils .getWebApplicationContext(context); 
		redisTemplateUtil = (RedisTemplateUtil)ac.getBean("redisTemplateUtil"); 
		return redisTemplateUtil;
	}
	public void setRedisTemplateUtil(RedisTemplateUtil redisTemplateUtil) {
		this.redisTemplateUtil = redisTemplateUtil;
	}
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String sid;
	public DispacherSessionImmpl(){
	}
	public DispacherSessionImmpl(HttpSession session){
		this.session=session;
	}
	public DispacherSessionImmpl(HttpServletRequest request,HttpServletResponse response,String id){
		this.sid = id;
		this.request = request;
		this.response = response;
	}
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		
	}
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getAttribute(String name) {
		Object object = getRedisTemplateUtil().get(sid);
		HttpSession session = (HttpSession)object;
		if (session != null) {
			//redisTemplateUtil.setExpire(key, timeout, unit);
			return session.getAttribute(name);
			//redisTemplateUtil.set(sid, session, 100);	
		} else 
			return null;
	}
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setAttribute(String name, Object value) {
		Object object = getRedisTemplateUtil().get(sid);
		HttpSession session = (HttpSession)object;
		if (session != null) {
			session.setAttribute(name, value);
			getRedisTemplateUtil().set(sid, session, 100);	
		}

	}
	public void putValue(String name, Object value) {
		// TODO Auto-generated method stub
		
	}
	public void removeAttribute(String name) {
		Object object = getRedisTemplateUtil().get(sid);
		HttpSession session = (HttpSession)object;
		if (session != null) {
			session.removeAttribute(name);
			getRedisTemplateUtil().set(sid, session, 100);	
		}
	}
	public void removeValue(String name) {
		// TODO Auto-generated method stub
		
	}
	public void invalidate() {
		Object object = getRedisTemplateUtil().get(sid);
		HttpSession session = (HttpSession)object;
		if (session != null) {
			getRedisTemplateUtil().delete(sid);
		}
	}
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}
}
