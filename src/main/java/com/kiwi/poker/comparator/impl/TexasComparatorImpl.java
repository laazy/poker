package com.kiwi.poker.comparator.impl;

import com.kiwi.poker.comparator.TexasComparator;
import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.TexasPokerRank;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class TexasComparatorImpl implements TexasComparator {
    @Override
    public int compare(Poker p1, Poker p2){
        return p1.getNumber().compareTo(p2.getNumber());
    }

    @Override
    public int compare(@NotNull Poker[] p1, @NotNull Poker[] p2)  {
        if (p1.length != p2.length || p2.length != MAX_POKER_COMPARE){
            throw new IllegalArgumentException();
        }

        TexasPokerRank r1 = PokerRankComparator.rank(p1),
                r2 = PokerRankComparator.rank(p2);
        if (r1 != r2){
            return r1.compareTo(r2);
        }
        return getMaxPoker(p1).getNumber().compareTo(getMaxPoker(p2).getNumber());
    }

    @Override
    public int compare(@NotNull List<Poker> p1, @NotNull List<Poker> p2) {
        if (p1.size() != p2.size() || p2.size() != MAX_POKER_COMPARE){
            throw new IllegalArgumentException();
        }
        return compare((Poker[])p1.toArray(), (Poker[])p2.toArray());
    }

    private Poker getMaxPoker(@NotNull Poker[] p){
        Poker tmp = p[0];
        for (Poker i : p){
            if (i.getNumber().compareTo(tmp.getNumber()) > 0){
                tmp = i;
            }
        }
        return tmp;
    }
}
