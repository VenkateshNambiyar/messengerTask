package com.messenger.validation;

import java.util.Set;

import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import org.hibernate.validator.HibernateValidator;

/**
 * Validate a user information
 */
public class UserDetailValidation {
    public static Validator validator;
    public static String validationMessage;

    /**
     * Checks the userDetails
     *
     * @param userDetail represent a UserDetail model object
     * @return validated input to the Authentication view
     */
    public static <T> String validateUserDetail(final Object userDetail, final Class<T> validation) {
        try (final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(userDetail, validation);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Object> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "valid";
            }
        }
        return validationMessage;
    }
}