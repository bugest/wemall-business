local a = redis.call('hgetall', KEYS[1])
return a