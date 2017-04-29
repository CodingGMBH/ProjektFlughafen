package It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeineEmailException extends IOException {
    public KeineEmailException() {}

    public KeineEmailException(String message) {
        super(message);
    }
}
