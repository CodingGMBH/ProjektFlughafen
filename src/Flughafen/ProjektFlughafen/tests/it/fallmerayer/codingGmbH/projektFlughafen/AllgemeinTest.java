package it.fallmerayer.codingGmbH.projektFlughafen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by stdeptho on 27.03.2017.
 */
public class AllgemeinTest {


    @Test
    public void isValidEmail(){
        String email = "thoams.thomas.thomas@yahoo.com";

        Assert.assertTrue(email + " ist keine gultige E-Mail",email.matches(
                "^[0-9a-zA-Z]+(\\.[0-9a-zA-Z]+)?@[a-z]+\\.[a-z]{2,6}$" ));
        /*
        *   Test dann erfullt, wenn Methode anhand Regex eine Email erkennt
        * */

    }


    @Before
    public void before(){
        //Sinn???
    }

    @After
    public void after(){
        //Sinn???
    }




}
