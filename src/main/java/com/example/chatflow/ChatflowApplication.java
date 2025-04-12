package com.example.chatflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatflowApplication.class, args);
	}

}
