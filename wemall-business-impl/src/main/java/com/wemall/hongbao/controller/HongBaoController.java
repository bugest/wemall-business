package com.wemall.hongbao.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wemall.activemq.service.MessageService;

@Controller
@RequestMapping("hongbao")
public class HongBaoController {
	
	@Qualifier("redisTemplateNoSeri")
	@Autowired
	private RedisTemplate redisTemplate;
	
	//同时只有两个线程并行
	private Semaphore semaphore = new Semaphore(2);
	
	@Autowired
	private MessageService messageService;
	@ResponseBody
	@RequestMapping("sendHongbao")
	public Map sendHongBao(int count, BigDecimal amount) {
		String uuid = "hongbao" + "-" + UUID.randomUUID().toString().replaceAll("-","");  
		//生成红包的信息
		HashOperations opsForHash = redisTemplate.opsForHash();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("id", uuid);
		hashMap.put("name", "大吉大利恭喜发财");
		hashMap.put("amount", amount.toString());
		hashMap.put("count", count + "");
		hashMap.put("leftcount",  count + "");
		hashMap.put("leftamount", amount.toString());
		Date dateBegin = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateBegin);
		calendar.add(Calendar.DATE, 1);
		Date dateEnd = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hashMap.put("createtime", simpleDateFormat.format(dateBegin));
		hashMap.put("endtime", simpleDateFormat.format(dateEnd));
		hashMap.put("team", "11111");
		opsForHash.putAll(uuid, hashMap);
		redisTemplate.expireAt(uuid, dateEnd);
		List<BigDecimal> spilte = spilte(count, amount);
		//生成拆分红包的各个明细
		List detailList = new ArrayList();
		for (int i = 0; i < spilte.size(); i++) {
			HashMap detail = new HashMap();
			String detailUUID = uuid + "-" + i;
			detail.put("id", detailUUID);
			detail.put("amount", spilte.get(i).toString());
			detail.put("hongbaoid", uuid);
			detailList.add(detail);
			opsForHash.putAll(detailUUID, detail);
			redisTemplate.expireAt(detailUUID, dateEnd);
		}
		hashMap.put("details", detailList);	
		return hashMap;
	}
	
	private List<BigDecimal> spilte(int count, BigDecimal amount) {
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
		return arrayList;
	}
	
	@ResponseBody
	@RequestMapping("doscript")
	public String doscript(String uuid) {
		//try {
			/*semaphore.acquire();*/ //通过semaphore 限制并发数量，可以防止流量都进入后台
			//redisTemplate.opsForValue().set("lua", 135);
		DefaultRedisScript<Long> getRedisScript;
		getRedisScript = new DefaultRedisScript<Long>();
		getRedisScript.setResultType(Long.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/test.lua")));
        List<String> keyList = new ArrayList<String>();
        Map<String,Object> argvMap = new HashMap<String,Object>();
        keyList.add(uuid);
        keyList.add("leftcount");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatdate = simpleDateFormat.format(date);
        Object o = redisTemplate.execute(getRedisScript, keyList, formatdate, "1");
        //返回剩余数量，如果  <0 就证明领完了
        Long left = (Long) o;
        if(left >= 0) {
        	Object amount = redisTemplate.opsForHash().get(uuid + "-" + o.toString(), "amount");
            Object wallet = redisTemplate.opsForHash().get(uuid + "-" + o.toString(), "wallet");
            //发送消息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", amount);
            jsonObject.put("wallet", wallet);
            jsonObject.put("scource_id", uuid + "-" + o.toString());
            jsonObject.put("scource_type", "hongbao");
            String jsonString = jsonObject.toJSONString();
            //1 更细wallet余额 2写明细
            messageService.sendMessage("hongbaotowritewallet", jsonString, "");
            //写结果
            redisTemplate.opsForHash().put(uuid + "-" + o.toString(), "isSend", "1");
        }
        return o.toString();
	/*	}
		catch (InterruptedException e) {
			
		} finally {
			semaphore.release();
		}
		return null;*/
		
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
