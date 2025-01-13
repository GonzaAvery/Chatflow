package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Habilita el soporte de STOMP sobre WebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registra el endpoint de WebSocket que los clientes usarán para conectarse
        registry.addEndpoint("/chat").withSockJS(); // "/chat" es el endpoint WebSocket
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Configura el prefijo para los destinos donde se envían los mensajes
        registry.setApplicationDestinationPrefixes("/app"); // Prefijo para destinos de la aplicación
        registry.enableSimpleBroker("/topic"); // Prefijo para destinos de los mensajes de suscripción
    }
}