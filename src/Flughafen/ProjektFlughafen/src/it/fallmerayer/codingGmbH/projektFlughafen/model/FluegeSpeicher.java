package it.fallmerayer.codingGmbH.projektFlughafen.model;



import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
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
    private List<Flug> fluegeListe = new LinkedList<>();
    private FluegeSpeicher instance = null;


    private FluegeSpeicher(){}


    public FluegeSpeicher getInstance(){
        if (instance==null){
            instance = new FluegeSpeicher();
        }
        return instance;
    }

    //TODO: Passt Exception?
    public Flug getFlug(String flugNummer)throws NoSuchElementException{
        for (Flug i : fluegeListe){
            if (i.getFlugNummer().equals(flugNummer)){
                return i;
            }
        }
        throw new NoSuchElementException("Es existiert kein Flug mit er Flugnummer " + flugNummer + "!");
    }

    //TODO: Check ob des passt, nochdem i di datei krieag hon
    public List<Flug> getBuchbareFluege(){
        List<Flug> buchbar = new LinkedList<>();

        for (Flug i: fluegeListe){
            if (i.isBuchbar()){
                buchbar.add(i);
            }
        }

        return buchbar;
    }

    //TODO: freies Gepäckgewicht selbo ausrechnen odo wiea?
    public List<Flug> getGefilterteFluege(Flughafen startFlughafen, Flughafen zielFlughafen, Date abflugDatum, int anzahlPassagiere, double gepaeckGewicht){
        List<Flug> gefiltert = new LinkedList<>();

        for (Flug i: fluegeListe){
            if (i.getStartFlughafen().equals(startFlughafen) && i.getZielFlughafen().equals(zielFlughafen) && i.getFreiePlaetze()>=anzahlPassagiere && i.getFreiesGepaeckGewicht()>=gepaeckGewicht){
                gefiltert.add(i);
            }
        }

        return gefiltert;
    }

    //TODO:
    public void addFlug(Flug flug){
        fluegeListe.add(flug);
    }

    //TODO:
    public void ausDateiLesen() throws IOException, NumberFormatException{
        File input = new File(".\\files\\Fluege.csv");

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String s = br.readLine();
            s = br.readLine();
            String [] flugProperties;
            Flug flug = null;
            Flugzeug flugzeug = null;
            Flughafen start = null;
            Flughafen ende = null;
            //TODO wrkl Date hernemmen? --> besser  LocalDateTime
            Date abflug = new Date();
            Date ankunft = new Date();


            LocalDateTime abflugD;
            LocalDateTime ankunftD;
            String[] date = null;
            String[] time = null;
            while (s!=null){
                flugProperties = s.split(";");
                try{
                    //TODO: null setzetn odo ""
                    flugzeug = new Flugzeug("",Integer.parseInt(flugProperties[6]),flugProperties[1],Double.parseDouble(flugProperties[7]));
                    start = new Flughafen("",flugProperties[2],"");
                    ende = new Flughafen("",flugProperties[3],"");
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
                    abflugD = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
                    date = ankunftHilfe[0].split("\\.");
                    {
                        int i = 1;

                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = ankunftHilfe[i].split(":");
                    }

                    ankunftD = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
                    //TODO: Date --> LocalDateTime
                    //flug = new Flug(flugzeug, flugProperties[0],start,ende,Integer.parseInt(flugProperties[10]),abflugD,ankunftD);
                }catch (NumberFormatException f){
                    throw new NumberFormatException("Fehler in Datei Fluege.csv");
                }

                //TODO: olles setzen
                fluegeListe.add(flug);
            }
        }catch (IOException e){
            throw new IOException("Datei Fluege.csv konnte nicht gelesen werden");
        }
    }
    //TODO:
    public void inDateiSchreiben()throws IOException{
        File output = new File(".\\files\\Fluege.csv");
        try ( FileWriter fw = new FileWriter(output,false)){
            fw.write("");
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder toWrite = new StringBuilder();
            for (Flug i: fluegeListe){
                toWrite.setLength(0);
                toWrite.append(i.getFlugNummer() + ";");
                toWrite.append(i.getFlugzeug().getFlugGesellschaft()+";");
                toWrite.append(i.getStartFlughafen().getStadt()+";");
                toWrite.append(i.getZielFlughafen().getStadt()+";");
                //TODO: Anpassen an Forat von *.csv
                toWrite.append(i.getAbflugZeit() + ";");
                toWrite.append(i.getAnkunftZeit() + ";");
                toWrite.append(i.getFlugzeug().getAnzahlSitzplaetze() + ";");
                toWrite.append(i.getFlugzeug().getGepaeckKapazitaet() + ";");
                toWrite.append(i.getZaehlerGebuchteSitzplaetze() + ";");
                toWrite.append(i.getZaehlerGepaeckGewicht() + ";");
                toWrite.append(i.getPreisSitzplatz() + ";");
                if (i.isBuchbar()){
                    toWrite.append("1" + System.lineSeparator());
                }else {
                    toWrite.append("0" + System.lineSeparator());
                }

                bw.write(toWrite.toString());

            }

            bw.close();
        }catch (IOException e){
            throw new IOException("In Datei Fluege.csv konnte nicht geschrieben werden");
        }
    }
    //TODO:
    public void fluegeInSystemEingliedern() throws IOException, NumberFormatException{
        File newFluege = new File(".\\..\\Flugliste\\UpdateFlugliste.csv");

        try(BufferedReader br = new BufferedReader(new FileReader(newFluege))){
            String s = br.readLine();
            s = br.readLine();
            String [] flugProperties;
            Flug flug = null;
            Flugzeug flugzeug = null;
            Flughafen start = null;
            Flughafen ende = null;
            //TODO wrkl Date hernemmen? --> besser  LocalDateTime
            Date abflug = new Date();
            Date ankunft = new Date();


            LocalDateTime abflugD;
            LocalDateTime ankunftD;
            String[] date = null;
            String[] time = null;
            while (s!=null){
                flugProperties = s.split(";");
                try{
                    //TODO: null setzetn odo ""
                    flugzeug = new Flugzeug("",Integer.parseInt(flugProperties[6]),flugProperties[1],Double.parseDouble(flugProperties[7]));
                    start = new Flughafen("",flugProperties[2],"");
                    ende = new Flughafen("",flugProperties[3],"");
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
                    abflugD = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
                    date = ankunftHilfe[0].split("\\.");
                    {
                        int i = 1;

                        while (abflugHilfe[i].equals(" ")){
                            i++;
                        }

                        time = ankunftHilfe[i].split(":");
                    }

                    ankunftD = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]));
                    //TODO: Date --> LocalDateTime
                    //flug = new Flug(flugzeug, flugProperties[0],start,ende,Integer.parseInt(flugProperties[10]),abflugD,ankunftD);
                }catch (NumberFormatException f){
                    throw new NumberFormatException("Fehler in Datei Fluege.csv");
                }

                //TODO: olles setzen
                fluegeListe.add(flug);
            }
            FileWriter clearFile = new FileWriter(newFluege,false);
            clearFile.write("");
            clearFile.close();

        }catch (IOException e){
            throw new IOException("Datei UpdateFlugliste.csv konnte nicht gelesen werden");
        }


    }







}

