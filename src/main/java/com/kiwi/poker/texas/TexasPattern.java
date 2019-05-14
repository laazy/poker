package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.PokerNumber;
import com.kiwi.poker.enumerate.TexasPokerRank;

import static com.kiwi.poker.constant.Constant.MAX_POKER_COMPARE;

public class TexasPattern {
    private TexasPokerRank rank;
    private int texasScore;

    private static final int PATTERN_MODIFIER = 0x100000;

    public TexasPattern(Poker[] p) {
        setPatternFromPoker(p);
    }

    public int compareTo(TexasPattern t) {
        return Integer.compare(this.texasScore, t.texasScore);
    }

    public TexasPokerRank getRank() {
        return rank;
    }

    public void setPatternFromPoker(Poker[] p) {
        if (p.length != MAX_POKER_COMPARE) {
            throw new IllegalArgumentException("illegal array length: " + String.valueOf(p.length));
        }
        boolean isNotHigh = setRoyalFlush(p) || setStraightFlush(p) || setFourKind(p) || setFullHouse(p) ||
                setFlush(p) || setStraight(p) || setThreeKind(p) || setTwoPair(p) || setPair(p);
        if (!isNotHigh) {
            rank = TexasPokerRank.HIGH_CARD;
            for (int i = 0; i < 5; i++) {
                texasScore += p[i].getNumber().ordinal() << (i * 4);
            }
        }
    }

    private boolean setRoyalFlush(Poker[] p) {
        if (setStraightFlush(p) && p[0].getNumber() == PokerNumber.POKER_10) {
            rank = TexasPokerRank.ROYAL_FLUSH;
            return true;
        }
        return false;
    }

    private boolean setStraightFlush(Poker[] p) {
        if (setFlush(p) && setStraight(p)) {
            rank = TexasPokerRank.STRAIGHT_FLUSH;
            return true;
        }
        return false;
    }

    private boolean setFourKind(Poker[] p) {
        PokerNumber pn = null;
        if ((p[1].getNumber() == p[2].getNumber() && p[2].getNumber() == p[3].getNumber())) {
            if (p[0].getNumber() == p[1].getNumber()) {
                pn = p[4].getNumber();
            } else if (p[4].getNumber() == p[1].getNumber()) {
                pn = p[0].getNumber();
            }
        }
        if (pn != null){
            rank = TexasPokerRank.FOUR_OF_A_KIND;
            texasScore = PATTERN_MODIFIER * rank.ordinal() + p[2].getNumber().ordinal() + pn.ordinal();
        }
        return pn != null;
    }

    private boolean setFullHouse(Poker[] p) {
        if (setThreeKind(p) && setTwoPair(p)) {
            PokerNumber pn = p[0].getNumber();
            if (pn == p[2].getNumber()) {
                pn = p[4].getNumber();
            }
            rank = TexasPokerRank.FULL_HOUSE;
            texasScore = PATTERN_MODIFIER * rank.ordinal() + p[2].getNumber().ordinal() << 4 + pn.ordinal();
            return true;
        }
        return false;
    }

    private boolean setFlush(Poker[] p) {
        if (p[0].getSuit() == p[1].getSuit() && p[1].getSuit() == p[2].getSuit() &&
                p[2].getSuit() == p[3].getSuit() && p[3].getSuit() == p[4].getSuit()) {
            texasScore = 0;
            rank = TexasPokerRank.FLUSH;
            for (int i = 4; i >= 0; i--) {
                texasScore <<= 4;
                texasScore += p[i].getNumber().ordinal();
            }
            texasScore += PATTERN_MODIFIER * rank.ordinal();
        }
        return false;
    }

    private boolean setStraight(Poker[] p) {
        for (int i = 0; i < 3; i++) {
            if (p[i].getNumber().ordinal() != p[i + 1].getNumber().ordinal() - 1) {
                return false;
            }
        }
        if (p[4].getNumber() == PokerNumber.POKER_A && p[0].getNumber() == PokerNumber.POKER_2) {
            rank = TexasPokerRank.STRAIGHT;
            texasScore = PATTERN_MODIFIER * rank.ordinal(); // the least pattern in straight rank
            return true;
        }
        if (p[4].getNumber().ordinal() - p[3].getNumber().ordinal() == 1) {
            rank = TexasPokerRank.STRAIGHT;
            texasScore = PATTERN_MODIFIER * rank.ordinal() + p[0].getNumber().ordinal();
            return true;
        }
        return false;
    }

    private boolean setThreeKind(Poker[] p) {
        int pos;
        for (pos = 0; pos <= 2; pos++) {
            if (p[pos].getNumber() == p[pos + 1].getNumber() && p[pos + 1].getNumber() == p[pos + 2].getNumber()) {
                break;
            }
        }
        if (pos > 2) {
            return false;
        }
        rank = TexasPokerRank.THREE_OF_A_KIND;
        // set three kind
        texasScore = p[pos].getNumber().ordinal();
        for (int i = 4; i >= 0; i--) {
            if (i == pos || i == pos + 1 || i == pos + 2) {
                continue;
            }
            texasScore <<= 4;
            texasScore += p[i].getNumber().ordinal();
        }
        texasScore += rank.ordinal() * PATTERN_MODIFIER;
        return true;
    }

    private boolean setTwoPair(Poker[] p) {
        int pairCnt = 0;
        for (int i = 0; i <= 3; i++) {
            if (p[i].getNumber() == p[i + 1].getNumber()) {
                pairCnt++;
                i++;
            }
        }
        if (pairCnt == 2) {
            rank = TexasPokerRank.TWO_PAIR;
            texasScore = PATTERN_MODIFIER * rank.ordinal();
            // set high pair and low pair number
            texasScore += p[3].getNumber().ordinal() << 8 + p[1].getNumber().ordinal() << 4;
            for (Poker i : p) {
                if (i.getNumber() != p[1].getNumber() && i.getNumber() != p[3].getNumber()) {
                    texasScore += i.getNumber().ordinal(); // set the unique number and skip not exist case
                    return true;
                }
            }
        }
        return false;
    }

    private boolean setPair(Poker[] p) {
        int pos;
        for (pos = 0; pos <= 3; pos++) {
            if (p[pos].getNumber() == p[pos + 1].getNumber()) {
                break;
            }
        }
        if (pos == 4) {
            return false;
        }
        rank = TexasPokerRank.PAIR;
        texasScore = p[pos].getNumber().ordinal(); // set pair number
        for (int i = 4; i >= 0; i--) {
            if (i != pos || i != pos + 1) {
                texasScore <<= 4; //left shift a hex
                texasScore += p[i].getNumber().ordinal();
            }
        }
        texasScore += PATTERN_MODIFIER * rank.ordinal(); // add pair tag
        return true;
    }

}
