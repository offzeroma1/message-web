package com.cwkim.message.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "redis_chat")
public class RedisChatVo {
    @Id
    private String id;

    private String roomId;
    private String senderId;
    private String receiverId;
    private String content;
    private long createTime;
    private boolean status; //send : 1, read : 0


}
