package com.kiwi.poker.common.comparator.impl;

import com.kiwi.poker.common.comparator.TexasComparator;
import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.texas.TexasPattern;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class TexasComparatorImpl implements TexasComparator {
    @Override
    public int compare(Poker p1, Poker p2) {
        return p1.getNumber().compareTo(p2.getNumber());
    }

    @Override
    public int compare(@NotNull Poker[] p1, @NotNull Poker[] p2) {
        if (p1.length != p2.length || p2.length != MAX_POKER_COMPARE) {
            throw new IllegalArgumentException();
        }
        TexasPattern pattern1 = new TexasPattern(p1), pattern2 = new TexasPattern(p2);
        return pattern1.compareTo(pattern2);
    }

    @Override
    public int compare(@NotNull List<Poker> p1, @NotNull List<Poker> p2) {
        if (p1.size() != p2.size() || p2.size() != MAX_POKER_COMPARE) {
            throw new IllegalArgumentException();
        }
        return compare((Poker[]) p1.toArray(), (Poker[]) p2.toArray());
    }

}
