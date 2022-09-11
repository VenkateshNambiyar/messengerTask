package com.messenger.conversation.service.conversationServiceImpl;

import com.messenger.conversation.dao.ContactDAO;
import com.messenger.conversation.service.conversationService.ContactService;
import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * provide implementation for Contact
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactServiceImpl implements ContactService {

    private static final ContactDAO CONTACT_DAO = new ContactDAO();

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    @Override
    public Collection<Map<String, Object>> getUserContact(final Enum<TableName> tableName,
                                                          final List<String> columnList,
                                                          final Map<String, Object> conditionColumnName) {
        return CONTACT_DAO.getUserContact(tableName, columnList, conditionColumnName);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    @Override
    public Boolean addNewContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return CONTACT_DAO.addNewContact(tableName, objectDetails);
    }

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    @Override
    public Boolean updateMobileNumber(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                                      final Map<String, Object> conditionColumn) {
        return CONTACT_DAO.updateMobileNumber(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    @Override
    public Boolean deleteUserContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return CONTACT_DAO.deleteUserContact(tableName, objectDetails);
    }
}