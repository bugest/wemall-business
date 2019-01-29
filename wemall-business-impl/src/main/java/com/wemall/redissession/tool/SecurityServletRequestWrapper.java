package com.wemall.redissession.tool;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wemall.redis.service.impl.RedisTemplateUtil;

@Component
public class SecurityServletRequestWrapper extends HttpServletRequestWrapper{
	
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	public RedisTemplateUtil getRedisTemplateUtil() {
		ServletContext context = session.getServletContext();
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
	public SecurityServletRequestWrapper(HttpServletRequest request,HttpServletResponse response) {
		super(request);
		this.request=request;
		this.response=response;
	}
	//重写获取session的方法
	public HttpSession getSession() {
		return this.getSession(true);
	} 
	public HttpSession getSession(boolean create) {
		if(create){
			String id = CookieUtil.getCookieValue(request, "pcxSessionId");
			if(StringUtils.isEmpty(id)){
				id = UUID.randomUUID().toString();
				CookieUtil.setCookie(request, response, "pcxSessionId", id, 60*60);
				//创建session
				HttpSession session = new DispacherSessionImmpl(this.request,this.response,id);
				this.session = session;
				getRedisTemplateUtil().set(id, session, 100);
				return session;
			} else {
				Object object = getRedisTemplateUtil().get(id);
				if (object == null) {
					HttpSession session = new DispacherSessionImmpl(this.request,this.response,id);
					getRedisTemplateUtil().set(id, session, 100);
					return session;
				} else {
					getRedisTemplateUtil().setExpire(id, new Long(100), TimeUnit.SECONDS);
					return (HttpSession) getRedisTemplateUtil().get(id);
				}
			}
		}else{
			return null;
		}
    }
}
