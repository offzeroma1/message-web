package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.chat.RedisChatVo;
import com.cwkim.message.infrastructure.AbstractEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class RedisChatPublisher extends AbstractEventPublisher<RedisChatVo> {

    //@Qualifier("customRedisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    protected String getChannel() {
        return "redis_chat";
    }
    @Override
    public Object setMessage(RedisChatVo redisChatVo) {
        return redisChatVo;
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
