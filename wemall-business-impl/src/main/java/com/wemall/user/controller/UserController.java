package com.wemall.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	

	@Autowired  
    private HttpServletRequest request; 
	
	@Autowired  
    private HttpServletResponse response;
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
			response.addCookie(cookie);
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
}
