package it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeinDatumException extends IOException {
    public KeinDatumException() {}

    public KeinDatumException(String message) {
        super(message);
    }
}
