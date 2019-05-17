package com.kiwi.poker.texas;

import com.kiwi.poker.domain.Poker;
import com.kiwi.poker.enumerate.TexasPlayerStatus;

public class Player {
    private String id;
    private TexasPlayerStatus status;
    private Integer chips;
    private Integer chipsInPot;
    private Poker poker1;
    private Poker poker2;

    public Player(String id, Integer chips) {
        this.id = id;
        this.chips = chips;
        this.status = TexasPlayerStatus.ON_DECK;
    }

    public TexasPlayerStatus getStatus() {
        return status;
    }

    public void setStatus(TexasPlayerStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Integer getChips() {
        return chips;
    }

    public void pollChips(Integer chips){
        this.chips -= chips;
        this.chipsInPot += chips;
    }

    public Poker getPoker1() {
        return poker1;
    }

    public Poker getPoker2() {
        return poker2;
    }

    public void setPoker(Poker p1, Poker p2){
        this.poker1 = p1;
        this.poker2 = p2;
    }
}
