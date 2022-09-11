package com.messenger.conversation.dao;

import com.messenger.orm.ORMImpl;
import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Create, read, update, and delete operations should be performed via a database connection
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactDAO {

    private static final ORMImpl ORM_IMPL = new ORMImpl();

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
        return ORM_IMPL.getParticularDetailsById(tableName, columnList, conditionColumnName);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addNewContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return ORM_IMPL.insert(tableName, objectDetails);
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
        return ORM_IMPL.update(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean deleteUserContact(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return ORM_IMPL.delete(tableName, objectDetails);
    }
}