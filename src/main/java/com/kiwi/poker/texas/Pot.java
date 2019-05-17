package com.kiwi.poker.texas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pot {
    private Integer total;
    private Map<Player, Integer> playerHold = new HashMap<>();

    public void bet(Player player, Integer bet) {
        total += bet;
        if (playerHold.containsKey(player)){
            playerHold.put(player, playerHold.get(player)+bet);
        }else {
            playerHold.put(player, bet);
        }
    }

    public Map<Player, Integer> win(List<Player> winners){
        Map<Player, Integer> ans = new HashMap<>();
        ans.put(winners.get(0), total);
        return ans;
    }
}
