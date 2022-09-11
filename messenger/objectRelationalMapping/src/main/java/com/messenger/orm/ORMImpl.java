package com.messenger.orm;

import com.messenger.connection.ConnectDataBase;
import com.messenger.exception.UserNotFoundException;
import com.messenger.exception.UsernameAlreadyExistsException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Provides an Object relational Mapping
 */
public class ORMImpl implements ORM {

    /**
     * Insert a new record in dataBase
     *
     * @param tableName    represent a tableName of the database
     * @param objectValues represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean insert(final Enum<TableName> tableName, final Map<String, Object> objectValues) {
        final StringJoiner column = new StringJoiner(",");
        final StringJoiner conditionColumnValues = new StringJoiner("' , '");
        final Collection<Object> valuesList = objectValues.values();
        final Set<String> columnList = objectValues.keySet();
        final StringBuilder insertQueryBuilder = new StringBuilder();

        for (final String name : columnList) {
            column.add(name);
        }

        for (final Object value : valuesList) {
            conditionColumnValues.add(value.toString());
        }
        insertQueryBuilder.append("insert into ").append(tableName).append("(").append(column).append(")")
                .append(" values ").append(" (' ").append(conditionColumnValues).append("');");

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(insertQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UsernameAlreadyExistsException("userName Already Exists");
        }
    }

    /**
     * Specific database record can be retrieved
     *
     * @param tableName       represent database table's name
     * @param columnList      represent name of a table's column
     * @param conditionColumn represent Mapping the column names and object values in a table
     * @return information about the particular details
     */
    public Collection<Map<String, Object>> getParticularDetailsById(final Enum<TableName> tableName,
                                                                    final List<String> columnList,
                                                                    final Map<String, Object> conditionColumn) {
        final StringJoiner column = new StringJoiner(" , ");
        final StringJoiner conditionColumnValues = new StringJoiner(" , ");
        final StringBuilder selectParticularQueryBuilder = new StringBuilder();

        for (final String name : columnList) {
            column.add(name);
        }

        for (final Map.Entry<String, Object> fields : conditionColumn.entrySet()) {
            conditionColumnValues.add(fields.getKey() + " = " + fields.getValue());
        }
        selectParticularQueryBuilder.append(" Select ").append(column).append(" from ").append(tableName)
                .append(" where ").append(conditionColumnValues).append(";");

        return retrieveDetails(selectParticularQueryBuilder.toString());
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
        final StringJoiner column = new StringJoiner(",");
        final StringBuilder selectQueryBuilder = new StringBuilder();

        for (final String fields : columnList) {
            column.add(fields);
        }
        selectQueryBuilder.append(" Select ").append(column).append(" from ").append(tableName);

        return retrieveDetails(selectQueryBuilder.toString());
    }

    /**
     * Get the list of records from the table
     *
     * @param selectQuery represent a select query
     * @return list of record
     */
    private Collection<Map<String, Object>> retrieveDetails(final String selectQuery) {
        final Map<String, Object> userDetails = new HashMap<>();
        final List<Map<String, Object>> result = new ArrayList<>();

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            final ResultSet resultSet = statement.executeQuery(String.valueOf(selectQuery));
            final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {

                for (int columnId = 1; columnId <= resultSetMetaData.getColumnCount(); columnId++) {
                    userDetails.put(resultSetMetaData.getColumnName(columnId), resultSet.getObject(columnId));
                }
                result.add(userDetails);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("user Not Found");
        }
        return result;
    }

    /**
     * Update database information
     *
     * @param tableName       represent database table's name
     * @param objectDetails   represent Mapping the column names and object values in a table
     * @param conditionColumn represent Mapping the condition column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean update(final Enum<TableName> tableName, final Map<String, Object> objectDetails,
                          final Map<String, Object> conditionColumn) {
        final StringJoiner column = new StringJoiner(",");
        final StringJoiner columnNames = new StringJoiner(" , ");
        final StringJoiner fieldValues = new StringJoiner(" , ");
        final StringBuilder updateQueryBuilder = new StringBuilder();
        final Collection<Object> valuesList = objectDetails.values();
        final Set<String> columnList = objectDetails.keySet();

        for (final String name : columnList) {
            column.add(name);
        }

        for (final Object value : valuesList) {
            fieldValues.add(value.toString());
        }

        for (final Map.Entry<String, Object> fields : conditionColumn.entrySet()) {
            columnNames.add(fields.getKey() + " = " + fields.getValue());
        }
        updateQueryBuilder.append("update ").append(tableName).append(" set ").append(column).append(" = ").append("'")
                .append(fieldValues).append("'").append(" where ").append(conditionColumn);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(updateQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("userNameNotFound");
        }
    }

    /**
     * Removes a specific details
     *
     * @param tableName     represent database table's name
     * @param objectDetails represent Mapping the column names and object values in a table
     * @return message of Success or Failure
     */
    public Boolean delete(final Enum<TableName> tableName, final Map<String, Object> objectDetails) {
        final StringBuilder deleteQueryBuilder = new StringBuilder();

        deleteQueryBuilder.append("Delete from ").append(tableName).append(" where ").append(objectDetails);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(deleteQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("userNotFound");
        }
    }
}