package code.infrastructure.exception;

import code.model.Role;

/**
 * Created by PC-Alyaksei on 29.04.2016.
 *
 */
public class NotAuthorizedException extends ShowToUserException {

    private static final String DEFAULT_MESSAGE = "You haven't permissions to do that!";

    public NotAuthorizedException() {
        this(DEFAULT_MESSAGE);
    }

    public NotAuthorizedException(Role needRole) {
        this(DEFAULT_MESSAGE + " Your role must be " + needRole.getName() + " to have access.");
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorizedException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
