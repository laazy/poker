package com.kiwi.poker.texas;

import com.kiwi.poker.common.comparator.TexasComparator;
import com.kiwi.poker.common.comparator.impl.TexasComparatorImpl;
import com.kiwi.poker.constant.Constant;
import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;
import com.kiwi.poker.enumerate.TexasOperation;
import com.kiwi.poker.enumerate.TexasPlayerStatus;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
    /**
     * The status of Poker has several types below:
     * 0: for free, it's still covered
     * 1: public, it's in public deck
     * 2~n, in players
     */
    private Set<Poker> deck = new HashSet<>();

    private List<Player> players;
    private Integer btn;
    private Integer smallBlind;
    private static final Integer blind = 200;

    private Lock opLock = new ReentrantLock();
    private Condition opCond = opLock.newCondition();
    private Player waitingPlayer;
    private Player lastPlayer;
    private boolean opDone;
    private Integer currentBet = 0;

    private Poker[] publicCards = new Poker[5];

    private Pot pot = new Pot();

    public Game(List<Player> players, Integer btn) {
        this.players = players;
        this.btn = btn;
        this.smallBlind = (btn + 1) % players.size();
    }

    private void askRound(Integer begin) {
        waitingPlayer = players.get(begin);
        lastPlayer = waitingPlayer;
        do {
            try {
                opLock.lock();
                currentBet = Game.blind;
                if (waitingPlayer.getStatus() != TexasPlayerStatus.ON_DECK) {
                    continue;
                }
                opDone = false;
                try {
                    opCond.await(Constant.MAX_AWAIT_TIME, Constant.TIME_UNIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!opDone) {
                    // follow current round if timeout
                    follow();
                }
            } finally {
                begin = (begin + 1) % players.size();
                waitingPlayer = players.get(begin);
                opLock.unlock();
            }
        } while (waitingPlayer != lastPlayer);
    }

    public void operation(String id, TexasOperation op, Integer money) {
        try {
            opLock.lock();
            if (!waitingPlayer.getId().equals(id) || opDone) {
                return;
            }
            switch (op) {
                case FOLD:
                    fold();
                    break;
                case FOLLEW:
                    if (waitingPlayer.getChips() >= currentBet) {
                        follow();
                    }
                    break;
                case RAISE:
                    if (money >= currentBet && waitingPlayer.getChips() > money) {
                        raise(money);
                    }
                    break;
                case ALL_IN:
                    allIn();
                    break;
            }
            opDone = true;
        } finally {
            opLock.unlock();
        }
    }

    private void fold() {
        waitingPlayer.setStatus(TexasPlayerStatus.FOLDED);
    }

    private void follow() {
        pot.bet(waitingPlayer, currentBet);
        waitingPlayer.pollChips(currentBet);
    }

    private void raise(Integer money) {
        pot.bet(waitingPlayer, money);
        currentBet = money;
        waitingPlayer.pollChips(money);
        lastPlayer = waitingPlayer;
    }

    private void allIn() {
        pot.bet(waitingPlayer, waitingPlayer.getChips());
        if (currentBet < waitingPlayer.getChips()) {
            currentBet = waitingPlayer.getChips();
        }
        waitingPlayer.pollChips(waitingPlayer.getChips());
        waitingPlayer.setStatus(TexasPlayerStatus.ALL_IN);
        lastPlayer = waitingPlayer;
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

    private void takeBlind(Player player, Integer blind) {
        if (player.getChips() < blind) {
            player.pollChips(player.getChips());
            player.setStatus(TexasPlayerStatus.ALL_IN);
        } else {
            player.pollChips(blind);
        }
    }

    private void preflop() {
        Player smallBlind = players.get(this.smallBlind),
                bigBlind = players.get((btn + 2) % players.size());
        takeBlind(smallBlind, blind);
        takeBlind(bigBlind, 2 * blind);
        askRound((btn + 3) % players.size());
    }

    private void flop() {
        for (int i = 0; i < 3; i++){
            publicCards[i] = pollRandomPoker();
        }
        askRound(smallBlind);
    }

    private void turn() {
        publicCards[3] = pollRandomPoker();
        askRound(smallBlind);
    }

    private void river() {
        publicCards[4] = pollRandomPoker();
        askRound(smallBlind);
    }

    class StupidCombine {
        TexasComparator comparator = new TexasComparatorImpl();

        private Poker[][] pickThreeConbineFromPublic() {
            Poker[][] ans = new Poker[20][];
            for (int p = 0; p < 20; p++)
                for (int i = 0; i < 5; i++)
                    for (int j = i + 1; j < 5; j++)
                        for (int k = j + 1; k < 5; k++)
                            ans[p] = new Poker[]{publicCards[i], publicCards[k], publicCards[k], null, null};
            return ans;
        }

        private Poker[][] getPlayerCards(Player player) {
            Poker[][] playerCards = pickThreeConbineFromPublic();
            for (int i = 0; i < 20; i++) {
                playerCards[i][3] = player.getPoker1();
                playerCards[i][4] = player.getPoker2();
                Arrays.sort(playerCards[i]);
            }
            return playerCards;
        }

        private Poker[] getPlayerMaxCards(Player player) {
            Poker[][] allPossible = getPlayerCards(player);
            Poker[] max = allPossible[0];
            for (int i = 1; i < 20; i++) {
                if (comparator.compare(allPossible[i], max) > 0) {
                    max = allPossible[0];
                }
            }
            return max;
        }


        List<Player> getWinner() {
            List<Player> candidate = new ArrayList<>();
            List<Poker[]> candidateCards = new ArrayList<>();
            for (Player i : players) {
                if (i.getStatus() == TexasPlayerStatus.ON_DECK) {
                    candidate.add(i);
                    candidateCards.add(getPlayerMaxCards(i));
                }
            }
            for (int i = 0; i < candidate.size(); i++) {
                for (int j = i + 1; j < candidate.size(); j++) {
                    if (comparator.compare(candidateCards.get(j), candidateCards.get(i)) > 0) {
                        {
                            Poker[] tmp = candidateCards.get(j);
                            candidateCards.set(j, candidateCards.get(i));
                            candidateCards.set(i, tmp);
                        }
                        {
                            Player tmp = candidate.get(j);
                            candidate.set(j, candidate.get(i));
                            candidate.set(i, tmp);
                        }
                    }
                }
            }
            return candidate;
        }
    }

    private void endGame() {
        StupidCombine sc = new StupidCombine();
        List<Player> winners = sc.getWinner();
        pot.win(winners);
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

    public void play() {
        initGame();
        initPlayerHands();
        preflop();
        flop();
        turn();
        river();
        endGame();
    }
}

