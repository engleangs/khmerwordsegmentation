package com.job4kh.exp;

import redis.clients.jedis.Jedis;

public class RedisConnection {
    private Jedis jedis;
    private static class InnerRedisConnection{
        private static RedisConnection redisConnection = new RedisConnection();
    }

    public static RedisConnection getInstance()
    {
        return InnerRedisConnection.redisConnection;
    }
    private RedisConnection()
    {
        this.jedis = new Jedis(); //todo get from config files
    }

    public Jedis getJedis()
    {
        return jedis;
    }
}
