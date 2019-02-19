package com.wemall.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LoginConfig;
import com.elasticsearch.entity.Employee;
import com.wemall.activemq.service.MessageService;
import com.wemall.jwt.service.impl.JwtToken;
import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;
import com.wemall.shopcategories.model.CategoryModel;
import com.wemall.shopcategories.service.CategoriesService;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

import comm.elasticsearch.dao.EmployeeRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private EmployeeRepository employeeRepository;	

	/*@Autowired  
    private HttpServletRequest request; 
	*/
	
    @Autowired//(required=false)
    private ElasticsearchTemplate esTemplate; 
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	
	@Autowired
	private MessageService messageService;
	
/*	@Autowired  
    private HttpServletResponse response;*/
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ShopService shopService;
	
/*	@Autowired
	private DemoService demoService;
*/	
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping("addes")
	@ResponseBody
	public String addEs() {
		Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeRepository.save(employee);
        System.err.println("add a obj");
        return "success";
	}
	
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
		redisTemplateUtil.set("1", a);
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
	@ResponseBody
	public String sendmq(String destination, String msg, String name) { 
		Shop selectByPrimaryKey = shopService.selectByPrimaryKey("1");
		//messageService.sendMessage(destination, JSON.toJSONString( selectByPrimaryKey));
		messageService.sendMessage(destination, msg, name);
		return "0k";
	}
	
	@ResponseBody
	@RequestMapping(value = "receive")
	public Object receivemq(String destination) {
		return messageService.receive(destination);
	}
	
	@ResponseBody
	@RequestMapping(value = "testdubbo")
	public List<CategoryModel> receivemq(Long id) {
		try {
			/*RpcContext.getContext().setAttachment("token","12345");*/
			ArrayList arrayList = new ArrayList();
			Object object = redisTemplateUtil.get("testhastime");
			if (object == null) {
				arrayList.add("1");
				arrayList.add("2");	
				redisTemplateUtil.set("testhastime", arrayList, 60);
				System.out.println(arrayList);
			} else {
				System.out.println(object);
			}
			return categoriesService.getCategoryModelList();
		} catch (Exception e) {
			System.out.println("捕获到异常");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "testinsert")
	public User testinsert(User user) {
		userService.insert(user);
		return user;
		//return categoriesService.getCategoryModelList();
		//return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "testedit")
	public User testEdit(User user) {
		userService.updateByPrimaryKey(user);
		return user;
		//return categoriesService.getCategoryModelList();
		//return null;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("testdoubletran")
	public List<CategoryModel> testdoubletran(User user) {
		//userService.insert(user);
		//return user;
		return categoriesService.getCategoryModelList();
	}
	
	@ResponseBody
	@RequestMapping("selectUserByJoin")
	public List<User> selectUserByJoin() {
		return userService.selectUserByJoin();
	}
	
	
	@ResponseBody
	@RequestMapping("selecttest")
	public List<User> selecttest(@RequestBody Map map) {
		return userService.selecttest(map);
	}
	
	
	@ResponseBody
	@RequestMapping("login2")
	public String login2(HttpServletRequest request, HttpServletResponse response)  {
		String redissessionid = UUID.randomUUID().toString();
		User user = new User();
		user.setId(new Long(2));
		user.setUserName("linan");
		redisTemplateUtil.set(redissessionid, user, LoginConfig.SESSIONTIMEOUT);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(LoginConfig.SESSIONTIMEOUT);
		Cookie cookie = new Cookie("redissessionid", redissessionid);
		response.addCookie(cookie);
		return "";
	}
	
	
}
