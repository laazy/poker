package com.kiwi.poker.common;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class Connection {

    WebSocketSession session;

    public Connection(WebSocketSession session) {
        this.session = session;
    }

    public void sendMessage(String s){
        try {
            session.sendMessage(new TextMessage(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
