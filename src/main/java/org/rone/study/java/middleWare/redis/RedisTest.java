package org.rone.study.java.middleWare.redis;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 学习
 */
public class RedisTest {

	public static void main(String[] args) {
		testJedisPool();
	}

	/**
	 * 使用jedis连接池
	 */
	public static void testJedisPool() {
		//通用连接池设置
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		//最大连接数
		poolConfig.setMaxTotal(30);
		//最大空闲的连接数，当返还连接时会将超过该数值的连接释放掉
		poolConfig.setMaxIdle(10);
		//最少空闲的连接数
		poolConfig.setMinIdle(3);
		//当连接池资源耗尽时，等待时间，超出则抛异常，默认为-1即永不超时
		poolConfig.setMaxWaitMillis(-1);
		//默认false，borrow的时候检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取
		poolConfig.setTestOnBorrow(true);
		//默认false，return的时候检测是有有效，如果无效则从连接池中移除，并尝试获取继续获取
		poolConfig.setTestOnReturn(true);
		//****************
		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 10000, null);
		Jedis jedis = jedisPool.getResource();
		jedis.auth("root");
		System.out.println("服务正在运行: " + jedis.ping());
	}

	/**
	 * jedis使用
	 */
	public static void testJedis() {
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
