package com.kiwi.poker.comparator.impl;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class PokerRankComparator {


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
        return isStraightFlush(p) && p[0].getNumber() == PokerNumber.POKER_10;
    }

    private static boolean isStraightFlush(Poker[] p){
        return isStraight(p) && isFlush(p);
    }

    private static boolean isFourKind(Poker[] p){
        return (p[1].getNumber() == p[2].getNumber() && p[2].getNumber() == p[3].getNumber()) &&
                (p[0].getNumber() == p[1].getNumber() || p[4].getNumber() == p[1].getNumber());
    }

    private static boolean isFullHouse(Poker[] p){
        return isThreeKind(p) && isTwoPair(p);
    }

    private static boolean isFlush(Poker[] p){
        return p[0].getSuit() == p[1].getSuit() && p[1].getSuit() == p[2].getSuit() &&
               p[2].getSuit() == p[3].getSuit() && p[3].getSuit() == p[4].getSuit();
    }

    private static boolean isStraight(Poker[] p){
        for (int i = 3; i > 0; i--){
            if (p[i].getNumber().ordinal() - p[i - 1].getNumber().ordinal() != 1){
                return false;
            }
        }
        return  (p[4].getNumber() == PokerNumber.POKER_A && p[0].getNumber() == PokerNumber.POKER_2) ||
                (p[4].getNumber().ordinal() - p[3].getNumber().ordinal() == 1);
    }

    private static boolean isThreeKind(Poker[] p){
        for (int i = 0; i < 2; i++){
            if (p[i].getNumber() == p[i + 1].getNumber() && p[i + 1].getNumber() == p[i + 2].getNumber()){
                return true;
            }
        }
        return false;
    }

    private static boolean isTwoPair(Poker[] p){
        int pairCnt = 0;
        for (int i = 0; i < 3; i++){
            if (p[i].getNumber() == p[i + 1].getNumber()){
                pairCnt++;
                i++;
            }
        }
        return true;
    }

    private static boolean isPair(Poker[] p){
        for (int i = 0; i < 3; i++){
            if (p[i].getNumber() == p[i + 1].getNumber()){
                return true;
            }
        }
        return false;
    }
}
