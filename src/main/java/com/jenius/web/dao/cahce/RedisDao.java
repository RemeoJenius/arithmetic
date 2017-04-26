package com.jenius.web.dao.cahce;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.jenius.web.mate.User;
import com.sun.xml.internal.ws.message.source.ProtocolSourceMessage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by liyongjun on 17/4/2.
 */
public class RedisDao {
    private final JedisPool jedisPool;

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);

    public RedisDao(String ip, int port) {
        this.jedisPool = new JedisPool(ip, port);
    }

    public User getUseById(int id) {
        // redis缓存逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:" + id;
                // 并没有实现内部序列化操作
                // get->byte[]->反序列化->object(userList);
                // 采用自定义序列化的方式
                // protostuff:pojo
                byte[] bytes = jedis.get(key.getBytes());
                // 缓存中获取到了
                if (bytes != null) {
                    // 空对象
                    User user = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, user, schema);
                    // user 反序列化
                    return user;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String putUserList(User user) {
        // set object(user) ->序列化->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "user:" + user.getId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存
                int timeout = 60 * 60; // 一小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
