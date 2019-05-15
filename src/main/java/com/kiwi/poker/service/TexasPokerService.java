package com.kiwi.poker.service;

import com.kiwi.poker.enumerate.TexasPokerRound;
import com.kiwi.poker.model.texaPoker.dto.GetTablesDto;
import org.springframework.web.socket.WebSocketSession;

public interface TexasPokerService {
    String enter(String id, WebSocketSession session);

    GetTablesDto getTables();

    String quit(String id);

    String sitdown(String id, Integer table);

    String standup(String id, Integer table);

    String fold(String id, TexasPokerRound round);

    String raise(String id, Integer chip, TexasPokerRound round);

    String newTable(String id);

}
