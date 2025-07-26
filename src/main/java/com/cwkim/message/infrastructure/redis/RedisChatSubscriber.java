package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.chat.RedisChatVo;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RedisChatSubscriber {

    public void handleMessage(RedisChatVo message) {

        log.info("[SUCCESS] {}", message);

    }
}
