package org.rone.study.java.middleWare.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Redis 学习
 */
public class RedisTest {

	public static void main(String[] args) {
		//连接 Redis 服务
		Jedis jedis = new Jedis("127.0.0.1");
		//验证密码
		jedis.auth("root");
		System.out.println("服务正在运行: " + jedis.ping());
		//获取所有的 key
		Set<String> keys = jedis.keys("*");
		System.out.println(keys);
		for (String key : keys) {
			System.out.print("key: " + key + ", valueType: " + jedis.type(key) + ", value: ");
			//判断 key 值对应的 value 的类型，来判断获取 value 的方式
			if ("string".equals(jedis.type(key))) {
				System.out.print(jedis.get(key));
			} else if ("list".equals(jedis.type(key))) {
				System.out.print(jedis.lrange(key, 0, jedis.llen(key)));
			} else if ("hash".equals(jedis.type(key))) {
				System.out.print(jedis.hgetAll(key));
			} else if ("set".equals(jedis.type(key))) {
				System.out.print(jedis.smembers(key));
			} else if ("zset".equals(jedis.type(key))) {
				System.out.print(jedis.zrange(key, 0, jedis.zcard(key)));
			}
			System.out.println();
		}
	}

}
