package com.wemall.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.activemq.service.MessageService;
import com.wemall.jwt.service.impl.JwtToken;
import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	

	/*@Autowired  
    private HttpServletRequest request; 
	*/
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	
	@Autowired
	private MessageService messageService;
	
/*	@Autowired  
    private HttpServletResponse response;*/
	@Autowired
	private IUserService userService;
	@ResponseBody
	@RequestMapping("login")
	public boolean login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			System.out.println("sessionId:" + session.getId());
			//解决跨域前端登录时没有将cookie写入的问题
			Cookie cookie = new Cookie("JSESSIONID", (String)session.getId());
			//response.addCookie(cookie);
			System.out.println("sessionHost:" + session.getHost());
			System.out.println("sessionTimeout:" + session.getTimeout());
			session.setAttribute("info", "session的数据");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("user")
	public User test(Long id) {
		return userService.selectByPrimaryKey(id);

	}
	@ResponseBody
	@RequestMapping("nolog")
	public String nolog() {
		return "没有登录";

	}
	@ResponseBody
	@RequestMapping("unauthorized")
	public String unauthorized() {
		return "没有权限";

	}
	
	@ResponseBody
	@RequestMapping("test")
	public String testredis() {
		List<String> a = new ArrayList<String>();
		a.add("1");
		redisTemplateUtil.get("1");
		return (String) redisTemplateUtil.get("1");

	}
	
	@ResponseBody
	@RequestMapping("test11")
	public String login1(String username, String password) {
		try {
			return JwtToken.createToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("send")
	public void sendmq(String destination, String msg) {
		messageService.sendMessage(destination, msg);
	}
	
	@ResponseBody
	@RequestMapping(value = "receive")
	public Object receivemq(String destination) {
		return messageService.receive(destination);
	}
}
