package com.messenger.conversation.model;

/**
 * Contains a collection of constants that are fixed for the user contact table
 */
public enum UserContactTable {

    primaryKey("org_id"),
    tableName("user_login"),
    columnField(new String[]{"user_name", "password"});

    public String name;
    public String[] field;

    UserContactTable(final String name) {
        this.name = name;
    }

    UserContactTable(final String[] field) {
        this.field = field;
    }
}