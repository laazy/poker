package com.kiwi.poker.comparator.impl;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class PokerRankComparator {

    static final PokerNumber[] ROYAL_FLUSH = {PokerNumber.POKER_10, PokerNumber.POKER_J,
            PokerNumber.POKER_Q, PokerNumber.POKER_K, PokerNumber.POKER_A};


    public static TexasPokerRank rank(@NotNull Poker[] p){
        if (p.length != MAX_POKER_COMPARE){
            throw new IllegalArgumentException();
        }
        if (isRoyalFlush(p)){
            return TexasPokerRank.ROYAL_FLUSH;
        }
        if (isStraightFlush(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isFourKind(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isFullHouse(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isFlush(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isStraight(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isThreeKind(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isTwoPair(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        if (isPair(p)){
            return TexasPokerRank.STRAIGHT_FLUSH;
        }
        return TexasPokerRank.HIGH_CARD;
    }

    public static TexasPokerRank rank(@NotNull List<Poker> p){
        return rank((Poker[])p.toArray());
    }

    private static boolean isRoyalFlush(Poker[] p){
        for (int i = 0; i < MAX_POKER_COMPARE; i++){
            if (p[i].getNumber() != ROYAL_FLUSH[i]){
                return false;
            }
        }
        return true;
    }

    private static boolean isStraightFlush(Poker[] p){
        return true;
    }

    private static boolean isFourKind(Poker[] p){
        return true;
    }

    private static boolean isFullHouse(Poker[] p){
        return true;
    }

    private static boolean isFlush(Poker[] p){
        return true;
    }

    private static boolean isStraight(Poker[] p){
        return true;
    }

    private static boolean isThreeKind(Poker[] p){
        return true;
    }

    private static boolean isTwoPair(Poker[] p){
        return true;
    }

    private static boolean isPair(Poker[] p){
        return true;
    }
}
