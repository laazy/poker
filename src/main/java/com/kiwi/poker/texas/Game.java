package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

import java.util.*;

public class Game implements Runnable {
    /**
     * The status of Poker has several types below:
     * 0: for free, it's still covered
     * 1: public, it's in public deck
     * 2~n, in players
     *
     */
    private Set<Poker> deck = new HashSet<>();
    private List<Player> players;
    private Integer btn;
    private Poker[] publicCards = new Poker[5];


    private static final int INIT_PUBLIC = 3;

    public Game() {
    }

    // contains main pot and side pots
    private List<Pot> pots = new ArrayList<>();

    public void play(List<Player> players, Integer btn) {
        this.players = players;
        this.btn = btn;
        initGame();
        initPlayerHands();
        preflop();
        flop();
        turn();
        river();
        endGame();
    }

//    public static void main(String[] args) {
//        Map<Poker, TexasPokerStatus> deck = new HashMap<>();
//        Poker p = new Poker(Suit.SPADE, PokerNumber.POKER_A);
//        deck.put(p, TexasPokerStatus.FREE);
//        deck.put(p, TexasPokerStatus.EIGHTH);
//        System.out.println(deck.size());
//    }

    private void initGame() {
        for (Suit s: Suit.values()){
            for (PokerNumber pn : PokerNumber.values()){
                deck.add(new Poker(s, pn));
            }
        }
    }

    private void initPlayerHands() {
        for (Player p : players){
            p.setPoker(pollRandomPoker(), pollRandomPoker());
        }
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

    private Poker pollRandomPoker() {
        int randomIndex = new Random().nextInt(deck.size());
        for (Poker p : deck) {
            if (randomIndex == 0) {
                deck.remove(p);
                return p;
            }
            randomIndex--;
        }
        throw new RuntimeException("Game: deck has no key");
    }

    @Override
    public void run() {

    }

    private boolean isRaiseValid() {
        return true;
    }
}
