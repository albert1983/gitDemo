package com.zhuoyue.common.web.config;

import com.zhuoyue.common.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 连接池
 *
 * @author 14258
 */
public class RedisPool {

    private static JedisPool pool;//jedis 连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", String.valueOf(20)));  // 最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", String.valueOf(20)));  //在jedispool中最大的idel状态（空闲的）的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", String.valueOf(10)));  //在jedispool中最小的idel状态（空闲的）的jedis实例的个数
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));  //在borrow一个redis实例的时候，是否要验证操作，如果赋值为true，则可用
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));  //在borrow一个redis实例的时候，是否要验证操作，如果赋值为true，则可用

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.valueOf(PropertiesUtil.getProperty("redis.port"));


    /**
     * 初始化连接池
     */
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        //连接耗尽时是否阻塞 ，false会抛出异常，true阻塞直到超时 ， 默认为true
        config.setBlockWhenExhausted(true);
        //
        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
    }

    static {
        initPool();
    }


    /**
     * 获得连接池
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnBrokenResource(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {


        Jedis jedis = pool.getResource();
        jedis.set("xuyy", "boy");
        returnResource(jedis);

        //临时调用,销毁连接池中所有连接
        pool.destroy();

        System.out.println("program is end ");


    }


}
