package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.Notification.RedisNotificationVo;
import com.cwkim.message.domain.chat.RedisChatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisNotificationSubscriber {

    public void handleMessage(RedisNotificationVo message) {

        log.info("[SUCCESS] subscribed redis message : {}", message);
    }

}
