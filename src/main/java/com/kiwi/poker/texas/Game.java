package com.kiwi.poker.texas;

import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;
import com.kiwi.poker.enumerate.TexasOperation;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game implements Runnable {
    /**
     * The status of Poker has several types below:
     * 0: for free, it's still covered
     * 1: public, it's in public deck
     * 2~n, in players
     */
    private Set<Poker> deck = new HashSet<>();

    private List<Player> players;
    private Integer btn;

    private Lock opLock = new ReentrantLock();
    private Condition opCond = opLock.newCondition();
    private Player waitingPlayer;
    private Integer currentBet = 0;

    private Poker[] publicCards = new Poker[5];

    private static final int INIT_PUBLIC = 3;

    // contains main pot and side pots
//    private List<Pot> pots = new ArrayList<>();
    // use simplest pot for now
    Pot pot = new Pot();

    public Game() {
    }

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

    private void askRound(Integer begin, Integer leastBet){
        try {
            opLock.lock();
            Integer i = (begin + players.size() - 1) % players.size();
            currentBet = leastBet;
            waitingPlayer = players.get(begin);
            try {
                opCond.await(Constant.MAX_AWAIT_TIME, Constant.TIME_UNIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } finally {
            opLock.unlock();
        }
    }

    public void operation(String id, TexasOperation op, Integer money) {
        try {
            opLock.lock();
            if (!waitingPlayer.getId().equals(id)) {
                return;
            }
            switch (op) {
                case FOLD:
                    fold();
                    break;
                case FOLLEW:
                    follew();
                    break;
                case RAISE:
                    if (money >= currentBet) {
                        raise(money);
                    }
                    break;
                case ALL_IN:
                    allIn();
                    break;
            }
        } finally {
            opLock.unlock();
        }
    }

    void fold() {

    }

    void follew() {

    }

    void raise(Integer money) {

    }

    void allIn() {

    }

    private void initGame() {
        for (Suit s : Suit.values()) {
            for (PokerNumber pn : PokerNumber.values()) {
                deck.add(new Poker(s, pn));
            }
        }
    }

    private void initPlayerHands() {
        for (Player p : players) {
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
}

