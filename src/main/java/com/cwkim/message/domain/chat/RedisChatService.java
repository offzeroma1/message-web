package com.cwkim.message.domain.chat;

import com.cwkim.message.infrastructure.redis.RedisChatPublisher;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisChatService {

    private final RedisChatPublisher redisChatPublisher; //redis send
    private final RedisChatRepository redisChatRepository; //mongoDB


    public void sendRedisChat(RedisChatVo redisChatVo) {
        try{
            redisChatPublisher.publish(redisChatVo);
        }catch (Exception e){
            //실패메시지 관리?
            log.error("[FAIL] failed saving 'redis_chat' data. ");
        }
    }

    @Operation(summary = "특정 Room의 메시지 조회", description = "roomId 기준으로 메시지 리스트 조회")
    public List<RedisChatVo> getAllChatByRoomId(String roomId) {
        return redisChatRepository.findByRoomIdOrderByCreateTimeAsc(roomId);
    }
}
