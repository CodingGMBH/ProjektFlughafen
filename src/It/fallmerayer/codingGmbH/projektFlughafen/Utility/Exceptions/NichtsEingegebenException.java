package It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class NichtsEingegebenException extends IOException {
    public NichtsEingegebenException() {}

    public NichtsEingegebenException(String message) {
        super(message);
    }
}
