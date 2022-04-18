package velykyi.vladyslav.DemoTestNJ.service.impl;

import org.springframework.stereotype.Component;
import velykyi.vladyslav.DemoTestNJ.exception.EmailNotAllowedException;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

@Component
public class ServiceValidator {

    public static void validateEmail(String email) {
        validate(email, ServiceValidator::patternMatches, EmailNotAllowedException::new);
    }

    /**
     * <p>Some email addresses that'll be valid via this email validation technique are:
     * <li>username@domain.com
     * <li>user.name@domain.com
     * <li>user-name@domain.com
     * <li>username@domain.co.in
     * <li>user_name@domain.com
     * <p><p>Shortlist of some email addresses that'll be invalid via this email validation:
     * <li>username.@domain.com
     * <li>.user.name@domain.com
     * <li>user-name@domain.com.
     * <li>username@.com
     * @param emailAddress  email to be validated
     * @return result of validation
     */
    private static boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" +
                               "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                      .matcher(emailAddress)
                      .matches();
    }

    private static <T> void validate(T value,
                                    Predicate<T> invalidValueVerifier,
                                    Supplier<RuntimeException> exceptionSupplier) {
        if (!invalidValueVerifier.test(value)) {
            throw exceptionSupplier.get();
        }
    }
}
