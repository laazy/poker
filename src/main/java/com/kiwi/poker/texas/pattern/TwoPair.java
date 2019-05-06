package com.kiwi.poker.texas.pattern;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;
import com.kiwi.poker.texas.TexasPokerPattern;

public class TwoPair implements TexasPokerPattern {
    private static final TexasPokerRank rank = TexasPokerRank.FLUSH;
    private PokerNumber highest;
    private PokerNumber last;

    public TwoPair(PokerNumber highest, PokerNumber last) {
        this.highest = highest;
        this.last = last;
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
            int tmp2 = highest.compareTo(((TwoPair)t).highest);
            if (tmp2 == 0){
                return last.compareTo(((TwoPair)t).last);
            }
            return tmp2;
        }
        return tmp;
    }

}
