package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Poker> deck = new ArrayList<>();
    private Poker[] publicCards = new Poker[5];

    private List<Player> gamePlayers;
    private int gameBtn;

    // contains main pot and side pots
    private List<Pot> pots = new ArrayList<>();


    public void play(List<Player> players, Player btn) {
        initGame();
        initPlayerHands();
        preflop();
        flop();
        turn();
        river();
        endGame();
    }

    private void initGame() {
        deck.clear();
        for (PokerNumber i : PokerNumber.values()) {
            for (Suit j : Suit.values()) {
                deck.add(new Poker(j, i));
            }
        }
    }

    private void initPlayerHands() {

    }

    private void preflop() {

    }

    private void flop() {

    }

    private void turn() {

    }

    private void river() {

    }

    private void endGame() {

    }


    private Poker getOnePoker() {
        return null;
    }

    private boolean isRaiseValid() {
        return true;
    }
}
