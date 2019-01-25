package com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.user.entity.User;

@Component("clickstreamFilter")
public class TestFilter1 implements Filter {

	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext(); 
		ApplicationContext ac = WebApplicationContextUtils .getWebApplicationContext(context); 
		redisTemplateUtil = (RedisTemplateUtil)ac.getBean("redisTemplateUtil"); 

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String url = httpRequest.getRequestURL().toString();
		if(url.endsWith("login2")) {
			chain.doFilter(httpRequest, response);
		} else {

			if (httpRequest.getSession().getAttribute("user") != null) {
				chain.doFilter(httpRequest, response);
			} else {
				Cookie[] cookies = httpRequest.getCookies();
				if (cookies != null && cookies.length > 0) {
					boolean getsession = false;
					for (Cookie cookie : cookies) {
						if ("redissessionid".equals(cookie.getName())) {
							String value = cookie.getValue();
							User user = (User)redisTemplateUtil.get(value);
							if (user != null) {
								httpRequest.getSession().setAttribute("user", user);
								getsession = true; 
								break;
							}	
						}
					}
					if (!getsession) {
						httpResponse.sendRedirect("/login.jsp");
					} else {
						chain.doFilter(httpRequest, httpResponse);
						System.out.print("1222");
					}
				}
			}
		}

/*		HttpServletRequest httpRequest = (HttpServletRequest)request;
		Cookie[] cookies = httpRequest.getCookies();
		//httpRequest
		
		Cookie ck = new Cookie("11233", "3213123");
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.addCookie(ck);
		// TODO Auto-generated method stub
		System.out.println("111111111");
		
		
		chain.doFilter(request, response);
		HttpSession session = httpRequest.getSession();
		redisTemplateUtil.set("sessionid-" + session.getId(), session);*/
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
