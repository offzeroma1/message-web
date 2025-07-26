package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.chat.RedisChatVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RedisChatSubscriber {

    public void handleMessage(RedisChatVo message) {

        log.info("[SUCCESS] {}", message);

    }
}
