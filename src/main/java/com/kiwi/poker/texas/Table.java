package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Integer tableStatus;  // ready? playing? waiting?
    private List<Player> players = new ArrayList<>();  // If someone leave table, the corresponding item maybe null
    private int btn;  // Index of btn player

    private Game game;

    public Table() {

    }

    public void playGame() {
//        game.play(players, btn);
    }

    public void enterTable() {

    }

    public void quitTable() {

    }

}
