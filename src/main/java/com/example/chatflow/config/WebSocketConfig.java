package com.example.chatflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:5173", "http://localhost:5174")
                .withSockJS();
    }

    @Bean
    public SimpMessagingTemplate messagingTemplate(@Qualifier("clientOutboundChannel") MessageChannel messageChannel) {
        SimpMessagingTemplate template = new SimpMessagingTemplate(messageChannel);
        template.setMessageConverter(new MappingJackson2MessageConverter());
        return template;
    }
}
