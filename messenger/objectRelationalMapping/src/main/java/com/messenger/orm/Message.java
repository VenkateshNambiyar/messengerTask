package com.messenger.orm;

/**
 * Contains a number of the fixed constants for the Message table column name
 */
public enum Message {
    messageId("id"),
    messageTime("message_time"),
    messageDetails("message_details");

    private String columnName;

    Message(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}