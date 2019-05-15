package com.kiwi.poker.service.impl;

import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.enumerate.TexasPokerRound;
import com.kiwi.poker.model.texaPoker.dto.GetTablesDto;
import com.kiwi.poker.service.TexasPokerService;
import com.kiwi.poker.texas.TexasTable;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TexasPokerServiceImpl implements TexasPokerService {

    private static Map<Integer, TexasTable> tables = new ConcurrentHashMap<>();
    private static Map<String, Boolean> connectedUsers = new ConcurrentHashMap<>();
    private static int maxTable = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public String enter(String id, WebSocketSession session) {
        return Constant.SUCCESS;
    }

    @Override
    public GetTablesDto getTables() {
        return null;
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
    public String sitdown(String id, Integer tableId) {
        return null;
    }

    @Override
    public String standup(String id, Integer tableId) {
        return null;
    }

    private int nextTableId() {
        try {
            lock.lock();
            return maxTable++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String newTable(String id) {
        if (maxTable > Constant.MAX_TABLE) {
            return Constant.FAILED;
        }
        tables.put(nextTableId(), new TexasTable());
        return Constant.SUCCESS;
    }
}
