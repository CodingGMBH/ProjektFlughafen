package it.fallmerayer.codingGmbH.projektFlughafen.model;

/**
 * Created by Hannes Niederwolfsgruber on 20.04.2017.
 */
public class TooMuchAngestellteException extends Exception {

    public TooMuchAngestellteException() {}

    public TooMuchAngestellteException(String message) {
        super(message);
    }
}