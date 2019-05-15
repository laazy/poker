package com.kiwi.poker.model.texaPoker.dto;

import java.util.List;

public class GetTablesDto {
    class TableInfo{
        int status;
        int tableId;
        int people;
    }
    List<TableInfo> tableInfos;

    public GetTablesDto(List<TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }
}
