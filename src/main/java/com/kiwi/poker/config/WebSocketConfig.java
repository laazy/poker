package com.kiwi.poker.config;

import com.kiwi.poker.websocket.MessageHandler;
import com.kiwi.poker.websocket.WebsocketHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MessageHandler(), "/websocket")
                .addInterceptors(new WebsocketHandshakeInterceptor())
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
