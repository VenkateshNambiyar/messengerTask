package com.messenger.authentication.model;

/**
 * Contains a collection of constants that are fixed for the user information table
 */
public enum UserInformationTable {

    primaryKey("org_id"),
    tableName("user_login"),
    columnField(new String[]{"user_name", "password"});

    public String name;
    public String[] field;

    UserInformationTable(final String name) {
        this.name = name;
    }

    UserInformationTable(final String[] field) {
        this.field = field;
    }
}