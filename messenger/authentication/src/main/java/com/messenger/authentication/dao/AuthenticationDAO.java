package com.messenger.authentication.dao;

import com.messenger.orm.ORMImpl;
import com.messenger.orm.TableName;

import java.util.Collection;
import java.util.Map;
import java.util.List;

/**
 * Create, read, update, and delete operations should be performed via a database connection
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationDAO {
    private final static ORMImpl ORM_IMPL = new ORMImpl();

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getParticularDetailsById(final Enum<TableName> tableName,
                                                                    final List<String> columnList,
                                                                    final Map<String, Object> conditionColumnName) {
        return ORM_IMPL.getParticularDetailsById(tableName, columnList, conditionColumnName);
    }

    /**
     * Retrieving all records
     *
     * @param tableName  represent database table's name
     * @param columnList represent name of a table's column
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getAllDetails(final Enum<TableName> tableName,
                                                         final List<String> columnList) {
        return ORM_IMPL.getAllDetails(tableName, columnList);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addNewUser(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
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
    public Boolean updatePassword(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                                  final Map<String, Object> conditionColumn) {
        return ORM_IMPL.update(tableName, objectDetails, conditionColumn);
    }

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean updateUserName(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                                  final Map<String, Object> conditionColumn) {
        return ORM_IMPL.update(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean deleteDetailsById(final Enum<TableName> tableName, final Map<String, Object> conditionColumnName) {
        return ORM_IMPL.delete(tableName, conditionColumnName);
    }
}