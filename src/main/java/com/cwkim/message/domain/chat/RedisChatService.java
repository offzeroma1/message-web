package com.cwkim.message.domain.chat;

import com.cwkim.message.infrastructure.redis.RedisChatPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisChatService {
    private final RedisChatPublisher redisChatPublisher;

    public void sendRedisChat(RedisChatVo redisChatVo) {
        redisChatPublisher.publish(redisChatVo);
    }
}
