package com.messenger.authentication.model;

import com.messenger.validation.UpdateUserName;
import com.messenger.validation.UpdateUserPassword;
import com.messenger.validation.AddNewUser;

import com.messenger.validation.UserDetails;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Define the object entities and include annotations that are predetermined
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class UserDetail {

    @Positive(groups = {UserDetails.class, UpdateUserName.class, UpdateUserPassword.class})
    @Min(value = 1, message = "Userid should have at least 1 character", groups = {UserDetails.class,
            UpdateUserName.class, UpdateUserPassword.class})
    private long userId;

    @NotEmpty(message = "userName must not be empty", groups = { AddNewUser.class, UpdateUserName.class})
    @Size(min = 3, message = "username should have at least contain 3 Character", groups = { AddNewUser.class,
            UpdateUserName.class })
    @Pattern(regexp = "(^[a-zA-Z_]{3,40}$)", message = "username Contain only alphabets and underscore",
            groups = { AddNewUser.class, UpdateUserName.class })
    private String userName;

    @NotEmpty(message = "password must not be empty" ,groups = {AddNewUser.class, UpdateUserPassword.class})
    @Size(min = 3, message = "password should have at least contain 6 Character", groups = {AddNewUser.class,
            UpdateUserPassword.class})
    @Pattern(regexp = "(^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$)",
            message = "Password should have at least one capital Letter, One Small Letter, one Special Character",
            groups = {AddNewUser.class, UpdateUserPassword.class})
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}