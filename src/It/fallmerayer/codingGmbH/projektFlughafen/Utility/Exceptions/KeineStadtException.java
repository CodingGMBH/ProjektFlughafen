package It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeineStadtException extends IOException {
    public KeineStadtException() {}

    public KeineStadtException(String message) {
        super(message);
    }
}
