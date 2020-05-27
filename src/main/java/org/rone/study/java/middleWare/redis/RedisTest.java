package org.rone.study.java.middleWare.redis;

import java.io.IOException;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * Redis
 */
public class RedisTest {

	public static void main(String[] args) throws IOException {
		simpleDemo();
//		pipelineDemo();
	}

	/**
	 * redis的管道技术，一次处理多个操作
	 */
	public static void pipelineDemo() throws IOException {
		//通过连接池获取jedis
		try (Jedis jedis = gainJedisFromPool(); Pipeline pipeline = jedis.pipelined()) {
			pipeline.set("rone", "3");
			pipeline.set("sonw", "hello");
			pipeline.incr("rone");
			pipeline.get("rone");
			pipeline.get("snow");List result = pipeline.syncAndReturnAll();
			System.out.println(result);
		}
	}

	/**
	 * 从连接池中获取jedis，使用完后需要关闭jedis，否则不会返回连接池
	 */
	public static Jedis gainJedisFromPool() {
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
		JedisPool jedisPool = new JedisPool(poolConfig, "114.55.26.204", 6379, 10000, "redis");
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/**
	 * 最简单的demo
	 */
	public static void simpleDemo() {
		//连接地址默认为：127.0.0.1:6379
		Jedis jedis = new Jedis();
		jedis.set("firstKey", "hello world");
		String result = jedis.get("firstKey");
		jedis.close();
		System.out.println(result);
	}

}
