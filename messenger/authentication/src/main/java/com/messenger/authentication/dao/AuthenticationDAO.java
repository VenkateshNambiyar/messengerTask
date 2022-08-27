package com.messenger.authentication.dao;

import com.messenger.authentication.model.Authentication;
import com.messenger.connectDatabase.ConnectDataBase;
import com.messenger.exception.UserNotFoundException;
import com.messenger.exception.UsernameAlreadyExistsException;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Provide database connection to perform create, read, update, delete operation
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class AuthenticationDAO {

    private static final Connection CONNECTION = ConnectDataBase.getInstance().getConnection();

    /**
     * Find a particular user record.
     *
     * @param username from Authentication Model.
     * @return username, userId of a particular user
     */
    public JSONObject getUserDetailsByUsername(final String username) {
        final Map<String, Object> userDetails = new HashMap<>();
        final String selectSql = " Select org_id, user_name from user_login where user_name = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectSql)) {
            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userDetails.put("userId", resultSet.getLong("org_id"));
                userDetails.put("username", resultSet.getString("user_name"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return new JSONObject(userDetails);
    }

    /**
     * Display the all user records.
     *
     * @return userId, username of all user in messenger
     */
    public JSONObject getAllDetails() {
        final Map<String, List<Object>> allUserDetails = new Hashtable<>();
        final List<Object> userProfile = new ArrayList<>();
        final String selectSql = " Select org_id, user_name from user_login ";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectSql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Authentication authentication = new Authentication();

                authentication.setUserId(resultSet.getLong("org_id"));
                authentication.setUsername(resultSet.getString("user_name"));
                final Map<String, Object> getDetails = new HashMap<>();

                getDetails.put("userid", authentication.getUserId());
                getDetails.put("username", authentication.getUsername());
                userProfile.add(getDetails);
                allUserDetails.put("UserProfile", userProfile);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return new JSONObject(allUserDetails);
    }

    /**
     * Creates a new user.
     *
     * @param authentication object of the Authentication model
     * @return Success or failure message
     */
    public boolean addNewUser(final Authentication authentication) {
        final String insertSql = "insert into user_login (user_name, password) values(?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertSql)) {
            preparedStatement.setString(1, authentication.getUsername());
            preparedStatement.setString(2, authentication.getPassword());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception sqlException) {
            throw new UsernameAlreadyExistsException("username already Exists");
        }
    }

    /**
     * Updates an existing password for user.
     *
     * @param authentication object of the Authentication Model.
     * @return Success or failure message
     */
    public boolean updatePassword(final Authentication authentication) {
        final String updateSql = "update user_login SET password = ? where user_name = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(updateSql)) {
            preparedStatement.setString(1, authentication.getPassword());
            preparedStatement.setString(2, authentication.getUsername());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Updates an existing username for user.
     *
     * @param authentication object of the Authentication Model.
     * @return Success or failure message
     */
    public boolean updateUsername(final Authentication authentication) {
        final String updateSql = "update user_login SET user_name = ? where org_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(updateSql)) {
            preparedStatement.setString(1, authentication.getUsername());
            preparedStatement.setLong(2, authentication.getUserId());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Deletes a particular userprofile.
     *
     * @param userid from the Authentication Model.
     * @return Success or failure message
     */
    public boolean deleteUserProfile(final long userid) {
        final String deleteSql = "delete from user_login where org_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(deleteSql)) {
            preparedStatement.setLong(1, userid);

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }
}