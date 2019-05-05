package com.kiwi.poker.comparator;

import com.kiwi.poker.domain.Poker;

import java.util.List;

public interface TexasComparator {
    int compare(Poker p1, Poker p2);

    int compare(Poker[] p1, Poker p2);

    int compare(List<Poker> p1, List<Poker> p2);

}
