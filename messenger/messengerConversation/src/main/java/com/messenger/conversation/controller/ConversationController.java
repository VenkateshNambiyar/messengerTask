package com.messenger.conversation.controller;

import com.messenger.conversation.service.conversationServiceImpl.ContactServiceImpl;
import com.messenger.conversation.service.conversationServiceImpl.MessageServiceImpl;
import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Controls the data flow into the model object and updates the view whenever data changes
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConversationController {

    private static final ContactServiceImpl CONTACT_SERVICE = new ContactServiceImpl();
    private static final MessageServiceImpl MESSAGE_SERVICE = new MessageServiceImpl();

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getUserContact(final Enum<TableName> tableName,
                                                          final List<String> columnList,
                                                          final Map<String, Object> conditionColumnName) {
        return CONTACT_SERVICE.getUserContact(tableName, columnList, conditionColumnName);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addNewContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return CONTACT_SERVICE.addNewContact(tableName, objectDetails);
    }

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean updateMobileNumber(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                                      final Map<String, Object> conditionColumn) {
        return CONTACT_SERVICE.updateMobileNumber(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean deleteUserContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return CONTACT_SERVICE.deleteUserContact(tableName, objectDetails);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addMessage(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return MESSAGE_SERVICE.addMessage(tableName, objectDetails);
    }

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getMessage(final Enum<TableName> tableName,
                                                          final List<String> columnList,
                                                          final Map<String, Object> conditionColumnName) {
        return MESSAGE_SERVICE.getMessage(tableName, columnList, conditionColumnName);
    }
}