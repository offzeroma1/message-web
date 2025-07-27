package com.cwkim.message.infrastructure.redis;

import com.cwkim.message.domain.chat.RedisChatRepository;
import com.cwkim.message.domain.chat.RedisChatVo;
import com.mongodb.MongoException;
import jakarta.websocket.SessionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class RedisChatSubscriber {

    private final RedisChatRepository redisChatRepository; //mongoDB
    private final SimpMessagingTemplate messagingTemplate; //Websocket


    public void handleMessage(RedisChatVo redisChatVo) {

        try{
            // mongoDB 저장
            redisChatRepository.save(redisChatVo);
            log.info("[SUCCESS] saved 'redis_chat' in mongo. : {}", redisChatVo);
        } catch(MongoException e) {
            log.error("[FAIL] mongoDB 네트워크 장애 발생 : {}", e);
        } catch(DataAccessException e) {
            log.error("[FAIL] mongoDB 네트워크 연결 실패 : {}", e);
        } catch(Exception e) {
            log.error("[FAIL] mongoDB 저장 실패 : {}", e);
        }

        try{
            // Websocket 실시간 알림 발행
            messagingTemplate.convertAndSend("/topic/chat/" + redisChatVo.getRoomId(), redisChatVo);
            log.info("[SUCCESS] send message notification.");
        } catch(MessagingException e) {
            log.warn("[FAIL] websocket 알림 전송 실패 : {}", e);
        } catch (Exception e) {
            log.warn("[FAIL] websocket 알림 전송 실패 : {}", e);
        }
    }
}
