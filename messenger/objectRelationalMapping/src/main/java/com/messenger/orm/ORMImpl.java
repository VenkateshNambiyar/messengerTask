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
public class ORMImpl {

    /**
     * Insert a new records
     *
     * @param tableName   represent database table's name
     * @param userDetails represent a model object
     * @return message of Success or Failure
     */
    public Boolean insert(final String tableName, final Map<String, Object> userDetails) {
        final StringJoiner column = new StringJoiner(",");
        final StringJoiner fieldValue = new StringJoiner("' , '");
        final Collection<Object> valuesList = userDetails.values();
        final Set<String> columnList = userDetails.keySet();
        final StringBuilder insertQueryBuilder = new StringBuilder();

        for (final String name : columnList) {
            column.add(name);
        }

        for (final Object value : valuesList) {
            fieldValue.add(value.toString());
        }
        insertQueryBuilder.append("insert into ");
        insertQueryBuilder.append(tableName);
        insertQueryBuilder.append("( ");
        insertQueryBuilder.append(column);
        insertQueryBuilder.append(" )");
        insertQueryBuilder.append(" values ");
        insertQueryBuilder.append("( '");
        insertQueryBuilder.append(fieldValue);
        insertQueryBuilder.append(" ');");

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(insertQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UsernameAlreadyExistsException("userName Already Exists");
        }
    }

    /**
     * Obtain a specific record
     *
     * @param userId represent a model object
     * @return information about the specified user's
     */
    public Collection<Map<String, Object>> getParticularUserDetails(final String primaryKey, final String tableName,
                                               final String[] columnName, final long userId) {
        final StringJoiner column = new StringJoiner(",");
        final StringBuilder selectParticularUserQueryBuilder = new StringBuilder();
        final Map<String, Object> userDetails = new HashMap<>();
        final List<Map<String, Object>> result = new ArrayList<>();

        for (final String fields : columnName) {
            column.add(fields);
        }
        selectParticularUserQueryBuilder.append(" Select ");
        selectParticularUserQueryBuilder.append(column);
        selectParticularUserQueryBuilder.append(" from ");
        selectParticularUserQueryBuilder.append(tableName);
        selectParticularUserQueryBuilder.append(" where ");
        selectParticularUserQueryBuilder.append(primaryKey);
        selectParticularUserQueryBuilder.append(" = ");
        selectParticularUserQueryBuilder.append(userId);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            final ResultSet resultSet = statement.executeQuery(String.valueOf(selectParticularUserQueryBuilder));
            final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    userDetails.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
            }
            result.add(userDetails);

            return result;
        } catch (Exception exception) {
            throw new UserNotFoundException("user Not Found");
        }
    }

    /**
     * Show every user record available
     *
     * @return information about the every user
     */
    public Collection<Map<String, Object>> getDetails(final String tableName, final String[] columnName) {
        final StringJoiner column = new StringJoiner(",");
        final List<Map<String, Object>> resultList = new ArrayList<>();
        final StringBuilder selectQueryBuilder = new StringBuilder();

        for (final String fields : columnName) {
            column.add(fields);
        }
        selectQueryBuilder.append(" Select ");
        selectQueryBuilder.append(column);
        selectQueryBuilder.append(" from ");
        selectQueryBuilder.append(tableName);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            final ResultSet resultSet = statement.executeQuery(String.valueOf(selectQueryBuilder));
            final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                final Map<String, Object> userDetails = new HashMap<>();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    userDetails.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                resultList.add(userDetails);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("user Not Found");
        }
        return resultList;
    }

    /**
     * Changes a user's current details
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetails represent a model object
     * @return message of Success or Failure
     */
    public boolean update(final String primaryKey, final String tableName, final String[] columnName,
                          final List<Object> userDetails, final List<Object> primaryKeyValue) {
        final StringJoiner column = new StringJoiner(",");
        final StringJoiner fieldName = new StringJoiner(",");
        final StringJoiner fieldValue = new StringJoiner(" , ");
        final StringBuilder updateQueryBuilder = new StringBuilder();

        for (final String field : columnName) {
            column.add(field);
        }

        for (final Object primaryFieldName : primaryKeyValue) {
            fieldName.add(primaryFieldName.toString());
        }

        for (final Object value : userDetails) {
            fieldValue.add(value.toString());
        }
        updateQueryBuilder.append("update ");
        updateQueryBuilder.append(tableName);
        updateQueryBuilder.append(" set ");
        updateQueryBuilder.append(column);
        updateQueryBuilder.append(" = ");
        updateQueryBuilder.append(" '");
        updateQueryBuilder.append(fieldValue);
        updateQueryBuilder.append("'");
        updateQueryBuilder.append(" where ");
        updateQueryBuilder.append(primaryKey);
        updateQueryBuilder.append(" = ");
        updateQueryBuilder.append(fieldName);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(updateQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("userNameNotFound");
        }
    }

    /**
     * Removes a specific user details
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userId     represent a model object
     * @return message of Success or Failure
     */
    public Boolean delete(final String primaryKey, final String tableName, final long userId) {
        final StringBuilder deleteQueryBuilder = new StringBuilder();

        deleteQueryBuilder.append("Delete from ");
        deleteQueryBuilder.append(tableName);
        deleteQueryBuilder.append(" where ");
        deleteQueryBuilder.append(primaryKey);
        deleteQueryBuilder.append(" = ");
        deleteQueryBuilder.append(userId);

        try (Statement statement = ConnectDataBase.getInstance().getConnection().createStatement()) {
            return statement.executeUpdate(String.valueOf(deleteQueryBuilder)) > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("userNotFound");
        }
    }
}