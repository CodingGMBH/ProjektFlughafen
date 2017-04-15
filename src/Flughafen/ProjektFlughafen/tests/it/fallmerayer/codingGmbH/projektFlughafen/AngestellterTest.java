package it.fallmerayer.codingGmbH.projektFlughafen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by stdeptho on 27.03.2017.
 */
public class AngestellterTest {
    //Angestellter angestellter = new Angestellter();


    @Test
    public void bucheFlugfuerKunde(){
        /*
        * angestellter.bucheFlugfuerKunde(flugNummer, gepaeckGewicht, passagierListe, angestelltenNummer);


        * Test dann bestanden, wenn ein Flug gebucht wird und er dann im Buchungsspeicher aufscheint
        * */
    }

    @Test
    public void bucheZuViel(){
        /*
        * Test ist dann bestanden, wenn ein Angestellter beispielsweise auf einem Flug mit 100 Sitzen versucht 1000 Plätze zu buchen
        * und die Methode einen Fehler melet (Exception usw)
        * */
    }

    @Test
    public void aendereBuchung(){
        /*
        angestellter.aendereBuchung(buchungsID);


        * Test ist dann bestanden, wenn ein Angestellter eine Buchung andert und diese Anderung im BIP aufscheint*/
    }



    @Before
    public void before(){
        //Daten von File holen
        //Stream holen
    }

    @After
    public void after(){
        //Filepointer dereferenzieren
        //Stream schliesse
    }
}
