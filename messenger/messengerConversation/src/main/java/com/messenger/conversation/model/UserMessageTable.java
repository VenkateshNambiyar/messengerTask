package com.messenger.conversation.model;

/**
 * Contains a collection of constants that are fixed for the user message table
 */
public enum UserMessageTable {

    primaryKey("org_id"),
    tableName("message"),
    columnField(new String[]{"mobile_time", "message_details"});

    public String name;
    public String[] field;

    UserMessageTable(final String name) {
        this.name = name;
    }

    UserMessageTable(final String[] field) {
        this.field = field;
    }
}
