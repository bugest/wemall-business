local leftcount = redis.call('hget', KEYS[1], KEYS[2])
local leftcountnum = tonumber(leftcount)
local done
if(leftcountnum < 1)
then
done = false
else	
local b = redis.call('hincrby', KEYS[1], KEYS[2], -1)
done = true
end
return 
done