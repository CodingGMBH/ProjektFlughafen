//package It.Fallmerayer.codingGmbH.projektFlughafen.Testdateien;
//
//import It.fallmerayer.codingGmbH.projektFlughafen.Modell.*;
//import org.junit.*;
//
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.LinkedList;
//
///**
// * Created by stdeptho on 27.03.2017.
// */
//public class FlugTest {
//    Flug flugNonstatic;
//    static Flug flug;
//    BuchungsprofileSpeicher bps = BuchungsprofileSpeicher.getInstance();
//
//    /*
//    * Test ist dann bestanden, wenn getFreiePlatze + gebuchtePlätze = gesamtPlätze
//    * */
//    @Test
//    public void difference(){
//        int alleGebuchtenPlaetze=0;
//        for (Buchungsprofil i: bps.getBuchungsprofilListe()){
//            if (i.getFlugNummer().equals(flugNonstatic.getFlugNummer())){
//                alleGebuchtenPlaetze++;
//            }
//        }
//        Assert.assertTrue(flugNonstatic.getFreiePlaetze() + alleGebuchtenPlaetze == flugNonstatic.getFlugzeug().getAnzahlSitzplaetze());
//    }
//
//    /*
//    * Test ist dann bestanden, wenn auf einem Flug alle Platze gebucht wurden und der Flug nachher nicht mehr buchbar ist
//    * */
//    @Test
//    public void changeBuchbar() {
//        for (int i = 0; i < flugNonstatic.getFlugzeug().getAnzahlSitzplaetze(); i++) {
//            try {
//                new BuchungsprofilAnwender(flugNonstatic.getFlugNummer(),0,1001,new LinkedList<Mitflieger>());
//            } catch (FlugNichtBuchbarException e) {
//                Assert.assertTrue(false);
//            }
//        }
//
//        Assert.assertTrue(!flugNonstatic.isBuchbar());
//    }
//
//
//
//
//
//    @Before
//    public void before(){
//        try {
//            FluegeSpeicher.getInstance().ausDateiLesen();
//            flugNonstatic = FluegeSpeicher.getInstance().getFlug("KL");
//            bps.ausDateiLesen();
//        } catch (IOException e) {
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
//
//
//
//    @BeforeClass
//    public static void beforeClass() {
//        System.out.println("Before class.");
//
//        LocalDateTime abflugZeit = LocalDateTime.of(2017, 12, 30, 13, 0);		// LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)
//        LocalDateTime ankunftZeit = LocalDateTime.of(2017, 12, 30, 17, 30);		// LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute)
//
//        FlugTest.flug = new Flug(new Flugzeug(30, "Lufthansa", 100.0), "LH12345", new Flughafen("Muenchen"), new Flughafen("Barcelona"), 50.0, abflugZeit, ankunftZeit);
//    }
//
//    @Before
//    public void beforeTest() {
//        System.out.println("Before Test.");
//    }
//
//    @Test
//    public void test1() {
//        System.out.println("Test 1.");
//        Assert.assertTrue(FlugTest.flug.getFlugzeug().getAnzahlSitzplaetze() == 30);
//    }
//
//    @Test
//    public void test2() {
//        System.out.println("Test 2.");
//        Assert.assertTrue(FlugTest.flug.getFlugzeug().getFlugGesellschaft().equals("Lufthansa"));
//    }
//
//    @Test
//    public void test3() {
//        System.out.println("Test 3.");
//        Assert.assertTrue(FlugTest.flug.getFlugzeug().getGepaeckKapazitaet() == 100);
//    }
//
//    @Test
//    public void test4() {
//        System.out.println("Test 4.");
//        Assert.assertTrue(FlugTest.flug.getFlugNummer().equals("LH12345"));
//    }
//
//    @Test
//    public void test5() {
//        System.out.println("Test 5.");
//        Assert.assertTrue(FlugTest.flug.getStartFlughafen().getStadt().equals("Muenchen"));
//    }
//
//    @Test
//    public void test6() {
//        System.out.println("Test 6.");
//        Assert.assertTrue(FlugTest.flug.getZielFlughafen().getStadt().equals("Barcelona"));
//    }
//
//    @Test
//    public void test7() {
//        System.out.println("Test 7.");
//        Assert.assertTrue(FlugTest.flug.getPreisSitzplatz() == 50);
//    }
//
//    @Test
//    public void test8() {
//        System.out.println("Test 8.");
//        Assert.assertTrue(FlugTest.flug.getAbflugZeit().equals(LocalDateTime.of(2017, 12, 30, 13, 0)));
//    }
//
//    @Test
//    public void test9() {
//        System.out.println("Test 9.");
//        Assert.assertTrue(FlugTest.flug.getAnkunftZeit().equals(LocalDateTime.of(2017, 12, 30, 17, 30)));
//    }
//
//    @Test
//    public void test10() {
//        System.out.println("Test 10.");
//        Assert.assertTrue(FlugTest.flug.getZaehlerGebuchteSitzplaetze() == 0);
//    }
//
//    @Test
//    public void test11() {
//        System.out.println("Test 11.");
//        Assert.assertTrue(FlugTest.flug.getZaehlerGepaeckGewicht() == 0);
//    }
//
//    @Test
//    public void test12() {
//        System.out.println("Test 12.");
//        Assert.assertTrue(FlugTest.flug.isBuchbar() == true);
//    }
//
//    @Test
//    public void test13() {
//        System.out.println("Test 13.");
//        Assert.assertTrue(FlugTest.flug.getDauerFlug() == 4.5);
//    }
//
//    @Test
//    public void test14() {
//        System.out.println("Test 14.");
//        Assert.assertTrue(FlugTest.flug.getFreiePlaetze() == 30);
//    }
//
//    @Test
//    public void test15() {
//        System.out.println("Test 15.");
//        Assert.assertTrue(FlugTest.flug.getFreiesGepaeckGewicht() == 100);
//    }
//
//    @After
//    public void afterTest() {
//        System.out.println("After Test.");
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        System.out.println("After class.");
//    }
//}
