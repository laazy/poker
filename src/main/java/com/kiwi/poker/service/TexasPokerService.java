package com.kiwi.poker.service;

import com.kiwi.poker.enumerate.TexasPokerRound;
import org.springframework.web.socket.WebSocketSession;

public interface TexasPokerService {
    String enter(String id, WebSocketSession session);

    String quit(String id);

    String sitdown(String id, String gameId);

    String standup(String id, String gameId);

    String fold(String id, TexasPokerRound round);

    String raise(String id, Integer chip, TexasPokerRound round);

}
