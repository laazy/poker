package com.kiwi.poker.constant;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

public final class Constant {
    static final int MAX_TABLE = 10;
    static final int MAX_PLAYER = 5;
    static final int MAX_POKER = PokerNumber.values().length * Suit.values().length;
}
