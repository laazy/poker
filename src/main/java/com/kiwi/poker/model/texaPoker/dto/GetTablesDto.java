package com.kiwi.poker.model.texaPoker.dto;

import com.kiwi.poker.texas.TexasTable;

import java.util.ArrayList;
import java.util.List;

public class GetTablesDto {
    public class TableInfo{
        public TableInfo(int status, int tableId, int people, int max) {
            this.status = status;
            this.tableId = tableId;
            this.people = people;
            this.max = max;
        }

        int status;
        int tableId;
        int people;
        int max;
    }
    private List<TableInfo> tableInfos;

    public GetTablesDto(List<TexasTable> tables){
        tableInfos = new ArrayList<>();
        for (int i = 0; i < tables.size(); i++){
            tableInfos.add(new TableInfo(tables.get(i).getTableStatus().ordinal(), i, tables.get(i).getPlaysNumer(),
                    tables.get(i).getMaxPlayers()));
        }
    }

    public List<TableInfo> getTableInfos() {
        return tableInfos;
    }

    public void setTableInfos(List<TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }
}
