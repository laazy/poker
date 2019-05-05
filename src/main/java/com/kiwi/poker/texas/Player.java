package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;

public class Player {
    private String id;
    private Integer chips;
    private Poker poker1;
    private Poker poker2;

    public Player(String id, Integer chips, Poker poker1, Poker poker2) {
        this.id = id;
        this.chips = chips;
        this.poker1 = poker1;
        this.poker2 = poker2;
    }

    public String getId() {
        return id;
    }

    public Integer getChips() {
        return chips;
    }

    public Poker getPoker1() {
        return poker1;
    }

    public Poker getPoker2() {
        return poker2;
    }

    public boolean raise(Integer i){
        if (chips < i){
            return false;
        }
        chips -= i;
        return true;
    }
}
