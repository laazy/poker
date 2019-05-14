package com.kiwi.poker.service.impl;

import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.enumerate.TexasPokerRound;
import com.kiwi.poker.service.TexasPokerService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TexasPokerServiceImpl implements TexasPokerService {

//    private static Map<String, WebSocketSession> onlineUsers = new ConcurrentHashMap<>();
//    private static Map<String, Table> tables = new ConcurrentHashMap<>();

    @Override
    public String enter(String id, WebSocketSession session) {
        return Constant.SUCCESS;
    }

    @Override
    public String fold(String id, TexasPokerRound round) {
        return null;
    }

    @Override
    public String raise(String id, Integer chip, TexasPokerRound round) {
        return null;
    }

    @Override
    public String quit(String id) {
        return Constant.FAILED;
    }

    @Override
    public String sitdown(String id, String gameId) {
        return null;
    }

    @Override
    public String standup(String id, String gameId) {
        return null;
    }
}
