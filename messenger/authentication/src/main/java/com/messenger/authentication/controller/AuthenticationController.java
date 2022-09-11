package com.messenger.authentication.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.messenger.authentication.service.AuthenticationServiceImpl;
import com.messenger.orm.TableName;

/**
 * Controls the data flow into the model object and updates the view whenever data changes
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationController {

    private static final AuthenticationServiceImpl AUTHENTICATION_SERVICE = new AuthenticationServiceImpl();

    /**
     * Specific database record can be retrieved
     *
     * @param tableName       represent database table's name
     * @param columnList      represent name of a table's column
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getParticularDetailsById(final Enum<TableName> tableName,
                                                                    final List<String> columnList,
                                                                    final Map<String, Object> conditionColumnName) {
        return AUTHENTICATION_SERVICE.getParticularDetailsById(tableName, columnList, conditionColumnName);
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
        return AUTHENTICATION_SERVICE.getAllDetails(tableName, columnList);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName    represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean addNewUser(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return AUTHENTICATION_SERVICE.addNewUser(tableName, objectDetails);
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
        return AUTHENTICATION_SERVICE.updatePassword(tableName, objectDetails, conditionColumn);
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
        return AUTHENTICATION_SERVICE.updateUserName(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName  represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean deleteDetailsById(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return AUTHENTICATION_SERVICE.deleteDetailsById(tableName, objectDetails);
    }
}