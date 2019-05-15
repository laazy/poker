package com.kiwi.poker.texas;

import com.kiwi.poker.common.Table;
import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.enumerate.TableStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TexasTable implements Table {
    private TableStatus tableStatus;  // ready? playing? waiting?

    private Player[] players = new Player[Constant.MAX_PLAYER];
    private int currentPlays = 0;
    private Lock playersLock = new ReentrantLock();
    //    private Map<String, Player> players = new ConcurrentHashMap<>();  // If someone leave table, the corresponding item maybe null
    private int btn;  // Index of btn player

    private Game game;
    private Thread gameThread;

    public TexasTable() {

    }

    @Override
    public String addPlayer(String id, Integer pos) {
        if (tableStatus != TableStatus.WAITING) {
            return Constant.FAILED;
        }
        try {
            playersLock.lock();
            if (currentPlays > Constant.MAX_PLAYER || players[pos] != null) {
                return Constant.FAILED;
            }
            for (Player p : players) {
                if (p != null && p.getId().equals(id)) {
                    return Constant.FAILED;
                }
            }
            players[pos] = new Player(id, null, null, null);
            currentPlays++;
            return Constant.SUCCESS;
        } finally {
            playersLock.unlock();
        }
    }

    @Override
    public String removePlayer(String id) {
        if (tableStatus != TableStatus.WAITING) {
            return Constant.FAILED;
        }
        try {
            playersLock.lock();
            for (int i = 0; i < Constant.MAX_PLAYER; i++) {
                if (players[i] != null && players[i].getId().equals(id)) {
                    players[i] = null;
                    return Constant.SUCCESS;
                }
            }
            return Constant.FAILED;
        } finally {
            playersLock.unlock();
        }
    }

    @Override
    public void startGame() {
        tableStatus = TableStatus.READY;
        List<Player> curPlayers = new ArrayList<>(currentPlays);
        for (int i = 0; i < Constant.MAX_PLAYER; i++){
            if (players[i] != null){
                curPlayers.add(players[i]);
            }
        }
        game.play(curPlayers, 0);
//        gameThread = new Thread(game);
//        gameThread.run();
    }

    @Override
    public String endGame() {
        return null;
    }
}
