package com.cwkim.message.domain.chat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis-chat")
public class RedisChatController {

    private final RedisChatService redisChatService;

    @PostMapping
    public void sendRedisChat(@RequestBody RedisChatVo redisChatVo) {
        redisChatService.sendRedisChat(redisChatVo);
    }
}
