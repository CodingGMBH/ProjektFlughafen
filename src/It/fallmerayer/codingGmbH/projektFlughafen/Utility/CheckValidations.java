package It.fallmerayer.codingGmbH.projektFlughafen.Utility;

import It.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gabriel on 24.04.17.
 */
public class CheckValidations {
    public static void isNameString(String nameString) throws KeinNameException {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("[a-zA-Z]+_*[a-zA-Z]*");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(nameString);
        if (!matcher.find()){
            throw new KeinNameException("Der angegebene Name ist ungültig");
        }
    }

    public static void isValidString(String nameString) throws NichtsEingegebenException {
        if (nameString.length() < 1){
            throw new NichtsEingegebenException("Es wurde nicht alle Felder ausgefüllt");
        }
    }

    public static void isEmail(String emailString) throws KeineEmailException {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailString);
        if (!matcher.find()){
            throw new KeineEmailException("Ungültiges Emailformat");
        }
    }

    public static void isValidPassword(String password) throws KeinPasswordException {
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z].*[A-Z])(?=.*[!@$+*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (!matcher.find()){
            throw new KeinPasswordException("Das Password ist zu schwach.\n2 Großbuchstaben\n1 Sonderzeichen (!@$+*)\n2 Zahlen\n3 Kleinbuchstaben\nmüssen enthalten sein.");
        }
    }

    public static void isDate(LocalDate dateString) throws KeinDatumException {
        try {
            dateString.toString();
        }catch (NullPointerException e){
            throw new KeinDatumException("Ungültiges Datumformat");
        }
    }

    public static void isCity(String city) throws KeineStadtException {
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(city);
        if (!matcher.find()){
            throw new KeineStadtException("Ungültiges Stadtformat");
        }
    }

    public static void isKommaNumber(String number) throws KeineNummerException {
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("(:?^|\\s)(?=.)((?:0|(?:[1-9](?:\\d*|\\d{0,2}(?:,\\d{3})*)))?(?:\\.\\d*[1-9])?)(?!\\S)");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(number);
        if (!matcher.find()){
            System.out.println("Test");
            throw new KeineNummerException("Ungültiges Nummernformat");
        }
    }

    public static void isNumber(String number) throws KeineNummerException {
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("[0-9]+");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(number);
        if (!matcher.find()){
            throw new KeineNummerException("Ungültiges Nummernformat");
        }
    }
}
