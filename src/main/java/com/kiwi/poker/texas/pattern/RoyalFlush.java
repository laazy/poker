package com.kiwi.poker.texas.pattern;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;
import com.kiwi.poker.texas.TexasPokerPattern;

public class RoyalFlush implements TexasPokerPattern {
    private static final TexasPokerRank rank = TexasPokerRank.FLUSH;

    public RoyalFlush() {
    }

    @Override
    public TexasPokerRank getRank() {
        return rank;
    }

    @Override
    public int greatThan(TexasPokerPattern t) {
        int tmp = rank.compareTo(t.getRank());
        return tmp;
    }

}
