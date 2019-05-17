package com.kiwi.poker.domain;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

public class Poker implements Comparable<Poker> {
    public Poker(Suit suit, PokerNumber number) {
        this.suit = suit;
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public PokerNumber getNumber() {
        return number;
    }

    @Override
    public int compareTo(Poker o) {
        return this.number.compareTo(o.number);
    }

    private final Suit suit;
    private final PokerNumber number;
}
