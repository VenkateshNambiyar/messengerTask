package com.messenger.authentication.model;

import com.messenger.authentication.validation.service.ValidatePassword;
import com.messenger.authentication.validation.service.ValidateUserId;
import com.messenger.authentication.validation.service.ValidateUsername;
import jakarta.validation.constraints.*;

/**
 * provide an information about authentication details.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class Authentication {

    @Positive(message = "userId should not be empty or null", groups = ValidateUserId.class)
    @Min(value = 1, message = "Userid should have at least 1 character", groups = ValidateUserId.class)
    private long userId;

    @NotEmpty(message = "Username should not be empty or null", groups = ValidateUsername.class)
    @Size(min = 3, message = "username should have at least contain 3 Character", groups = ValidateUsername.class)
    @Pattern(regexp = "(^[a-zA-Z_]{3,40}$)", message = "username Contain only alphabets and underscore",
            groups = ValidateUsername.class)
    private String username;

    @NotEmpty(message = "password should not be empty or null", groups = ValidatePassword.class)
    @Size(min = 6, message = "password should have at least 6 character")
    @Pattern(regexp = "(^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$)",
            message = "Password should have at least one capital Letter, One Small Letter, one Special Character",
            groups = ValidatePassword.class)
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}