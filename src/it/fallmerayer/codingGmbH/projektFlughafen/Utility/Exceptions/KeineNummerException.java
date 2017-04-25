package it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions;

import java.io.IOException;

/**
 * Created by gabriel on 25.04.17.
 */
public class KeineNummerException extends IOException {
    public KeineNummerException() {}

    public KeineNummerException(String message) {
        super(message);
    }
}
