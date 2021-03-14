package com.github.dev.muzi.base.concurrent.knowledge.redis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * @author lifuyi8
 * @since 2021/2/23 2:53 下午
 */
public class RedisTest {


    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.github.dev.muzi.base.concurrent.knowledge.redis");

        RedisService redisService = context.getBean(RedisService.class);

        new Thread(()->{
            while (true){
                for (int i = 0; i < 10 ; i++) {
                    System.out.println(redisService.eval());
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
