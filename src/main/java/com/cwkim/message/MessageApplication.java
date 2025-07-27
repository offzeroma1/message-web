package com.cwkim.message;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@OpenAPIDefinition(
		info = @Info(
				title = "Chat & Notification API",
				version = "v1",
				description = "Redis Pub/Sub + WebSocket + MongoDB 기반 실시간 채팅 및 알림 API"
		)
)

@SpringBootApplication/* jdbc 연결 넘기기 (exclude = {DataSourceAutoConfiguration.class})*/
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

}
