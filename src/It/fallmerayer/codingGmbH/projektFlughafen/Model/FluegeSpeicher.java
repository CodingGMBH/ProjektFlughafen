package It.fallmerayer.codingGmbH.projektFlughafen.Model;




import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    //Gibt alle Fluege zuruck, welche den Suchkriterien entsprechen
    public List<Flug> getGefilterteFluege(Flughafen startFlughafen, Flughafen zielFlughafen, LocalDateTime abflugDatum, int anzahlPassagiere, double gepaeckGewicht){
        List<Flug> gefiltert = new LinkedList<>();

        for (Flug i: fluegeListe){
            if (i.getStartFlughafen().equals(startFlughafen) && i.getZielFlughafen().equals(zielFlughafen) && i.getAbflugZeit().toLocalDate().isEqual(abflugDatum.toLocalDate()) && i.getFreiePlaetze()>=anzahlPassagiere && i.getFreiesGepaeckGewicht()>=gepaeckGewicht && i.isBuchbar()){
                gefiltert.add(i);
            }
        }

        return gefiltert;
    }


    //Flug hinzufuegen
    //Wenn die Flugnummer schon existiert wird der Flug nich hinzugefuegt
    public void addFlug(Flug flug){
        boolean containsID = false;
        for (Flug i : fluegeListe){
            if (i.getFlugNummer().equals(flug.getFlugNummer())){
                containsID=true;
            }
        }
        if (!containsID){
            fluegeListe.add(flug);
        }
    }



    public void aktualiesereBuchbar(){
        for (Flug i : fluegeListe) {
            if ((i.getFreiePlaetze() == 0 || i.getAbflugZeit().isBefore(LocalDateTime.now()))) {
                i.setBuchbar(false);
            }
        }
    }

    //Methode um aus Datei zu lesen
    public void ausDateiLesen() throws IOException, NumberFormatException{
        File input = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "Fluege.csv");


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
                if (!s.equals("")){
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
                            while (abflugHilfe[i].equals("")){
                                i++;
                            }

                            time = abflugHilfe[i].split(":");

                        }

                        abflug = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                        date = ankunftHilfe[0].split("\\.");

                        {
                            int i = 1;

                            while (ankunftHilfe[i].equals("")){
                                i++;
                            }
                            time = ankunftHilfe[i].split(":");
                        }




                        ankunft = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                        flug = new Flug(flugzeug, flugProperties[0],start,ende,Double.parseDouble(flugProperties[10]),abflug,ankunft);
                        flug.setZaehlerGebuchteSitzplaetze(Integer.parseInt(flugProperties[8]));
                        flug.setZaehlerGepaeckGewicht(Double.parseDouble(flugProperties[9]));


                    }catch (NumberFormatException f){
                        throw new NumberFormatException("Fehler in Datei Fluege.csv");
                    }

                    fluegeListe.add(flug);
                }

                s=br.readLine();
            }
            this.aktualiesereBuchbar();
        }catch (IOException e){
            throw new IOException("Datei Fluege.csv konnte nicht gelesen werden");
        }
    }

    //In Datei schreiben
    public void inDateiSchreiben()throws IOException{
        File output = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "Fluege.csv");

        try ( FileOutputStream fw = new FileOutputStream(output,false)){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fw,"UTF-8"));
            bw.write("Flugnummer;Fluggesellschaft;Start;Ziel;Startzeit;Ankunftszeit;Sitzplaetze;Max. GepaeckGewicht;Gebuchte Plaetze;Gewicht Gepaeck; Preis p. Sitzplatz\n");
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

                if (i.getAbflugZeit().getMinute()<10){
                    toWrite.append("0" + i.getAbflugZeit().getMinute() + ";");
                }else {
                    toWrite.append(i.getAbflugZeit().getMinute() + ";");
                }
                toWrite.append(i.getAnkunftZeit().getDayOfMonth() + ".");
                toWrite.append(i.getAnkunftZeit().getMonthValue() + ".");
                toWrite.append(i.getAnkunftZeit().getYear() + "  ");
                toWrite.append(i.getAnkunftZeit().getHour() + ":");
                if (i.getAnkunftZeit().getMinute()<10){
                    toWrite.append("0" + i.getAnkunftZeit().getMinute() + ";");
                }else {
                    toWrite.append(i.getAnkunftZeit().getMinute() + ";");
                }

                toWrite.append(i.getFlugzeug().getAnzahlSitzplaetze() + ";");
                toWrite.append(i.getFlugzeug().getGepaeckKapazitaet() + ";");
                toWrite.append(i.getZaehlerGebuchteSitzplaetze() + ";");
                toWrite.append(i.getZaehlerGepaeckGewicht() + ";");
                toWrite.append(i.getPreisSitzplatz());

                toWrite.append(System.lineSeparator());
                bw.write(toWrite.toString());

            }

            bw.close();
        }catch (IOException e){
            throw new IOException("In Datei Fluege.csv konnte nicht geschrieben werden");
        }
    }

    //Neue Fluege werden ins System aufgenommen
    //Funktion --> Siehe ausDateiLesen()
    public void fluegeInSystemEingliedern() throws IOException, NumberFormatException{
        boolean containsID = false;
        List<Flug> eingliedern = new LinkedList<>();

        File newFluege = new File(System.getProperty("user.dir") + File.separator + ".." + File.separator + "Flugliste" + File.separator + "Update_Flugliste.csv");
        //File newFluege = new File(".\\..\\Flugliste\\Update_Flugliste.csv");

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
                if (!s.equals("")){
                    flugProperties = s.split(";");
                    containsID = false;
                    for (Flug i : fluegeListe){
                        if (i.getFlugNummer().equals(flugProperties[0])){
                            containsID=true;
                        }
                    }
                    if (!containsID){
                        try{

                            flugzeug = new Flugzeug(Integer.parseInt(flugProperties[6]),flugProperties[1],Double.parseDouble(flugProperties[7]));
                            start = new Flughafen(flugProperties[2]);
                            ende = new Flughafen(flugProperties[3]);
                            String[] abflugHilfe = flugProperties[4].split(" ");
                            String[] ankunftHilfe = flugProperties[5].split(" ");
                            date = abflugHilfe[0].split("\\.");
                            {
                                int i = 1;

                                while (abflugHilfe[i].equals("")){
                                    i++;
                                }

                                time = abflugHilfe[i].split(":");
                            }

                            abflug = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);
                            date = ankunftHilfe[0].split("\\.");
                            {
                                int i = 1;

                                while (abflugHilfe[i].equals("")){
                                    i++;
                                }

                                time = ankunftHilfe[i].split(":");
                            }

                            ankunft = LocalDateTime.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]), Integer.parseInt(date[0]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),0);


                            flug = new Flug(flugzeug, flugProperties[0],start,ende,0,abflug,ankunft);
                            flug.setPreisSitzplatz(Math.round(flug.getDauerFlug()*50*100)/100);
                        }catch (NumberFormatException f){
                            throw new NumberFormatException("Fehler in Datei Update_Flugliste.csv");
                        }
                        eingliedern.add(flug);
                    }
                }

                s=br.readLine();
            }

            input.close();
        }catch (IOException e){
            throw new FileNotFoundException("Datei Update_Flugliste.csv konnte nicht gelesen werden");
        }

        try{

            OutputStreamWriter clearFile = new OutputStreamWriter(new FileOutputStream(newFluege,false),"UTF-8");
            clearFile.write("Flugnummer;Fluggesellschaft;Abflugort;Ankunftsort;Ablugszeit;Ankunftszeit;Anzahl Sitzplätze;Gepäckskapazität [kg]\n");
            clearFile.close();
        }catch (IOException e){
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




            //fs.fluegeInSystemEingliedern();

            fs.addFlug(new Flug(new Flugzeug(34,"Lufthansa",3),"LH32145",new Flughafen("Bozen"),new Flughafen("München"),0,LocalDateTime.of(LocalDate.now(), LocalTime.of(14,00)),LocalDateTime.of(LocalDate.now(),LocalTime.of(16,00))));
            fs.inDateiSchreiben();


            //for (Flug i : fs.getFluegeListe()){


                //Flug i = fs.getFlug("LH3428");

            for(Flug i:fs.getFluegeListe()){
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

            System.out.println("-+-+-Davon buchbar:-+-+-");
            for(Flug i:fs.getBuchbareFluege()){
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

            {
                System.out.println("LH3458:");
                Flug i = fs.getFlug("LH3458");
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

