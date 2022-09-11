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

    /**
     * Checks the details
     *
     * @param objectDetail represent a model object
     * @return validated input to the Authentication view
     */
    public static <T> String validateDetails(final Object objectDetail, final Class<T> validation) {
        String validationMessage = null;

        try (final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().buildValidatorFactory()) {
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(objectDetail, validation);

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