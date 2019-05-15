package com.kiwi.poker.texas;

import com.kiwi.poker.common.Table;
import com.kiwi.poker.constant.Constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TexasTable implements Table {
    private Integer tableStatus;  // ready? playing? waiting?
    private Map<String, Player> players = new ConcurrentHashMap<>();  // If someone leave table, the corresponding item maybe null
    private int btn;  // Index of btn player

    private Game game;

    public TexasTable() {

    }

    @Override
    public String addPlayer(String id) {
        if (players.size() == Constant.MAX_PLAYER || players.containsKey(id)) {
            return Constant.FAILED;
        }
        players.put(id, new Player(null, null, null, null));
        return Constant.SUCCESS;
    }

    @Override
    public String removePlayer(String id) {
        players.remove(id);
        return null;
    }

    @Override
    public void startGame() {

    }

    @Override
    public String endGame() {
        return null;
    }
}
