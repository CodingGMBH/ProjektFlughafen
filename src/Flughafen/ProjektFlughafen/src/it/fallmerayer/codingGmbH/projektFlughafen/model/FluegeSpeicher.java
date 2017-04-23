package it.fallmerayer.codingGmbH.projektFlughafen.model;



import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * ProjektFlughafen, created by Depian Thomas on 13.04.2017.
 * (C)opright 2017 Depian Thomas - All rights reserved.
 */
public class FluegeSpeicher {
    //Attribute
    private List<Flug> fluegeListe = new LinkedList<>();
    private static FluegeSpeicher instance = null;

    //Privater Konstruktor fuer Singelton
    private FluegeSpeicher(){}

    //Instance falls noetig erstellen und dann zurueckgeben --> Singleton
    public static FluegeSpeicher getInstance(){
        if (instance==null){
            instance = new FluegeSpeicher();
        }
        return instance;
    }

    //TODO:
    /*
    * der zur Flugnummer entsprechenden Flug wird zurueckgegeben
    * falls keiner existiert --> NoSuchElementException*/
    public Flug getFlug(String flugNummer)throws NoSuchElementException{
        for (Flug i : fluegeListe){
            if (i.getFlugNummer().equals(flugNummer)){
                return i;
            }
        }
        throw new NoSuchElementException("Es existiert kein Flug mit er Flugnummer " + flugNummer + "!");
    }

    //Gibt alle Buchbaren Fluege zurueck
    public List<Flug> getBuchbareFluege(){
        List<Flug> buchbar = new LinkedList<>();
        aktualiesereBuchbar();
        for (Flug i: fluegeListe){
            if (i.isBuchbar()){
                buchbar.add(i);
            }
        }

        return buchbar;
    }

    //TODO:
    //Gibt alle Fluege zuruck, welche den Suchkriterien entsprechen
    public List<Flug> getGefilterteFluege(Flughafen startFlughafen, Flughafen zielFlughafen, Date abflugDatum, int anzahlPassagiere, double gepaeckGewicht){
        List<Flug> gefiltert = new LinkedList<>();

        for (Flug i: fluegeListe){
            if (i.getStartFlughafen().equals(startFlughafen) && i.getZielFlughafen().equals(zielFlughafen) && i.getAbflugZeit().equals(abflugDatum) && i.getFreiePlaetze()>=anzahlPassagiere && i.getFreiesGepaeckGewicht()>=gepaeckGewicht){
                gefiltert.add(i);
            }
        }

        return gefiltert;
    }

    //TODO:
    //Flug hinzufuegen
    public void addFlug(Flug flug){
        fluegeListe.add(flug);
    }


    public void aktualiesereBuchbar(){
        for (Flug i : fluegeListe){
            if (i.isBuchbar()==true){
                if ((i.getFreiePlaetze()==0 || i.getAbflugZeit().isAfter(LocalDateTime.now()))){
                    i.setBuchbar(false);
                }
            }
        }
    }

    //TODO:
    //Methode um aus Datei zu lesen
    public void ausDateiLesen() throws IOException, NumberFormatException{
        File input = new File(".\\files\\Fluege.csv");

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"))){
            String s = br.readLine();
            s = br.readLine();
            String [] flugProperties;
            Flug flug = null;
            Flugzeug flugzeug = null;
            Flughafen start = null;
            Flughafen ende = null;

            LocalDateTime abflug;
            LocalDateTime ankunft;
            String[] date = null;
            String[] time = null;
            while (s!=null){
                //*.csv-Datei aufteilen
                flugProperties = s.split(";");
                try{

                    flugzeug = new Flugzeug(Integer.parseInt(flugProperties[6]),flugProperties[1],Double.parseDouble(flugProperties[7]));
                    start = new Flughafen(flugProperties[2]);
                    ende = new Flughafen(flugProperties[3]);

                    /*
                    * Das Ablfugs- und Landedatum wird in der Form
                    * TT.MM.JJJJ  hh:mm:ss
                    * angebeben
                    * */

                    //Datum von Uhrzeit trennen
                    String[] abflugHilfe = flugProperties[4].split(" ");
                    String[] ankunftHilfe = flugProperties[5].split(" ");

                    //Datum in TT, MM, JJJJ aufteilen
                    date = abflugHilfe[0].split("\\.");

                    {
                        int i = 1;

                        //Leerzeichen zwischen Datum und Zeit ueberspringen
                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = abflugHilfe[i].split(":");

                    }
                    abflug = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                    date = ankunftHilfe[0].split("\\.");
                    {
                        int i = 1;

                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = ankunftHilfe[i].split(":");
                    }

                    ankunft = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                    flug = new Flug(flugzeug, flugProperties[0],start,ende,Integer.parseInt(flugProperties[10]),abflug,ankunft);
                    flug.setZaehlerGebuchteSitzplaetze(Integer.parseInt(flugProperties[8]));
                    flug.setZaehlerGepaeckGewicht(Double.parseDouble(flugProperties[9]));
                }catch (NumberFormatException f){
                    throw new NumberFormatException("Fehler in Datei Fluege.csv");
                }

                fluegeListe.add(flug);
                s=br.readLine();
            }
            this.aktualiesereBuchbar();
        }catch (IOException e){
            throw new IOException("Datei Fluege.csv konnte nicht gelesen werden");
        }
    }
    //TODO:
    //In Datei schreiben
    public void inDateiSchreiben()throws IOException{
        File output = new File(".\\files\\Fluege.csv");
        try ( FileWriter fw = new FileWriter(output,false)){
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Flugnummer;Fluggesellschaft;Start;Ziel;Startzeit;Ankunftszeit;Sitzplaetze;Max. GepaeckGewicht;Gebuchte Plaetze;Gewicht Gepaeck; Preis p. Sitzplatz");
            StringBuilder toWrite = new StringBuilder();
            for (Flug i: fluegeListe){
                toWrite.setLength(0);
                toWrite.append(i.getFlugNummer() + ";");
                toWrite.append(i.getFlugzeug().getFlugGesellschaft()+";");
                toWrite.append(i.getStartFlughafen().getStadt()+";");
                toWrite.append(i.getZielFlughafen().getStadt()+";");
                toWrite.append(i.getAbflugZeit().getDayOfMonth() + ".");
                toWrite.append(i.getAbflugZeit().getMonthValue() + ".");
                toWrite.append(i.getAbflugZeit().getYear() + "  ");
                toWrite.append(i.getAbflugZeit().getHour() + ":");
                toWrite.append(i.getAbflugZeit().getMinute() + ";");
                toWrite.append(i.getAnkunftZeit().getDayOfMonth() + ".");
                toWrite.append(i.getAnkunftZeit().getMonthValue() + ".");
                toWrite.append(i.getAnkunftZeit().getYear() + "  ");
                toWrite.append(i.getAnkunftZeit().getHour() + ":");
                toWrite.append(i.getAnkunftZeit().getMinute() + ";");
                toWrite.append(i.getFlugzeug().getAnzahlSitzplaetze() + ";");
                toWrite.append(i.getFlugzeug().getGepaeckKapazitaet() + ";");
                toWrite.append(i.getZaehlerGebuchteSitzplaetze() + ";");
                toWrite.append(i.getZaehlerGepaeckGewicht() + ";");
                toWrite.append(i.getPreisSitzplatz());

                bw.write(toWrite.toString());

            }

            bw.close();
        }catch (IOException e){
            throw new IOException("In Datei Fluege.csv konnte nicht geschrieben werden");
        }
    }
    //TODO:
    //Neue Fluege werden ins System aufgenommen
    //Funktion --> Siehe ausDateiLesen()
    public void fluegeInSystemEingliedern() throws IOException, NumberFormatException{
        List<Flug> eingliedern = new LinkedList<>();
        File newFluege = new File(".\\..\\Flugliste\\Update_Flugliste.csv");
        //TODO: Ausafinden wetta kodierung a file hot
        InputStreamReader input = new InputStreamReader(new FileInputStream(newFluege), "UTF-8");

        try(BufferedReader br = new BufferedReader(input)){
            String s = br.readLine();
            s = br.readLine();
            String [] flugProperties;
            Flug flug = null;
            Flugzeug flugzeug = null;
            Flughafen start = null;
            Flughafen ende = null;
            LocalDateTime abflug;
            LocalDateTime ankunft;

            String[] date = null;
            String[] time = null;
            while (s!=null){
                flugProperties = s.split(";");
                try{
                    flugzeug = new Flugzeug(Integer.parseInt(flugProperties[6]),flugProperties[1],Double.parseDouble(flugProperties[7]));
                    start = new Flughafen(flugProperties[2]);
                    ende = new Flughafen(flugProperties[3]);
                    String[] abflugHilfe = flugProperties[4].split(" ");
                    String[] ankunftHilfe = flugProperties[5].split(" ");
                    date = abflugHilfe[0].split("\\.");
                    {
                        int i = 1;

                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = abflugHilfe[i].split(":");
                    }
                    abflug = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                    date = ankunftHilfe[0].split("\\.");
                    {
                        int i = 1;

                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = ankunftHilfe[i].split(":");
                    }

                    ankunft = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);

                    //TODO: Preis pro Sitzplatz berechnen
                    flug = new Flug(flugzeug, flugProperties[0],start,ende,0,abflug,ankunft);
                }catch (NumberFormatException f){
                    throw new NumberFormatException("Fehler in Datei Fluege.csv");
                }

                eingliedern.add(flug);
                s=br.readLine();
            }

            input.close();
        }catch (IOException e){
            throw new IOException("Datei Update_Flugliste.csv konnte nicht gelesen werden");
        }

        try{

            FileWriter clearFile = new FileWriter(newFluege,false);
            clearFile.write("");
            clearFile.close();
        }catch (IOException e){
            //TODO: Bessere Exception finden fi leeren
            throw new IOException("Datei Update_Flugliste.csv konnte nicht geleert werden. Aus Sicherheitsgruenden wurden keine neuen Fluege ins System aufgefasst. Haben Sie die Datei womöglich offen?");
        }
        fluegeListe.addAll(eingliedern);
        this.aktualiesereBuchbar();


    }

    //Getter:
    public List<Flug> getFluegeListe() {
        return fluegeListe;
    }


    public static void main(String[] args) {
        FluegeSpeicher fs = FluegeSpeicher.getInstance();



        try {
            fs.ausDateiLesen();
            fs.fluegeInSystemEingliedern();
            for (Flug i : fs.getFluegeListe()){
                System.out.println("---");
                System.out.println(i.getFlugNummer());
                System.out.println(i.getFlugzeug().getFlugGesellschaft());
                System.out.println(i.getStartFlughafen().getStadt());
                System.out.println(i.getZielFlughafen().getStadt());
                System.out.println(i.getAbflugZeit().toString());
                System.out.println(i.getAnkunftZeit().toString());
                System.out.println(i.getFlugzeug().getAnzahlSitzplaetze());
                System.out.println(i.getFlugzeug().getGepaeckKapazitaet());
                System.out.println(i.getZaehlerGebuchteSitzplaetze());
                System.out.println(i.getZaehlerGepaeckGewicht());
                System.out.println(i.getPreisSitzplatz());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        fs.getFlug();
        fs.getBuchbareFluege();
        fs.getGefilterteFluege();
        fs.addFlug();
        fs.aktualiesereBuchbar();

        fs.inDateiSchreiben();
        fs.fluegeInSystemEingliedern();*/
    }


}

