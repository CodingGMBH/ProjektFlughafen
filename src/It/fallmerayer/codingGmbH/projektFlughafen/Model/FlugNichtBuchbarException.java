package It.fallmerayer.codingGmbH.projektFlughafen.Model;

/**
 * Created by Hannes Niederwolfsgruber on 22.04.2017.
 */
public class FlugNichtBuchbarException extends Exception{

    public FlugNichtBuchbarException() {}

    public FlugNichtBuchbarException(String message) {
        super(message);
    }
}
