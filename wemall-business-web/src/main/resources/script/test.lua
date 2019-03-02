local leftcount = redis.call('hget', KEYS[1], KEYS[2])
local leftcountnum = tonumber(leftcount)
local done
if(leftcountnum < 1)
then
done = -1
else	
local b = redis.call('hincrby', KEYS[1], KEYS[2], -1)
redis.call('hset',KEYS[1].."-"..b,"wallet", ARGV[2])
redis.call('hset',KEYS[1].."-"..b,"opendate", ARGV[1])
done = b
end
return 
done