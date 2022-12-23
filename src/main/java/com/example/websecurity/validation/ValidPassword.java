package com.example.websecurity.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidPassword.Validator.class)
@Documented
public @interface ValidPassword {

    String message() default """
            Password must contain at least one digit [0-9],at least one lowercase Latin character [a-z],
            at least one uppercase Latin character [A-Z],at least one special character like ! @ # & ( ),
            a length of at least 8 characters and a maximum of 20 characters.
            """;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<ValidPassword, String> {

        public static final Pattern PATTERN = Pattern.compile(
                "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"
        );

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return PATTERN.matcher(value).matches();
        }
    }
}
