package com.kiwi.poker.constant;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

public final class Constant {
    public static final int MAX_TABLE = 10;
    public static final int MAX_PLAYER = 5;
    public static final int MAX_POKER = PokerNumber.values().length * Suit.values().length;
    public static final int MAX_POKER_COMPARE = 5;
}
