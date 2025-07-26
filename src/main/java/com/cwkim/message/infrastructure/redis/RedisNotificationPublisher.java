package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.Notification.RedisNotificationVo;
import com.cwkim.message.infrastructure.AbstractEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisNotificationPublisher extends AbstractEventPublisher<RedisNotificationVo> {
    //@Qualifier("customRedisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    protected String getChannel() {
        return "redis_notification";
    }
    @Override
    public Object setMessage(RedisNotificationVo redisNotificationVo) {
        return redisNotificationVo;
    }

    @Override
    protected void send(String channel, Object message) {
        try{
            Long subs = redisTemplate.convertAndSend(channel, message);
            log.info("[SUCCESS] send redis message. ");
            if(subs == 0){
                log.info("{} 의 구독자가 0입니다.", channel);
            }
        }catch (RedisConnectionFailureException e) {
            log.error("Redis 연결 실패: {}", e.getMessage());
        }catch (RuntimeException e){
            log.error("Redis 메시지 전송 중 예기치 못한 오류: {}", e.getMessage());
        }
    }
}
