package com.wemall.shrio.filterchaindef;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

// AuthorizationFilter抽象类事项了javax.servlet.Filter接口，它是个过滤器。    
public class AllAuthorizationFilter extends AuthorizationFilter {

	private final static Logger log = Logger.getLogger(AllAuthorizationFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) throws Exception {
		Subject subject = getSubject(req, resp);
		String[] allArray = (String[]) mappedValue;
		if (allArray == null || allArray.length == 0) { // 没有角色限制，有权限访问
			log.info("all过滤条件为空，直接不进行权限控制");
			return true;
		}
		if (allArray.length != 1) {
			log.info("all过滤条件要求数组只有一个表达式，目前不唯一，所以不过滤");
			return true;
		}
		Map<String, Boolean> hasRoleOrPerm = new HashMap<String, Boolean>();
		String expnew = "";
		for (String exp : allArray) {
			expnew = exp;
			String[] arr = exp.split("\\s+");
			for (String ss : arr) {
				// 获取里边的role-和perm-相关的
				if (ss.indexOf("role-") != -1) {
					hasRoleOrPerm.put(ss, subject.hasRole(ss.replaceAll("role-", "")));
				}
				// 获取里边的role-和perm-相关的
				if (ss.indexOf("perm-") != -1) {
					hasRoleOrPerm.put(ss, subject.isPermitted(ss.replaceAll("perm-", "")));
				}
			}
			Set<Entry<String, Boolean>> entryset = hasRoleOrPerm.entrySet();
			for (Entry<String, Boolean> entry : entryset) {
				expnew = expnew.replace(entry.getKey(), entry.getValue().toString());
			}
			expnew = expnew.replace("and", "&&");
			expnew = expnew.replace("or", "||");
		}
		ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(expnew);
        return (Boolean) result;
        //System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
		//return false;
	}
}