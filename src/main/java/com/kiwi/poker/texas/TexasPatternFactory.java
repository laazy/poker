package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.texas.pattern.*;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class TexasPatternFactory {
    private TexasPatternFactory(){}

    public static TexasPokerPattern getPattern(Poker[] p){
        if (p.length != MAX_POKER_COMPARE){
            throw new IllegalArgumentException("illegal array length: " + String.valueOf(p.length));
        }
        TexasPokerPattern ans = getPair(p);
        if (ans != null){
            return ans;
        }
        ans = getTwoPair(p);
        if (ans != null){
            return ans;
        }
        ans = getThreeKind(p);
        if (ans != null){
            return ans;
        }
        ans = getStraight(p);
        if (ans != null){
            return ans;
        }
        ans = getFlush(p);
        if (ans != null){
            return ans;
        }
        ans = getFullHouse(p);
        if (ans != null){
            return ans;
        }
        ans = getFourKind(p);
        if (ans != null){
            return ans;
        }
        ans = getStraightFlush(p);
        if (ans != null){
            return ans;
        }
        ans = getRoyalFlush(p);
        if (ans != null){
            return ans;
        }
        return new HighCard(p[MAX_POKER_COMPARE - 1].getNumber());
    }

    private static TexasPokerPattern getRoyalFlush(Poker[] p){
        if  (getStraightFlush(p) != null && p[0].getNumber() == PokerNumber.POKER_10){
            return new RoyalFlush();
        }
        return null;
    }

    private static TexasPokerPattern getStraightFlush(Poker[] p){
        if (getStraight(p) != null && getFlush(p) != null){
            return new StraightFlush(p[4].getNumber());
        }
        return null;
    }

    private static TexasPokerPattern getFourKind(Poker[] p){
        if ((p[1].getNumber() == p[2].getNumber() && p[2].getNumber() == p[3].getNumber()) &&
                (p[0].getNumber() == p[1].getNumber() || p[4].getNumber() == p[1].getNumber())){
            return new FourKind(p[2].getNumber());
        }
        return null;
    }

    private static TexasPokerPattern getFullHouse(Poker[] p){
        if (getThreeKind(p) != null && getTwoPair(p) != null){
            PokerNumber pn = p[0].getNumber();
            if (pn == p[2].getNumber()){
                pn = p[4].getNumber();
            }
            return new FullHouse(p[2].getNumber(), pn);
        }
        return null;
    }

    private static TexasPokerPattern getFlush(Poker[] p){
        if (p[0].getSuit() == p[1].getSuit() && p[1].getSuit() == p[2].getSuit() &&
                p[2].getSuit() == p[3].getSuit() && p[3].getSuit() == p[4].getSuit()){
            return new Flush(p[4].getNumber());
        }
        return null;
    }

    private static TexasPokerPattern getStraight(Poker[] p){
        for (int i = 3; i > 0; i--){
            if (p[i].getNumber().ordinal() - p[i - 1].getNumber().ordinal() != 1){
                return null;
            }
        }
        if (p[4].getNumber() == PokerNumber.POKER_A && p[0].getNumber() == PokerNumber.POKER_2){
            return new Straight(PokerNumber.POKER_A);
        }
        if (p[4].getNumber().ordinal() - p[3].getNumber().ordinal() == 1){
            return new Straight(p[4].getNumber());
        }
        return null;
    }

    private static TexasPokerPattern getThreeKind(Poker[] p){
        for (int i = 0; i < 2; i++){
            if (p[i].getNumber() == p[i + 1].getNumber() && p[i + 1].getNumber() == p[i + 2].getNumber()){
                return new ThreeKind(p[i].getNumber());
            }
        }
        return null;
    }

    private static TexasPokerPattern getTwoPair(Poker[] p){
        int pairCnt = 0;
        PokerNumber pn = null;
        for (int i = 0; i < 3; i++){
            if (p[i].getNumber() == p[i + 1].getNumber()){
                pairCnt++;
                pn = p[i].getNumber();
                i++;
            }
        }
        if (pairCnt == 2){
            return new TwoPair(p[3].getNumber(), p[1].getNumber());
        }
        return null;
    }

    private static TexasPokerPattern getPair(Poker[] p){
        for (int i = 0; i < 3; i++){
            if (p[i].getNumber() == p[i + 1].getNumber()){
                return new Pair(p[i].getNumber());
            }
        }
        return null;
    }
}
