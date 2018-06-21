package com.wemall.jwt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wemall.jwt.service.impl.JwtToken;

public class JwtFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//ResultMsg resultMsg;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if ("/user/test11".equals(httpRequest.getServletPath())) {
			chain.doFilter(request, response);
			return;
		}
		String auth = httpRequest.getHeader("JwtToken");
		//String auth = httpRequest.getParameter("Authorization"); //getHeader("Authorization");
		try {
			JwtToken.verifyToken(auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");  
			httpResponse.setContentType("application/json; charset=utf-8"); 
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			ObjectMapper mapper = new ObjectMapper();
			
			//resultMsg = new ResultMsg(ResultStatusCode.INVALID_TOKEN.getErrcode(), ResultStatusCode.INVALID_TOKEN.getErrmsg(), null);
			httpResponse.getWriter().write(mapper.writeValueAsString("1"));
			return;
		}
		chain.doFilter(request, response);
		return;
		
		/*if ((auth != null) && (auth.length() > 7))
		{
			String HeadStr = auth.substring(0, 6).toLowerCase();
			if (HeadStr.compareTo("bearer") == 0)
			{
				
				auth = auth.substring(7, auth.length()); 
	            if (JwtHelper.parseJWT(auth, audienceEntity.getBase64Secret()) != null)
	            {
	            	chain.doFilter(request, response);
	            	return;
	            }
			}
		}
		*/
	/*	HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");  
		httpResponse.setContentType("application/json; charset=utf-8"); 
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		ObjectMapper mapper = new ObjectMapper();
		
		//resultMsg = new ResultMsg(ResultStatusCode.INVALID_TOKEN.getErrcode(), ResultStatusCode.INVALID_TOKEN.getErrmsg(), null);
		httpResponse.getWriter().write(mapper.writeValueAsString("1"));
		return;*/
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
