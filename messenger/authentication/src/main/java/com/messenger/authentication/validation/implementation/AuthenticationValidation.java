package com.messenger.authentication.validation.implementation;

import com.messenger.authentication.model.Authentication;
import com.messenger.authentication.validation.service.ValidatePassword;
import com.messenger.authentication.validation.service.ValidateUserId;
import com.messenger.authentication.validation.service.ValidateUsername;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;

/**
 * provides a validation for Authentication
 */
public class AuthenticationValidation {

    private static Validator validator;
    private static String validationMessage;

    /**
     * Checks the username as accepting only alphabets and underscore
     *
     * @param username of the user
     * @return validated input to the Authentication view
     */
    public static String checkUsername(final String username) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Authentication authentication = new Authentication();

            authentication.setUsername(username);
            final Set<ConstraintViolation<Authentication>> constraintViolations =
                    validator.validate(authentication, ValidateUsername.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Authentication> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "Username is valid";
            }
        }
        return validationMessage;
    }

    /**
     * Checks the password as accepting only minimum one captain letter, one small letter and one special character
     *
     * @param password of the user
     * @return validated input to the Authentication view
     */
    public static String checkPassword(final String password) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Authentication authentication = new Authentication();

            authentication.setPassword(password);
            final Set<ConstraintViolation<Authentication>> constraintViolations =
                    validator.validate(authentication, ValidatePassword.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Authentication> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "Password is valid";
            }
        }
        return validationMessage;
    }

    /**
     * Checks the userId as accepting only positive number
     *
     * @param userId of the user
     * @return validated input to the Authentication view
     */
    public static String checkUserId(final long userId) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Authentication authentication = new Authentication();

            authentication.setUserId(userId);
            final Set<ConstraintViolation<Authentication>> constraintViolations =
                    validator.validate(authentication, ValidateUserId.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Authentication> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "UserId is valid";
            }
        }
        return validationMessage;
    }
}