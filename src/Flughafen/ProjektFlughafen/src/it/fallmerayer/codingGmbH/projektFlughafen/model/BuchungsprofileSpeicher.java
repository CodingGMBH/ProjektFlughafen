package it.fallmerayer.codingGmbH.projektFlughafen.model;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjektFlughafen, created by Depian Thomas on 15.04.2017.
 * (C)opright 2017 Depian Thomas - All rights reserved.
 */
public class BuchungsprofileSpeicher {
    private List<Buchungsprofil> buchungsprofilListe = new LinkedList<>();
    private static int buchungsCounter = 0;
    private static BuchungsprofileSpeicher instance=null;

    private BuchungsprofileSpeicher(){}

    public static BuchungsprofileSpeicher getInstance(){
        if (instance==null){
            instance=new BuchungsprofileSpeicher();
        }

        return instance;
    }

    //TODO: Wos kimpen do daher?
    public static void aktualiesiereBuchungsCounter(int wert){
        buchungsCounter=wert;
    }

    //TODO
    public List<Buchungsprofil> getBuchungsprofile(int PID){
        List<Buchungsprofil> toReturn = new LinkedList<>();


        //TODO: olle mit PID ausaholen. ACHTUNG: De PID gibs la ba di obgeleiteten Klassen!!!
        return null;
    }


    public void addBuchungsprofil(Buchungsprofil buchungsprofil){
        buchungsprofilListe.add(buchungsprofil);
    }
/*
    public boolean loescheBuchungsprofil(int buchungsID){
        Buchungsprofil toRemove = null;
        for (Buchungsprofil i: buchungsprofilListe){
            if (i.getBuchungsID()==buchungsID){
                i=toRemove;
            }
        }

        if (toRemove==null){
            return false;
        }else {
            return buchungsprofilListe.remove(toRemove);
        }
    }*/
/*
    //TODO: woas man dass is gepaeckgewicht Plotz hot? --> NA
    public boolean aendereBuchungsprofil(int buchungsID, double gepaeckGewicht){
        for(Buchungsprofil i: buchungsprofilListe){
            if (i.getBuchungsID()==buchungsID){

            }
        }
    }*/

    //TODO
    public void ausDateiLesen() throws IOException{
        File input = new File(".\\files\\Buchungsprofile.csv");

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String s = br.readLine();
            s = br.readLine();
            String [] buchungsproperties;
            while (s!=null){
                buchungsproperties = s.split(";");
                Buchungsprofil buchungsprofil = new Buchungsprofil();
                //TODO: olles setzen
                //TODO: aupassen ba passsagiere wegen ## und &&

                System.out.println("ID: " + buchungsproperties[0]);
                System.out.println("Flug: " + buchungsproperties[1]);
                System.out.println("Gewicht Gepaeck(kg): " + buchungsproperties[2]);
                System.out.println("Gebucht von: " + buchungsproperties[3]);
                System.out.println("---Passagiere:");
                {
                    String[] i = buchungsproperties[4].split("&&");
                    String[] j = null;
                    LocalDate geb;
                    for (String k : i){
                        j = k.split("##");

                        System.out.println("\tVorname: " + j[0]);
                        System.out.println("\tNachname: " + j[1]);
                        System.out.println("\tE-Mail: " + j[2]);
                        String[] l = j[3].split("\\.");
                        geb = LocalDate.of(Integer.parseInt(l[2]),Integer.parseInt(l[1]),Integer.parseInt(l[0]));
                        System.out.println("\tGeburtsdatum: " + geb.toString());
                        System.out.println("\tNummer: " + j[4]);
                        System.out.println("\t---Next one---");

                    }
                }
                System.out.println("---Ende Passagiere");
                System.out.println("Preis (€): " + buchungsproperties[5]);
                s = br.readLine();
            }
        }catch (IOException e){
            throw new IOException("Datei Fluege.csv konnte nicht gelesen werden");
        }

    }

    //TODO
    public void inDateiSchreiebn(){}


    public static void main(String[] args) {
        BuchungsprofileSpeicher bps = BuchungsprofileSpeicher.getInstance();
        try {
            bps.ausDateiLesen();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
