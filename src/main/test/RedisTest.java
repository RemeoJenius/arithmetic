import com.jenius.web.dao.UserDao;
import com.jenius.web.dao.cahce.RedisDao;
import com.jenius.web.mate.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by liyongjun on 17/4/1.
 */
@ContextConfiguration({"classpath:spring-*.xml"})

public class RedisTest {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() {
        // get put
        User user = redisDao.getUseById(1);
        if (user == null) {
            user = userDao.getUserById(1);
            if (user != null){
                String result = redisDao.putUserList(user);
                System.out.println(result);
                user = redisDao.getUseById(1);
                System.out.println(user);
            }
        }

    }


    @Test
    public void jedisPool() {
        JedisPool pool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("aa"));


    }
}
