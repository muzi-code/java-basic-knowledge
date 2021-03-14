package com.github.dev.muzi.base.concurrent.knowledge.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lifuyi8
 * @since 2021/2/23 3:01 下午
 */
@Component
public class RedisService {

    @Autowired
    RedisScriptDictionary dictionary;

    Jedis jedis = new Jedis("localhost");

    public String loadScript(String script) {
        return jedis.scriptLoad(script);
    }

    public String eval() {
        Object obj = jedis.evalsha(dictionary.getScriptSha("ILB"), Arrays.asList("unique-key", String.valueOf(getSecondTimestampTwo())), Arrays.asList("10", "20", String.valueOf(getSecondTimestampTwo()), "1"));
        System.out.println(JSON.toJSONString(obj));
        return null;
    }
    public static int getSecondTimestampTwo(){
        String timestamp = String.valueOf(new Date().getTime()/1000);
        return Integer.valueOf(timestamp);
    }
}
