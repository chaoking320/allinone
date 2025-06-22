package chaoking.java.allinone.learn.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Nullable;

/**
 * 监听rediskey失效
 */
public class MyRedisTest extends KeyExpirationEventMessageListener {

    public MyRedisTest(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message,@Nullable byte[] pattern){
        message.getBody();
    }
}
