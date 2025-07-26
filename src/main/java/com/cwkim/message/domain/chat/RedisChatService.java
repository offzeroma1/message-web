package com.cwkim.message.domain.chat;

import com.cwkim.message.infrastructure.redis.RedisChatPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisChatService {

    private final RedisChatPublisher redisChatPublisher;
    private final RedisChatRepository redisChatRepository; //mongoDB

    public void sendRedisChat(RedisChatVo redisChatVo) {
        try{
            redisChatPublisher.publish(redisChatVo);
            redisChatRepository.save(redisChatVo);
        }catch (Exception e){
            //실패메시지 관리?
            log.error("[FAIL] failed saving 'redis_chat' data. ");
        }
    }

    public List<RedisChatVo> getAllChatByRoomId(String roomId) {
        return redisChatRepository.findByRoomIdOrderByCreateTimeAsc(roomId);
    }
}
