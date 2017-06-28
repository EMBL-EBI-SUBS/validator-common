package uk.ac.ebi.subs.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by karoly on 28/06/2017.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationResultNotFoundException extends RuntimeException {

    public ValidationResultNotFoundException(String idType, String id) {
        super(getMessage(idType, id));
    }

    public static String getMessage(String idType, String id) {
        return String.format("Could not find ValidationResult by %s id: %s", idType, id);
    }
}
