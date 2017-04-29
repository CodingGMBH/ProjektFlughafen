//package It.Fallmerayer.codingGmbH.projektFlughafen.Testdateien;
//
//import It.fallmerayer.codingGmbH.projektFlughafen.Modell.*;
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
//public class AnwenderTest {
//
//    Anwender anwender = new Anwender("Thomas","Depian","thomas.depian99@gmail.com", LocalDateTime.now(),"anwender","anwender",12345);
//    String flug = "LH34892";
//
//    /*
//    * Test dann bestanden, wenn ein Flug gebucht wird und er dann im Buchungsspeicher aufscheint
//    * */
//    @Test
//    public void bucheFlug(){
//
//
//
//
//        BuchungsprofileSpeicher bs = BuchungsprofileSpeicher.getInstance();
//
//        BuchungsprofilAnwender bucheFlugfuerKunde = null;
//        try {
//            bucheFlugfuerKunde = anwender.bucheFlug(flug, 0, new LinkedList<Mitflieger>());
//        } catch (FlugNichtBuchbarException e) {
//            e.printStackTrace();
//        }
//
//        for (Buchungsprofil i : bs.getBuchungsprofile(anwender.getPID())){
//            if (i.getBuchungsID() == bucheFlugfuerKunde.getBuchungsID()){
//                Assert.assertEquals(bucheFlugfuerKunde,i);
//            }
//        }
//    }
//
//    /*
//    * Test ist dann bestanden, wenn ein Angestellter beispielsweise auf einem Flug mit 100 Sitzen versucht 1000 Plätze zu buchen
//    * und die Methode einen Fehler melet (Exception usw)
//    * */
//    @Test
//    public void bucheZuViel(){
//        BuchungsprofilAnwender buchungsprofilAnwender = null;
//        double gepaeckGewicht = 1509;
//        for (int i = 0; i < 1002; i++) {
//            if (FluegeSpeicher.getInstance().getFlug(flug).isBuchbar()){
//                try {
//                    buchungsprofilAnwender = anwender.bucheFlug(flug, gepaeckGewicht,  new LinkedList<Mitflieger>());
//
//                    System.out.println(i + " Freie Plätze: " + FluegeSpeicher.getInstance().getFlug(flug).getFreiePlaetze() + " und gepäck: " + FluegeSpeicher.getInstance().getFlug(flug).getFreiesGepaeckGewicht());
//
//                } catch (FlugNichtBuchbarException e) {
//                    System.out.println(i + e.getMessage() + " " + FluegeSpeicher.getInstance().getFlug(flug).getFlugzeug().getGepaeckKapazitaet() + " " + FluegeSpeicher.getInstance().getFlug(flug).getFlugzeug().getAnzahlSitzplaetze());
//                }
//            }else {
//                Assert.assertTrue("Flug voll",FluegeSpeicher.getInstance().getFlug(flug).getFreiePlaetze()==0);
//            }
//        }
//
//
//    }
//
//
//    /*
//    * Test ist dann bestanden, wenn bie doppelter Registrierung ein Fehler geworfen wird
//    * */
//    @Test
//    public void register(){
//
//
//
//        if(!BenutzerprofilSpeicher.getInstance().containsBenutzername("Anwender")){
//            Anwender anwender1 = new Anwender("Anwender","Anwender","anwender@example.com", LocalDateTime.now(),"anwender","anwender",12345);
//            Assert.assertTrue("Benutzernamen schon vergeben",true);
//        }
//
//
//
//
//
//
//
//    }
//
//    /*
//    * */
//    @Test
//    public void aendereBuchung(){
//
//        double neuesGepaeck = 10;
//        double gepaeckGewichtFlugold = FluegeSpeicher.getInstance().getFlug(flug).getZaehlerGepaeckGewicht();
//        //System.out.println(gepaeckGewichtFlugold);
//        //Mitflieger mitflieger = new Mitflieger("Thomas","Depian","test@example.com",LocalDateTime.now(),123456);
//        List<Mitflieger> mitfliegerList = new LinkedList<>();
//
//        BuchungsprofilAnwender buchungsprofilAnwender = null;
//        try {
//            buchungsprofilAnwender = anwender.bucheFlug(flug, 0, mitfliegerList);
//        } catch (FlugNichtBuchbarException e) {
//            e.printStackTrace();
//        }
//        try {
//            BuchungsprofileSpeicher.getInstance().aendereBuchungsprofil(buchungsprofilAnwender.getBuchungsID(), neuesGepaeck);
//        } catch (FlugNichtBuchbarException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Assert.assertTrue(buchungsprofilAnwender.getGepaeckGewicht() - 10 == gepaeckGewichtFlugold);
//    }
//
//    @Before
//    public void before(){
//        try{
//            FluegeSpeicher.getInstance().ausDateiLesen();
//            BenutzerprofilSpeicher.getInstance().ausDateiLesen();
//            BenutzerprofilSpeicher.getInstance().addBenutzerprofil(anwender);
//        }catch (IOException e){
//            e.printStackTrace();
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
