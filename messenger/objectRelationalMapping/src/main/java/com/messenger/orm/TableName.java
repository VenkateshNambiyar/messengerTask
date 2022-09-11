package com.messenger.orm;

/**
 * Contains a number of the fixed constants for the database table name
 */
public enum TableName {
    user_login,
    contact,
    message;

    private String tableName;

    TableName() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}