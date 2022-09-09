package com.messenger.authentication.dao;

import com.messenger.authentication.model.UserDetail;
import com.messenger.authentication.model.UserInformationTable;
import com.messenger.orm.ORMImpl;

import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Create, read, update, and delete operations should be performed via a database connection
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationDAO {
   private final static ORMImpl ORM_IMPL = new ORMImpl();

    /**
     * Obtain a specific user record
     *
     * @param userId represent a UserDetail model object
     * @return information about the specified user's userName and userId
     */
    public Collection<Map<String, Object>> getUserDetailsByUserName(final long userId) {
        final String[] fieldName = new String[2];

        for (final String column : UserInformationTable.columnField.field) {

            if (column.equals("user_name")) {
                fieldName[0] = column;
                fieldName[1] = UserInformationTable.primaryKey.name;
            }
        }
        return ORM_IMPL.getParticularUserDetails(UserInformationTable.primaryKey.name,
                UserInformationTable.tableName.name, fieldName, userId);
    }

    /**
     * Show every user record available
     *
     * @return information about the userId and userName of every user
     */
    public Collection<Map<String, Object>> getAllDetails() {
        final String[] fieldName = new String[2];

        for (final String column : UserInformationTable.columnField.field) {

            if (column.equals("user_name")) {
                fieldName[0] = column;
                fieldName[1] = UserInformationTable.primaryKey.name;
            }
        }
        return ORM_IMPL.getDetails(UserInformationTable.tableName.name, fieldName);
    }

    /**
     * Insert a new user
     *
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean addNewUser(final String tableName, final Map<String, Object> userDetail) {
        return ORM_IMPL.insert(tableName, userDetail);
    }

    /**
     * Changes a user's current password
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean updatePassword(final String primaryKey, final String tableName, final UserDetail userDetail) {
        final List<Object> fieldValues = new ArrayList<>();
        final List<Object> primaryKeyValue = new ArrayList<>();
        final String[] columnList = new String[2];

        for (final String column : UserInformationTable.columnField.field) {
            if (column.equals("user_name")) {
                columnList[0] = column;
            }
        }
        fieldValues.add(userDetail.getUserName());
        primaryKeyValue.add(userDetail.getUserId());

        return ORM_IMPL.update(primaryKey, tableName, columnList, fieldValues, primaryKeyValue);
    }

    /**
     * Changes a user's current userName
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userDetail represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean updateUsername(final String primaryKey, final String tableName, final UserDetail userDetail) {
        final List<Object> fieldValues = new ArrayList<>();
        final List<Object> primaryKeyValue = new ArrayList<>();
        final String[] columnList = new String[1];

        for (final String column : UserInformationTable.columnField.field) {
            if (column.equals("user_name")) {
                columnList[0] = column;
            }
        }
        fieldValues.add(userDetail.getUserName());
        primaryKeyValue.add(userDetail.getUserId());

        return ORM_IMPL.update(primaryKey, tableName, columnList, fieldValues, primaryKeyValue);
    }

    /**
     * Removes a specific user profile
     *
     * @param primaryKey represent name of a table's column
     * @param tableName  represent database table's name
     * @param userId     represent a UserDetail model object
     * @return message of Success or Failure
     */
    public Boolean deleteUserProfile(final String primaryKey, final String tableName, final long userId) {
        return ORM_IMPL.delete(primaryKey, tableName, userId);
    }
}