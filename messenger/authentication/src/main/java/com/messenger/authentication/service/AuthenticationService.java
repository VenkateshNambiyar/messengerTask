package com.messenger.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.messenger.orm.TableName;

/**
 * Provides the authentication services that Messenger has available
 *
 * @author Venkatesh N
 * @version 1.0
 */
public interface AuthenticationService {

    /**
     * Specific database record can be retrieved
     *
     * @param tableName           represent database table's name
     * @param columnList          represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getParticularDetailsById(final Enum<TableName> tableName,
                                                             final List<String> columnList,
                                                             final Map<String, Object> conditionColumnName);

    /**
     * Retrieving all records
     *
     * @param tableName  represent database table's name
     * @param columnList represent name of a table's column
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getAllDetails(final Enum<TableName> tableName, final List<String> columnList);

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean addNewUser(final Enum<TableName> tableName, final Map<String, Object> objectDetails);

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean updateUserName(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                           final Map<String, Object> conditionColumn);

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean updatePassword(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                           final Map<String, Object> conditionColumn);

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean deleteDetailsById(final Enum<TableName> tableName, final Map<String, Object> conditionColumnName);
}