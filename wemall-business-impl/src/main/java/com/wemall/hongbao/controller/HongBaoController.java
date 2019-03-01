package com.wemall.hongbao.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("hongbao")
public class HongBaoController {
	
	@Autowired
	private RedisTemplate redisTemplate;
	@ResponseBody
	@RequestMapping("sendHongbao")
	public String sendHongBao(Integer count, BigDecimal amount) {
		String uuid = "hongbao" + "-" + UUID.randomUUID().toString().replaceAll("-","");  
		HashOperations opsForHash = redisTemplate.opsForHash();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
/*		hashMap.put("id", uuid);
		hashMap.put("name", "大吉大利恭喜发财");
		hashMap.put("amount", amount);
		hashMap.put("count", count);
		hashMap.put("leftcount", count);
		hashMap.put("leftamount", amount);
		Date dateBegin = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateBegin);
		calendar.add(Calendar.DATE, 1);
		Date dateEnd = calendar.getTime();
		hashMap.put("createtime", dateBegin);
		hashMap.put("endtime", dateEnd);
		hashMap.put("team", "11111");
		opsForHash.put(uuid, "id", uuid);*/
		opsForHash.put(uuid, "name", "大吉大利恭喜发财");
		opsForHash.put(uuid, "amount", amount);
		opsForHash.put(uuid, "count", count);
		
		//opsForHash.putAll(uuid, hashMap);
		//redisTemplate.expireAt(uuid, dateEnd);
		return uuid;
	}
	@ResponseBody
	@RequestMapping("doscript")
	public String doscript() {
		DefaultRedisScript<String> getRedisScript;
		getRedisScript = new DefaultRedisScript<String>();
        getRedisScript.setResultType(String.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/test.lua")));
        List<String> keyList = new ArrayList<String>();
        Map<String,Object> argvMap = new HashMap<String,Object>();
        keyList.add("1212");
        keyList.add("12313");
        argvMap.put("expire",10000);
        argvMap.put("times",10);
        Object o = redisTemplate.execute(getRedisScript, keyList, argvMap);
        //System.out.println(result);
        return o.toString();
	}
	
	@ResponseBody
	@RequestMapping("domuti")
	//@Transactional
	public String domuti() {
		//redis 事务根据测试 122 类型指定为hash redisTemplate.opsForValue().get("122"); 会有类型错误，但是事务还是执行了，执行完了，能查到结果了，才报了个异常
		//redisTemplate.opsForValue().set(null, null); 这一句在执行前就会报错误，类似在客户端写了个 get ，所以整个事务都没执行
		//所以证明了事务的有效
		redisTemplate.multi();
		redisTemplate.opsForValue().set("11", "33");
		//这句类型不对，因为122 为hash，但是事务还是执行了，都执行成功后这句才抛出的异常
		redisTemplate.opsForValue().get("122");
		//下面的这句会造成事务验证不过，直接不执行
		//redisTemplate.opsForValue().set(null, null); 
		redisTemplate.opsForValue().set("12", "4");
		redisTemplate.exec();
		return "0k";
	}
}
