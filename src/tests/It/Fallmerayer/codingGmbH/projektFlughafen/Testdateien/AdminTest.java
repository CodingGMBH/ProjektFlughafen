//package It.Fallmerayer.codingGmbH.projektFlughafen.Testdateien;
//
//import Administrator;
//import BenutzerprofilSpeicher;
//import TooMuchAngestellteException;
////import it.fallmerayer.codingGmbH.projektFlughafen.model.*;
//import org.junit.*;
//
//import java.time.LocalDateTime;
//
///**
// * Created by stdeptho on 27.03.2017.
// */
//public class AdminTest {
//    Administrator admin;
//    BenutzerprofilSpeicher bs = BenutzerprofilSpeicher.getInstance();
//
//
//    /*
//    * Test dann erfullt, wenn Admin ein Angestelltenkonto erstellt und jenes im Nutzerprofilspeicher aufscheint
//    * Die maximale Anzahl an Angestellten (ID-Kreis) darf dabei nicht überschhritten werden
//    * */
//    @Test
//    public void erstelleAngestelltenKonto(){
//        final int ANZAHLANGESTELLTEN = 10000;
//        int eingetrageneAngestellten = 0;
//
//        for (int i = 0; i < ANZAHLANGESTELLTEN; i++) {
//            try{
//                admin.erstelleAngestelltenKonto("Test", "Angestellter", "angestellter.test@example.com", LocalDateTime.now(), "Angestellter"+i, String.valueOf(Math.random()));
//            }catch (TooMuchAngestellteException e){
//                Assert.assertTrue(true);
//                return;
//            }
//        }
//
//        for (int i = 0; i < ANZAHLANGESTELLTEN; i++) {
//            if (bs.containsBenutzername("Angestellter"+i)){
//                eingetrageneAngestellten++;
//            }
//        }
//
//        Assert.assertTrue(ANZAHLANGESTELLTEN==eingetrageneAngestellten);
//    }
//
//    /*
//    * Test dann erfullt, wenn Admin ein Adminkonto erstellt und jenes im Nutzerprofilspeicher aufscheint
//    * Die maximale Anzahl an Admins (ID-Kreis) darf dabei nicht übershcritten werden
//    * */
//    @Test
//    public void erstelleAdminKonto(){
//        final int ANZAHLADMIN = 1000;
//        int eingetrageneAdmin = 0;
//
//        for (int i = 0; i < ANZAHLADMIN; i++) {
//            try{
//                admin.erstelleAdministratorKonto("Test", "Admin", "admin.test@example.com", LocalDateTime.now(), "Admin"+i, String.valueOf(Math.random()));
//            }catch (TooMuchAngestellteException e){
//                Assert.assertTrue(true);
//                return;
//            }
//        }
//
//        for (int i = 0; i < ANZAHLADMIN; i++) {
//            if (bs.containsBenutzername("Admin"+i)){
//                eingetrageneAdmin++;
//            }
//        }
//
//        Assert.assertTrue(ANZAHLADMIN==eingetrageneAdmin);
//
//
//    }
//
//
//
//    @Before
//    public void before(){
//        try {
//            admin = new Administrator("Admin","Admin","Admin",LocalDateTime.now(),"Admin","Admin");
//        } catch (TooMuchAngestellteException e) {
//            Assert.assertTrue(false);
//        }
//    }
///*
//    @After
//    public void after(){
//        //Filepointer dereferenzieren
//        //Stream schliesse
//    }*/
//}
