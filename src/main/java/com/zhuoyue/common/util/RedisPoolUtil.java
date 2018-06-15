package com.zhuoyue.common.util;

import com.zhuoyue.common.web.config.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Redis 操作工具类
 *
 * @author 14258
 */
public class RedisPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);


    /**
     * 设置set key
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("expire key:{} error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置set key 的有效期，
     *
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.setex(key, exTime, value);
        } catch (Exception e) {
            logger.error("setex key:{} value:{}  error", key, value, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    /**
     * 设置key的有效期，单位是秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public static long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;

        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            logger.error("expire key:{} error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    /**
     * 根据key 删除
     *
     * @param key
     * @return
     */
    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("del key:{} error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error("get key:{} error", key, e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {

        Jedis jedis = RedisPool.getJedis();
        RedisPoolUtil.set("keyTest", "value");

        String value = RedisPoolUtil.get("keyTest");

        RedisPoolUtil.setEx("keyex", "valueex", 60 * 10);

        RedisPoolUtil.expire("keyTest", 60 * 10);

        RedisPoolUtil.del("keyTest");

        System.out.println("end");


    }


}
