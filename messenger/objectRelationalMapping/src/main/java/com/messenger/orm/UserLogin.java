package com.messenger.orm;

/**
 * Contains a number of the fixed constants for the UserLogin table column name
 */
public enum UserLogin {
    userId("id"),
    userName("user_name"),
    password("password");

    private String columnName;

    UserLogin(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}