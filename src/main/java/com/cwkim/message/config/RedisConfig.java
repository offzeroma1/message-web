package com.cwkim.message.config;

import com.cwkim.message.infrastructure.redis.RedisChatSubscriber;
import com.cwkim.message.infrastructure.redis.RedisNotificationSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean//(name = "customRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //* JSON 직렬화 설정
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //*/

        return template;
    }

    /*
    *  chatSubscriber         : "redis_chat"
    *  notificationSubscriber : "redis_notification"
    * */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
                                                        RedisChatSubscriber chatSubscriber, RedisNotificationSubscriber notificationSubscriber) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        /* redis_chat 설정 */
        MessageListenerAdapter chatAdapter = new MessageListenerAdapter(chatSubscriber, "handleMessage");
        chatAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        chatAdapter.afterPropertiesSet(); //수동 생성(new)한 객체 → afterPropertiesSet() 로 vo매핑 설정 필요
        container.addMessageListener(chatAdapter, new ChannelTopic("redis_chat"));

        /* redis_notification 설정 */
        MessageListenerAdapter notificationAdapter = new MessageListenerAdapter(notificationSubscriber, "handleMessage");
        notificationAdapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        notificationAdapter.afterPropertiesSet();
        container.addMessageListener(notificationAdapter, new ChannelTopic("redis_notification"));

        return container;
    }
}
