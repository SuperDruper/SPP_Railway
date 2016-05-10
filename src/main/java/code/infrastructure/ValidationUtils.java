package code.infrastructure;

import code.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by PC-Alyaksei on 24.04.2016.
 */
public class ValidationUtils {
    private static ValidatorFactory ourInstance = getNewFactory();

    public static ValidatorFactory getValidationFactory() {
        return ourInstance;
    }


    private ValidationUtils() {}

    public static <T> List<String> fromConstraintViolationSetToMessageList(Set<ConstraintViolation<T>> set) {
        if (set == null) return null;

        List<String> messages = new ArrayList();

        for (ConstraintViolation violation : set) {
            messages.add(violation.getMessage());
        }

        return messages;
    }

    public static <T> List<String> validate(T obj) {
        Validator validator = ValidationUtils.getValidationFactory().getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(obj);
        return ValidationUtils.fromConstraintViolationSetToMessageList(set);
    }

    private static ValidatorFactory getNewFactory() {
        try {
            return Validation.buildDefaultValidatorFactory();
        } catch (Throwable e) {
            e.printStackTrace();
           // throw ;
        }
        return null;
    }

}
