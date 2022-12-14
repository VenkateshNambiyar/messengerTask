package com.messenger.conversation.model;

import com.messenger.conversation.validation.service.ValidateContactId;
import com.messenger.conversation.validation.service.ValidateMobileNumber;
import com.messenger.conversation.validation.service.ValidatePersonName;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;

/**
 * Provide an information about authentication details.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class Conversation {

    @Positive(message = "contactId should not be empty or null", groups = ValidateContactId.class)
    @Min(value = 1, message = "contactId should have at least 1 character", groups = ValidateContactId.class)
    private long contactId;

    @Positive(message = "mobileNumber must contain positive number only", groups = ValidateMobileNumber.class)
    private long mobileNumber;

    @NotEmpty(message = "Username should not be empty or null", groups = ValidatePersonName.class)
    @Size(min = 3, message = "username should have at least contain 3 Character", groups = ValidatePersonName.class)
    @Pattern(regexp = "(^[a-zA-Z_]{3,40}$)", message = "username Contain only alphabets and underscore",
            groups = ValidatePersonName.class)
    private String personName;
    private long profileId;
    private Timestamp messageTime;
    private String messageContent;
    private String profileName;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}