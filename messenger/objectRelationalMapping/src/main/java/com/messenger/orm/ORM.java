package com.messenger.orm;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides the ORM services
 */
public interface ORM {

    /**
     * Insert a new record in dataBase
     *
     * @param tableName    represent a tableName of the database
     * @param objectValues represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean insert(final Enum<TableName> tableName, final Map<String, Object> objectValues);

    /**
     * Specific database record can be retrieved
     *
     * @param tableName       represent database table's name
     * @param columnList      represent name of a table's column
     * @param conditionColumn represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getParticularDetailsById(final Enum<TableName> tableName,
                                                             final List<String> columnList,
                                                             final Map<String, Object> conditionColumn);

    /**
     * Retrieving all records
     *
     * @param tableName  represent database table's name
     * @param columnList represent name of a table's column
     * @return information about the particular details
     */
    Collection<Map<String, Object>> getAllDetails(final Enum<TableName> tableName, final List<String> columnList);

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    Boolean update(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                   final Map<String, Object> conditionColumn);

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of success or failure
     */
    Boolean delete(final Enum<TableName> tableName, final Map<String, Object> objectDetails);
}