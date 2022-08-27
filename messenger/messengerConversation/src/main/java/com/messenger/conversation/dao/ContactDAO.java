package com.messenger.conversation.dao;

import com.messenger.connectDatabase.ConnectDataBase;
import com.messenger.conversation.model.Conversation;
import com.messenger.exception.UserNotFoundException;
import com.messenger.exception.UsernameAlreadyExistsException;

import java.sql.*;
import java.util.*;

/**
 * Provide database connection to perform create, read, update, delete operation
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactDAO {

    private static final Connection CONNECTION = ConnectDataBase.getInstance().getConnection();

    /**
     * Creates a new contact
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    public boolean addContact(final Conversation conversation) {
        final String insertSql = "insert into contact (mobile_number, person_name) values(?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertSql)) {
            preparedStatement.setLong(1, conversation.getMobileNumber());
            preparedStatement.setString(2, conversation.getPersonName());
            preparedStatement.executeUpdate();

            return createContact(conversation);
        } catch (Exception exception) {
            throw new UsernameAlreadyExistsException("username already Exists");
        }
    }

    /**
     * Retrieve a user contact details
     *
     * @param conversation object of the Conversion model
     * @return retrieveContactId of the user
     */
    public long retrieveContactId(final Conversation conversation) {
        long retrieveContactId = 0;
        final String selectSql = "select org_id from contact where mobile_number = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectSql)) {
            preparedStatement.setLong(1, conversation.getMobileNumber());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                retrieveContactId = resultSet.getLong("org_id");
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
        return retrieveContactId;
    }

    /**
     * Retrieve a user profile details
     *
     * @param conversation object of the Conversion model
     * @return retrieveProfileId of the user
     */
    public long retrieveUserId(final Conversation conversation) {
        long retrieveUserId = 0;
        final String selectSql = " Select org_id from user_login where user_name = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectSql)) {
            preparedStatement.setObject(1, conversation.getProfileName());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                retrieveUserId = resultSet.getLong("org_id");
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
        return retrieveUserId;
    }

    /**
     * Create a user contact profile
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    private boolean createContact(final Conversation conversation) {
        final String insertSql = "insert into user_contact(user_id, contact_id) values (?,?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertSql)) {
            preparedStatement.setLong(1, retrieveUserId(conversation));
            preparedStatement.setLong(2, retrieveContactId(conversation));

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Find a particular user contact record
     *
     * @param username name of the user
     * @return Contact of the Model
     */
    public List<Object> getUserContact(final String username) {
        final List<Object> userContact = new ArrayList<>();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select contact.mobile_number, contact.person_name, contact.org_id");
        stringBuilder.append(" from contact inner join user_contact on");
        stringBuilder.append(" user_contact.contact_id = contact.org_id inner join user_login on");
        stringBuilder.append(" user_login.org_id = user_contact.user_id where user_login.user_name = ? ");

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(String.valueOf(stringBuilder))) {
            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Conversation contact = new Conversation();

                contact.setContactId(resultSet.getLong("org_id"));
                contact.setMobileNumber(resultSet.getLong("mobile_number"));
                contact.setPersonName(resultSet.getString("person_name"));
                final Map<String, Object> getContactDetails = new HashMap<>();

                getContactDetails.put("contactId", contact.getContactId());
                getContactDetails.put("mobileNumber", contact.getMobileNumber());
                getContactDetails.put("personName", contact.getPersonName());
                userContact.add(getContactDetails);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
        return userContact;
    }

    /**
     * Updates an existing mobileNumber for a user.
     *
     * @param conversation object of the Conversion model
     * @return Success or failure message
     */
    public boolean updateMobileNumber(final Conversation conversation) {
        final String updateSql = "update contact SET mobile_number = ? where org_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(updateSql)) {
            preparedStatement.setLong(1, conversation.getMobileNumber());
            preparedStatement.setLong(2, conversation.getContactId());

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * Deletes a particular user contact record.
     *
     * @param contactId contactId of the messenger user.
     * @return Success or failure message
     */
    public boolean deleteContact(final long contactId) {
        final String deleteSql = "delete from contact where org_id = ?";
        final String deleteUserContact = "delete from user_contact where contact_id = ?";

        try (PreparedStatement deleteContact = CONNECTION.prepareStatement(deleteUserContact);
             PreparedStatement preparedStatement = CONNECTION.prepareStatement(deleteSql)) {
            deleteContact.setLong(1, contactId);
            preparedStatement.setLong(1, contactId);
            deleteContact.execute();

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }
}