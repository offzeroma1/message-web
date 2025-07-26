package com.cwkim.message.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisChatVo {

    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private long createTime;
    private boolean status; //send : 1, read : 0


}
