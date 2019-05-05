package com.kiwi.poker.controller;

import com.kiwi.poker.service.TexasPokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
public class TexasPokerController {
    private final TexasPokerService texasPokerService;

    private static CopyOnWriteArraySet<TexasPokerController> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @Autowired
    public TexasPokerController(TexasPokerService texasPokerService) {
        this.texasPokerService = texasPokerService;
    }

    @OnOpen
    public String enter(Session session) {
        this.session = session;
        webSocketSet.add(this);
        String s = texasPokerService.enter(session.getId());
        sendMessageToSet(s);
        return "success";
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if (message.startsWith("fold")) {
            String s = texasPokerService.fold(session.getId());
            sendMessageToSet(s);
        }
        if (message.startsWith("raise")) {
            String s = texasPokerService.raise(session.getId(), Integer.valueOf(message.substring(6)));
            sendMessageToSet(s);
        }
    }

    @OnClose
    public String quit(Session session) {
        webSocketSet.remove(this);
        String s = texasPokerService.quit(session.getId());
        sendMessageToSet(s);
        return "success";
    }

    private void sendMessageToSet(String message) {
        for (TexasPokerController i : webSocketSet) {
            try {
                i.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
