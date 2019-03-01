local a = redis.call('hget', KEYS[1], KEYS[2])
local c = tonumber(a) - 1
redis.call('hset', KEYS[1], KEYS[2], c)
local b = redis.call('hget', KEYS[1], KEYS[2])
return b