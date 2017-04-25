package it.fallmerayer.codingGmbH.projektFlughafen.Utility;

import it.fallmerayer.codingGmbH.projektFlughafen.Utility.Exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (!matcher.find()){
            throw new KeinPasswordException("Das Password ist zu schwach.\n 2 Großbuchstaben 1 Sonderzeichen 2 Zahlen 3 Kleinbuchstaben müssen enthalten sein.");
        }
    }

    public static void isDate(LocalDate dateString) throws KeinDatumException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            dateString.toString();
        }catch (NullPointerException e){
            throw new KeinDatumException("Ungültiges Datumformat");
        }
    }
}
