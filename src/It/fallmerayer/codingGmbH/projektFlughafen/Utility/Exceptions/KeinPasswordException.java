package It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeinPasswordException extends IOException {
    public KeinPasswordException() {}

    public KeinPasswordException(String message) {
        super(message);
    }
}
