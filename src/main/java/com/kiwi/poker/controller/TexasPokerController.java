package com.kiwi.poker.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{sid}")
@Component
public class TexasPokerController {

    @OnOpen
    public String enter(Session session){

    }

}
