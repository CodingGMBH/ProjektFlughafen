package It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeinNameException extends IOException {
    public KeinNameException() {}

    public KeinNameException(String message) {
        super(message);
    }
}
