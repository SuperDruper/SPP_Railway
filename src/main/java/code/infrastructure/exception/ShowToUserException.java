package code.infrastructure.exception;

/**
 * Created by PC-Alyaksei on 30.04.2016.
 *
 */
public class ShowToUserException extends Exception {
    public ShowToUserException() {
    }

    public ShowToUserException(String message) {
        super(message);
    }

    public ShowToUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShowToUserException(Throwable cause) {
        super(cause);
    }
}
