//package It.Fallmerayer.codingGmbH.projektFlughafen.Testdateien;
//
//import FluegeSpeicher;
////import it.fallmerayer.codingGmbH.projektFlughafen.model.FluegeSpeicher;
////import it.fallmerayer.codingGmbH.projektFlughafen.model.Flug;
////import it.fallmerayer.codingGmbH.projektFlughafen.model.Flughafen;
////import it.fallmerayer.codingGmbH.projektFlughafen.model.Flugzeug;
//import org.junit.*;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
///**
// * Created by stdeptho on 27.03.2017.
// */
//public class FluegeSpeicherTest {
//
//    FluegeSpeicher fs = FluegeSpeicher.getInstance();
//    int neuefluege =0;
//    /*
//    * Test ist dann bestanden, wenn der Fluegspeicher nach dem einlesen der neuen Fluege, die alten und neuen Fluege enthaelt
//    * */
//    @Test
//    public void update(){
//        int before = fs.getFluegeListe().size();
//        try{
//
//            fs.fluegeInSystemEingliedern();
//
//            Assert.assertTrue("Alle Fluege eingegleidert", fs.getFluegeListe().size()==before+neuefluege);
//        }catch (IOException e){
//            Assert.assertTrue(false);
//        }
//    }
//
//
//
//
//
//    @Before
//    public void before(){
//        String s;
//
//        try{
//            fs.ausDateiLesen();
//        }catch (IOException e){
//            Assert.assertTrue(false);
//        }
//
//        try(BufferedReader br = new BufferedReader(new FileReader(".\\..\\Flugliste\\Update_Flugliste.csv"))){
//            s = br.readLine();
//            s = br.readLine();
//            while (s!=null) {
//                neuefluege++;
//                s=br.readLine();
//            }
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
