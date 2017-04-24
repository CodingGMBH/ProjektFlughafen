package it.fallmerayer.codingGmbH.projektFlughafen.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gabriel on 24.04.17.
 */
public class CheckValidations {
    public static boolean isNameString(String nameString){
        System.out.println(nameString + ": " + nameString.matches("[a-zA-Z]+_*[a-zA-Z]*"));
        return nameString.matches("[a-zA-Z]+_*[a-zA-Z]*");
    }

    public static boolean isValidString(String nameString){
        System.out.println(nameString + ": " + (nameString.length() > 0));
        return nameString.length() > 0;
    }

    public static boolean isEmail(String emailString){
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailString);
        System.out.println("=???");
        System.out.println(emailString + ": " + matcher.find());
        return matcher.find();
    }

    public static boolean isValidPassword(String password){
        final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$");
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        System.out.println(password + ": " + matcher.find());
        return matcher.find();
    }

    public static boolean isDate(String dateString){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            df.parse(dateString);
            System.out.println(dateString + ": " + true);
            return true;
        } catch (ParseException e) {
            System.out.println(dateString + ": " + false);
            return false;
        }
    }
}
