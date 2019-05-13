package com.kiwi.poker.websocket;

import com.kiwi.poker.service.TexasPokerService;
import com.kiwi.poker.service.impl.TexasPokerServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class MessageHandler extends TextWebSocketHandler {

    // TODO: 这里没法自动注入，待修改
    private TexasPokerService texasPokerService = new TexasPokerServiceImpl();
    private MessageManager messageManager = new MessageManager();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        messageManager.addUser(session.getId(), session);
        texasPokerService.enter(session.getId(), session);
        System.out.println("establish");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("received: " + new String(message.asBytes()));
        session.sendMessage(new TextMessage("abc"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        messageManager.removeUser(session.getId());
        texasPokerService.quit(session.getId());
        System.out.println("close");
    }
}
