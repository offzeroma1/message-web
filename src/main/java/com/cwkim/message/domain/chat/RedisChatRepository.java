package com.cwkim.message.domain.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RedisChatRepository extends MongoRepository<RedisChatVo, String> {
    List<RedisChatVo> findByRoomIdOrderByCreateTimeAsc(String roomId);
}