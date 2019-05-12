package com.kiwi.poker.service.impl;

import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.domain.Table;
import com.kiwi.poker.domain.User;
import com.kiwi.poker.service.TexasPokerService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TexasPokerServiceImpl implements TexasPokerService {

//    private static Map<String, WebSocketSession> onlineUsers = new ConcurrentHashMap<>();
    private static Map<String, User> onlineUsers = new ConcurrentHashMap<>();
    private static Map<String, Table> tables = new ConcurrentHashMap<>();

    @Override
    public String enter(String id, WebSocketSession session) {
        if (onlineUsers.containsKey(id)) {
            return Constant.FAILED;
        }
        onlineUsers.put(id, new User(id, session));
        return Constant.SUCCESS;
    }

    @Override
    public String fold(String id) {
        return null;
    }

    @Override
    public String raise(String id, Integer chip) {
        return null;
    }

    @Override
    public Integer getOnlineNum() {
        return onlineUsers.size();
    }

    @Override
    public String quit(String id) {
        if (onlineUsers.containsKey(id)) {
            onlineUsers.remove(id);
            return Constant.SUCCESS;
        }
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
