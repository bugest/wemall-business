package com.wemall.redissession.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wemall.redissession.tool.SecurityServletRequestWrapper;

public class SessionFilter implements Filter {
	public FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res=(HttpServletResponse)response;
         req = new SecurityServletRequestWrapper(req, res);
         HttpSession session = req.getSession();
         chain.doFilter(req, res);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		this.config=null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config=filterConfig;
	}

}
