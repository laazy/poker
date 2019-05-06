package com.kiwi.poker.texas.pattern;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;
import com.kiwi.poker.texas.TexasPokerPattern;

public class StraightFlush implements TexasPokerPattern {
    private static final TexasPokerRank rank = TexasPokerRank.STRAIGHT_FLUSH;
    private PokerNumber highest;

    public StraightFlush(PokerNumber highest) {
        this.highest = highest;
    }

    @Override
    public TexasPokerRank getRank() {
        return rank;
    }

    @Override
    public int greatThan(TexasPokerPattern t) {
        int tmp = rank.compareTo(t.getRank());
        if (tmp == 0){
            if(t.getClass() != this.getClass()){
                throw new IllegalArgumentException("can't compare");
            }
            return highest.compareTo(((StraightFlush)t).highest);
        }
        return tmp;
    }

}