package com.kiwi.poker.texas;

import com.kiwi.poker.enumerate.TexasPokerRank;

public interface TexasPokerPattern {
    TexasPokerRank getRank();

    int greatThan(TexasPokerPattern t);
}
