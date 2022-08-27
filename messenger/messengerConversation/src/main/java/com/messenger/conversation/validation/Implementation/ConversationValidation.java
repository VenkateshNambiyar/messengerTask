package com.messenger.conversation.validation.Implementation;

import com.messenger.conversation.model.Conversation;
import com.messenger.conversation.validation.service.ValidateContactId;
import com.messenger.conversation.validation.service.ValidateMobileNumber;
import com.messenger.conversation.validation.service.ValidatePersonName;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;

public class ConversationValidation {

    private static Validator validator;
    private static String validationMessage;

    /**
     * Checks the name as accepting only alphabets and underscore
     *
     * @param name of the user
     * @return validated input to the Authentication view
     */
    public static String checkName(final String name) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Conversation conversation = new Conversation();

            conversation.setPersonName(name);
            final Set<ConstraintViolation<Conversation>> constraintViolations =
                    validator.validate(conversation, ValidatePersonName.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Conversation> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "Name is valid";
            }
        }
        return validationMessage;
    }

    /**
     * Checks the mobileNumber as accepting only number
     *
     * @param mobileNumber of the user
     * @return validated input to the Authentication view
     */
    public static String checkMobileNumber(final long mobileNumber) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Conversation conversation = new Conversation();

            conversation.setMobileNumber(mobileNumber);
            final Set<ConstraintViolation<Conversation>> constraintViolations =
                    validator.validate(conversation, ValidateMobileNumber.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Conversation> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "MobileNumber is valid";
            }
        }
        return validationMessage;
    }

    /**
     * Checks the id as accepting only positive number
     *
     * @param id of the user
     * @return validated input to the Authentication view
     */
    public static String checkId(final long id) {
        try (ValidatorFactory validatorFactory =
                     Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
            final Conversation conversation = new Conversation();

            conversation.setContactId(id);
            final Set<ConstraintViolation<Conversation>> constraintViolations =
                    validator.validate(conversation, ValidateContactId.class);

            if (constraintViolations.size() > 0) {

                for (ConstraintViolation<Conversation> violation : constraintViolations) {
                    validationMessage = violation.getMessage();
                }
            } else {
                validationMessage = "Id is valid";
            }
        }
        return validationMessage;
    }
}