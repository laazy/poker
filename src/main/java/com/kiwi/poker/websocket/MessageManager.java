package com.kiwi.poker.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class MessageManager {

    private static Map<String, WebSocketSession> users;

    public void addUser(String userId, WebSocketSession session) {
        users.put(userId, session);
    }

    public void removeUser(String userId) {
        users.remove(userId);
    }

    public void sendMessageTo(Collection<String> receivers, String msg) throws IOException {
        for (String userId: receivers) {
            WebSocketSession session = users.get(userId);
            if (session != null) {
                session.sendMessage(new TextMessage(msg));
            }
        }
    }
}
