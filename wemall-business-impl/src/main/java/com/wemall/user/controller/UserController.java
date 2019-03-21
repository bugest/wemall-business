package com.wemall.user.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.math.util.MathUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LoginConfig;
import com.alibaba.fastjson.JSON;
import com.elasticsearch.entity.Employee;
import com.wemall.activemq.service.MessageService;
import com.wemall.jwt.service.impl.JwtToken;
import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;
import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.model.CategoryModel;
import com.wemall.shopcategories.service.CategoriesService;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

import comm.elasticsearch.dao.EmployeeRepository;
import java.util.Random;
@Controller
@RequestMapping("/user")
public class UserController {
	/*@Autowired
    private EmployeeRepository employeeRepository;	*/

	@Autowired  
    private HttpServletRequest request; 
	
	@Qualifier("redisTemplate")
	@Autowired
    private RedisTemplate redisTemplate; 
	
    /*@Autowired//(required=false)
    private ElasticsearchTemplate esTemplate; */
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
	
/*	@RequestMapping("addes")
	@ResponseBody
	public String addEs() {
		Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeRepository.save(employee);
        Employee employee1 = new Employee();
        employee1.setId("2");
        employee1.setFirstName("nan");
        employee1.setLastName("li");
        employee1.setAge(29);
        employee1.setAbout("i am in tianjin");
        employeeRepository.save(employee1);
        System.err.println("add a obj");
        Employee queryEmployeeById = employeeRepository.queryEmployeeById("1");
        Iterable<Employee> findAll = employeeRepository.findAll();
        Optional<Employee> findById = employeeRepository.findById("1");
        Optional<Employee> findById2 = employeeRepository.findById("2");
        Employee queryEmployeeByLastName = employeeRepository.queryEmployeeByLastName("z");
        Employee queryEmployeeByLastName2 = employeeRepository.queryEmployeeByLastName("zh");
        Employee queryEmployeeByLastName3 = employeeRepository.queryEmployeeByLastName("a");
        Employee findByLastName = employeeRepository.findByLastName("z");
        Employee findByLastName2 = employeeRepository.findByLastName("zh");
        Employee findByLastName3 = employeeRepository.findByLastName("a");
        List<Employee> findByLastNameOrFirstName = employeeRepository.findByLastNameOrFirstName("zh", "nan");
        List<Employee> findByLastNameOrFirstName2 = employeeRepository.findByLastNameOrFirstName("zh1", "xuxu");
        List<Employee> findByLastNameOrFirstName3 = employeeRepository.findByLastNameOrFirstName("zh1", "xuxu1");
        List<Employee> findByLastNameOrFirstNameLike = employeeRepository.findByLastNameOrFirstNameLike("zh", "nan");
        List<Employee> findByLastNameAndFirstName = employeeRepository.findByLastNameAndFirstName("zh", "xuxu");
        List<Employee> findByLastNameAndFirstName2 = employeeRepository.findByLastNameAndFirstName("zh1", "xuxu");
        Pageable pageable = new PageRequest(0, 50);
        Page<Employee> findByLastNameOrFirstNameNot = employeeRepository.findByLastNameOrFirstNameNot("12", "23", pageable);
        return "success";
	}*/
	
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
	public List<String> testredis(HttpServletResponse response) {
		response.addHeader("Set-Cookie", "uid1=112; Path=/; HttpOnly");

		//设置多个cookie
		response.addHeader("Set-Cookie", "uid2=112; Path=/; HttpOnly");
		response.addHeader("Set-Cookie", "timeout=30; Path=/test; HttpOnly");

		//设置https的cookie
		response.addHeader("Set-Cookie", "uid=112; Path=/123; Secure; HttpOnly");
		List<String> a = new ArrayList<String>();
		a.add("1");
		redisTemplateUtil.set("1", a);
 		List<String> s = (List<String>) redisTemplateUtil.get("1");
 		HashMap<String, String> hashMap = new HashMap<String, String>();
 		ArrayList arrayList = new ArrayList();
 		redisTemplate.opsForZSet().add("huhu", hashMap, 1);
 		redisTemplate.opsForZSet().add("huhu", hashMap, 2);
 		redisTemplate.opsForZSet().add("huhu", arrayList, 1);
 		List a1= new ArrayList(); 
 		a1.add("sadsdf");
 		redisTemplate.opsForZSet().add("huhu", a1, 2);
 		HashMap hashMap2 = new HashMap();
 		hashMap2.put("nihao", 1);
 		HashMap hashMap3 = new HashMap();
 		hashMap3.put("nihao", "1");
 		HashMap hashMap4 = new HashMap();
 		hashMap4.put("nihao", "1");
 		HashMap hashMap5 = new HashMap();
 		User u = new User();
 		u.setId(new Long(3322));
 		hashMap5.put("nihao", u);
 		String jsonString2 = JSON.toJSONString(hashMap2);
 		String jsonString3 = JSON.toJSONString(hashMap3);
 		String jsonString4 = JSON.toJSONString(hashMap4);
 		String jsonString5 = JSON.toJSONString(hashMap5);
 		redisTemplate.opsForZSet().add("ww", jsonString2, 1);
 		redisTemplate.opsForZSet().add("ww", jsonString3, 1);
 		redisTemplate.opsForZSet().add("ww", jsonString4, 2);
 		redisTemplate.opsForZSet().add("ww", jsonString5, 2);
 		return s;
 		

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
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
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
	
	@RequestMapping("updateSexCount")
	@ResponseBody
	public int updateSexCount(User user) {
		//int a = userService.updateSexCount(user);
		redisTemplate.opsForValue().increment("count", -1);
		return 1;
	}
	
	@Test
	public void ss() {
		spilte(2, new BigDecimal(1.13));
	}
	
	private void spilte(int count, BigDecimal amount) {
		BigDecimal leftMoney = amount;
		ArrayList<BigDecimal> arrayList = new ArrayList<BigDecimal>();
		//初始化总个数等于0.01
		for (int i = 0; i < count; i++) {
			arrayList.add(new BigDecimal(0.01));
			leftMoney = leftMoney.subtract(new BigDecimal(0.01));
		}
		while (leftMoney.compareTo(BigDecimal.ZERO) > 0) {
			Random random = new Random();
			int nextInt = random.nextInt();
			int i = Math.abs(nextInt%count);
			leftMoney = leftMoney.subtract(new BigDecimal(0.01));
			arrayList.set(i, arrayList.get(i).add(new BigDecimal(0.01)));
		}
		for (int i=0; i < count; i++) {
			arrayList.set(i, arrayList.get(i).setScale(2, BigDecimal.ROUND_DOWN));
		}
		System.out.println(arrayList);
	}
	
	@ResponseBody
	@RequestMapping(value = "testdubbo1")
	public Categories receivemq1(Long id) {
		try {
			categoriesService.selectByPrimaryKey(200);
			return categoriesService.selectByPrimaryKey(100);
		} catch (Exception e) {
			System.out.println("捕获到异常");
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping("updateSexCount1")
	@ResponseBody
	public int updateSexCount1(User user) {
		int a = userService.updateSexCount(user);
		//redisTemplate.opsForValue().increment("count", -1);
		return a;
	}
}
