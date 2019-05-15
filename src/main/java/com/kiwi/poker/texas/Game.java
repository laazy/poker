package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;
import com.kiwi.poker.enumerate.TexasPokerStatus;

import java.util.*;

public class Game {
    private Map<Poker, TexasPokerStatus> deck = new HashMap<>();
    private Poker[] publicCards = new Poker[5];

    public Game() {
        for (PokerNumber pn : PokerNumber.values()) {
            for (Suit s : Suit.values()) {
                deck.put(new Poker(s, pn), TexasPokerStatus.FREE);
            }
        }
    }

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

    private Poker getRandomPoker() {
        Set<Poker> pokers = deck.keySet();
        int randomIndex = new Random().nextInt(pokers.size());
        Poker ans = null;
        for (Poker p : pokers) {
            if (randomIndex == 0) {
                ans = p;
                break;
            }
            randomIndex--;
        }
        if (ans == null){
            throw new RuntimeException("Game: deck has no key");
        }
        return ans;
    }

    private boolean isRaiseValid() {
        return true;
    }
}
