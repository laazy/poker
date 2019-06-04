package com.kiwi.poker.constant;

import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.Suit;

import java.util.concurrent.TimeUnit;

public final class Constant {
    public static final int MAX_TABLE = 10;
    public static final int MAX_PLAYER = 9;
    public static final int MAX_POKER = PokerNumber.values().length * Suit.values().length;
    public static final int MAX_POKER_COMPARE = 5;
    public static final int MAX_AWAIT_TIME = 15000;
    public static final TimeUnit TIME_UNIT = TimeUnit.MICROSECONDS;

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
}
