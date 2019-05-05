package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Table {
    private List<Poker> pokers = new ArrayList<Poker>();
    private List<Player> players = new ArrayList<>();

    public Table() {
        for (PokerNumber i : PokerNumber.values()) {
            for (Suit j : Suit.values()) {
                pokers.set(1, new Poker(j, i));
            }
        }
    }
}
