package com.wemall.redis.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;  
import java.util.Map;  
import java.util.Set;
import java.util.concurrent.TimeUnit;  
  
/** 
 * Created by tl on 17/2/16. 
 */  
@Service
public class RedisTemplateUtil {  
	@Qualifier("redisTemplate")
	@Autowired
    private RedisTemplate redisTemplate;  
  
    public RedisTemplateUtil(RedisTemplate redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
  
    public void set(String key, Object value, long timeout) {
        ValueOperations valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value, timeout, TimeUnit.SECONDS);;  	
    }
  
    public void set(String key, Object value) {  
        ValueOperations valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value);  
    }  
  
    public Object get(String key) {  
        return redisTemplate.opsForValue().get(key);  
    }  
  
    public void setList(String key, List<?> value) {  
        ListOperations listOperations = redisTemplate.opsForList();  
        listOperations.leftPush(key, value);  
    }  
  
    public Object getList(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  
  
    public void setSet(String key, Set<?> value) {  
        SetOperations setOperations = redisTemplate.opsForSet();  
        setOperations.add(key, value);  
    }  
  
    public Object getSet(String key) {  
        return redisTemplate.opsForSet().members(key);  
    }  
  
  
    public void setHash(String key, Map<String, ?> value) {  
        HashOperations hashOperations = redisTemplate.opsForHash();  
        hashOperations.putAll(key, value);  
    }  
  
    public void deleteHash(String key) {  
        redisTemplate.delete(key);
    }
    
    public Object getHash(String key) {  
        return redisTemplate.opsForHash().entries(key);  
    }  
  
  
    public void delete(String key) {  
        redisTemplate.delete(key);  
    }
    
    public Boolean setIfAbsent(String key, Object value) {  
        ValueOperations opsForValue = redisTemplate.opsForValue();  
        return opsForValue.setIfAbsent(key, value);  
    }  
    
    public void setWithTimeOut(String key, Object value, Long timeout, TimeUnit unit) {  
        redisTemplate.opsForValue().set(key, value, timeout, unit);  
    }  
    
    public void setExpire(String key, Long timeout, TimeUnit unit) {  
        redisTemplate.expire(key, timeout, unit); 
    } 
    
    public <HK, HV> BoundHashOperations<String, HK, HV> boundHashOps(String key) {
		return redisTemplate.boundHashOps(key);
	}
}