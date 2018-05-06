package com.zrblog.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName: JedisTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zrblog
 * @date 2017年12月27日
 * 
 */

public class JedisTest {
	@Test
	public void jedisTest() {
		Jedis jedis = new Jedis("192.168.37.161", 6379);

		String pong = jedis.ping();

		String key = "jedis";

		String result = jedis.set(key, "Hello Jedis");

		System.out.println(result);

		String getResult = jedis.get(key);

		System.out.println(getResult);

		jedis.close();
	}

	@Test
	public void jedisPoolTest() {
		JedisPool jedisPool = new JedisPool("192.168.37.161", 6379);

		Jedis jedis = jedisPool.getResource();

		String pong = jedis.ping();

		String key = "jedis";

		String result = jedis.set(key, "Hello Jedis");

		System.out.println(result);

		String getResult = jedis.get(key);

		System.out.println(getResult);

		// 每次使用完jedis连接，必须Close,指的是还会连接池
		jedis.close();

		jedis.close();

		jedisPool.close();
	}

	@Test
	public void jedisClusterTest() {

		Set<HostAndPort> nodes = new HashSet<HostAndPort>();

		nodes.add(new HostAndPort("192.168.37.161", 7001));
		nodes.add(new HostAndPort("192.168.37.161", 7002));
		nodes.add(new HostAndPort("192.168.37.161", 7003));
		nodes.add(new HostAndPort("192.168.37.161", 7004));
		nodes.add(new HostAndPort("192.168.37.161", 7005));
		nodes.add(new HostAndPort("192.168.37.161", 7006));

		// 创建对象
		JedisCluster jedisCluster = new JedisCluster(nodes);

		String key = "Test-jedisCluster";

		String result = jedisCluster.set(key, "Hello Jedis");

		System.out.println(result);

		String getResult = jedisCluster.get(key);

		System.out.println(getResult);
		// 每次使用完jedis连接，必须Close,指的是还会连接池

		jedisCluster.close();
	}

}
