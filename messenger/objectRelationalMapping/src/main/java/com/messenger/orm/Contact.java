package com.messenger.orm;

/**
 * Contains a number of the fixed constants for the Contact table column name
 */
public enum Contact {
    contactId("id"),
    mobileNumber("mobile_number"),
    personName("person_name");

    private String columnName;

    Contact(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}