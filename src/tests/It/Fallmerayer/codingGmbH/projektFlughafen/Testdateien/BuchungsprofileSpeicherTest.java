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
//public class BuchungsprofileSpeicherTest {
//
//    BuchungsprofileSpeicher bps = BuchungsprofileSpeicher.getInstance();
//    BenutzerprofilSpeicher beps = BenutzerprofilSpeicher.getInstance();
//    FluegeSpeicher fs = FluegeSpeicher.getInstance();
//
//
//    /*
//    * Test ist dann bestanden, wenn after.equals(before) true zuruckliefert und somit das Buchungsprofil sauber gespeichert wurde
//    * */
//    @Test
//    public void savedCorrectly() throws FlugNichtBuchbarException, IOException {
//
//        Angestellter angestellter = null;
//        Mitflieger mitflieger = new Mitflieger("Thomas","Depian","test99@example.com",LocalDateTime.now(),123456);
//        List<Mitflieger> mitfliegerList = new LinkedList<>();
//        mitfliegerList.add(mitflieger);
//
//        for (Angestellter a : beps.getAngestelltenListe()){
//            if (a.getPID() == 105){
//                angestellter=a;
//            }
//        }
//        int buchungsid = BuchungsprofileSpeicher.getBuchungsCounter();
//        Buchungsprofil before = angestellter.bucheFlugFuerKunde("EJ1222",12,mitfliegerList);
//        Buchungsprofil after = null;
//        for (Buchungsprofil i : bps.getBuchungsprofile(105)){
//            if ((i.getBuchungsID() == buchungsid)){
//                after=i;
//            }
//        }
//        Assert.assertTrue(after.equals(before));
//
//    }
//
//
//
//
//
//    @Before
//    public void before(){
//        try{
//            fs.ausDateiLesen();
//            bps.ausDateiLesen();
//            beps.ausDateiLesen();
//
//        }catch (IOException e){
//            Assert.assertTrue(false);
//        }
//    }
//
//    @After
//    public void after(){
//
//    }
//}
