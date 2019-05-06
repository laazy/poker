package com.kiwi.poker.texas.pattern;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;
import com.kiwi.poker.texas.TexasPokerPattern;

public class FullHouse implements TexasPokerPattern {
    private static final TexasPokerRank rank = TexasPokerRank.FULL_HOUSE;
    private PokerNumber three;
    private PokerNumber pair;

    public FullHouse(PokerNumber three, PokerNumber pair) {
        this.three = three;
        this.pair = pair;
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
            int tmp2 = this.three.compareTo(((FullHouse)t).three);
            if (tmp2 == 0){
                return this.pair.compareTo(((FullHouse)t).pair);
            }
            return tmp2;
        }
        return tmp;
    }
}
