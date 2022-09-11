package com.messenger.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.messenger.authentication.dao.AuthenticationDAO;
import com.messenger.orm.TableName;

/**
 * Provide implementation for Authentication
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final AuthenticationDAO AUTHENTICATION_DAO = new AuthenticationDAO();

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
        return AUTHENTICATION_DAO.getParticularDetailsById(tableName, columnList, conditionColumnName);
    }

    /**
     * Retrieving all records
     *
     * @param tableName  represent database table's name
     * @param columnList represent name of a table's column
     * @return information about the particular details
     */
    @Override
    public Collection<Map<String, Object>> getAllDetails(final Enum<TableName> tableName,
                                                         final List<String> columnList) {
        return AUTHENTICATION_DAO.getAllDetails(tableName, columnList);
    }

    /**
     * Insert a new record in dataBase
     *
     * @param tableName     represent a tableName of the database
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    @Override
    public Boolean addNewUser(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        return AUTHENTICATION_DAO.addNewUser(tableName, objectDetails);
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
    public Boolean updateUserName(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                                  final Map<String, Object> conditionColumn) {
        return AUTHENTICATION_DAO.updateUserName(tableName, objectDetails, conditionColumn);
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
         return AUTHENTICATION_DAO.updatePassword(tableName, objectDetails, conditionColumn);
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param conditionColumnName represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    @Override
    public Boolean deleteDetailsById(final Enum<TableName> tableName, final Map<String, Object> conditionColumnName) {
        return AUTHENTICATION_DAO.deleteDetailsById(tableName, conditionColumnName);
    }
}