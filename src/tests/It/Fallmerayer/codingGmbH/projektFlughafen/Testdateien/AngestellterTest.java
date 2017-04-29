//package It.Fallmerayer.codingGmbH.projektFlughafen.Testdateien;
//
//import It.fallmerayer.codingGmbH.projektFlughafen.Model.*;
//import org.junit.*;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * Created by stdeptho on 27.03.2017.
// */
//public class AngestellterTest {
//    Angestellter angestellter;
//
//    /*
//    * Test dann bestanden, wenn ein Flug gebucht wird und er dann im Buchungsspeicher aufscheint
//    * */
//    @Test
//    public void bucheFlugfuerKunde(){
//        BuchungsprofileSpeicher bs = BuchungsprofileSpeicher.getInstance();
//        String flugNummer = "LH34892";
//        double gepaeckGewicht = 0;
//        List<Mitflieger> passagierListe = new LinkedList<>();
//        passagierListe.add(new Mitflieger("dfg", "dghsfh", "nichtsenden@example.com", LocalDateTime.now(), 69078560));
//
//        BuchungsprofilAngestellter bucheFlugfuerKunde = null;
//
//        try {
//            bucheFlugfuerKunde = angestellter.bucheFlugFuerKunde(flugNummer, gepaeckGewicht, passagierListe);
//        } catch (FlugNichtBuchbarException e) {
//            e.printStackTrace();
//        }
//
//        for (Buchungsprofil i : bs.getBuchungsprofile(angestellter.getPID())){
//            if (i.getBuchungsID() == bucheFlugfuerKunde.getBuchungsID()){
//                Assert.assertEquals(bucheFlugfuerKunde,i);
//            }
//        }
//
//
//    }
//
//    /*
//    * Test ist dann bestanden, wenn ein Angestellter beispielsweise auf einem Flug mit 100 Sitzen versucht 1000 Plätze zu buchen
//    * und die Methode einen Fehler melet (Exception usw)
//    * */
//    @Test
//    public void bucheZuViel(){
//
//        BuchungsprofilAngestellter buchungsprofilAngestellter = null;
//        String flugNummer = "LH34892";
//        double gepaeckGewicht = 0;
//        List<Mitflieger> passagierListe = new LinkedList<>();
//        passagierListe.add(new Mitflieger("dfg", "dghsfh", "nichtsenden@example.com", LocalDateTime.now(), 69078560));
//
//        for (int i = 0; i < 1000; i++) {
//            if (FluegeSpeicher.getInstance().getFlug(flugNummer).isBuchbar()){
//                try {
//                    buchungsprofilAngestellter = angestellter.bucheFlugFuerKunde(flugNummer, gepaeckGewicht, passagierListe);
//                } catch (FlugNichtBuchbarException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                Assert.assertTrue("Flug voll", FluegeSpeicher.getInstance().getFlug(flugNummer).getFreiePlaetze()==0);
//            }
//        }
//
//
//    }
//
//    /*
//    * Test ist dann bestanden, wenn ein Angestellter eine Buchung andert und diese Anderung im BIP aufscheint
//    * */
//    @Test
//    public void aendereBuchung(){
//
//        String flugNummer = "LH34892";
//        List<Mitflieger> passagierListe = new LinkedList<>();
//        passagierListe.add(new Mitflieger("dfg", "dghsfh", "nichtsenden@example.com", LocalDateTime.now(), 69078560));
//        double neuesGepaeck;
//        double gepaeckGewichtFlugold = FluegeSpeicher.getInstance().getFlug(flugNummer).getZaehlerGepaeckGewicht();
//        double gepaeckGewichtFlugnew = gepaeckGewichtFlugold + 10;
//
//
//        BuchungsprofilAngestellter buchungsprofilAngestellter = null;
//        try{
//            buchungsprofilAngestellter = angestellter.bucheFlugFuerKunde(flugNummer, gepaeckGewichtFlugold, passagierListe);
//            BuchungsprofileSpeicher.getInstance().aendereBuchungsprofil(buchungsprofilAngestellter.getBuchungsID(), gepaeckGewichtFlugnew);
//        }catch (IOException e){
//            e.printStackTrace();
//        }catch (FlugNichtBuchbarException e){
//            e.printStackTrace();
//        }
//
//        Assert.assertTrue(buchungsprofilAngestellter.getGepaeckGewicht() - 10 == gepaeckGewichtFlugold);
//    }
//
//    @Before
//    public void before(){
//        try{
//            FluegeSpeicher.getInstance().ausDateiLesen();
//            BenutzerprofilSpeicher.getInstance().ausDateiLesen();
//            angestellter = new Angestellter("Angestellter","Angestellter","angestellter@example.com", LocalDateTime.now(),"Angestellter","angesteller");
//            BenutzerprofilSpeicher.getInstance().addBenutzerprofil(angestellter);
//        }catch (IOException e){
//            e.printStackTrace();
//            Assert.assertTrue(false);
//        } catch (TooMuchAngestellteException e) {
//            Assert.assertTrue(false);
//        }
//    }
//
//    @After
//    public void after(){
//        //Filepointer dereferenzieren
//        //Stream schliesse
//    }
//}
