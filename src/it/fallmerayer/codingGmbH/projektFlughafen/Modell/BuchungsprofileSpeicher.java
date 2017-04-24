package it.fallmerayer.codingGmbH.projektFlughafen.Modell;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjektFlughafen, created by Depian Thomas on 15.04.2017.
 * (C)opright 2017 Depian Thomas - All rights reserved.
 */
public class BuchungsprofileSpeicher {
    //Attribute
    private List<Buchungsprofil> buchungsprofilListe = new LinkedList<>();
    private static int buchungsCounter = 0;
    private static BuchungsprofileSpeicher instance=null;

    //Privater Konstruktor fuer Singleton
    private BuchungsprofileSpeicher(){}

    //Instance falls noetig erstellen und dann zurueckgeben --> Singleton
    public static BuchungsprofileSpeicher getInstance(){
        if (instance==null){
            instance=new BuchungsprofileSpeicher();
        }
        return instance;
    }

    //Der Buchungscounter wird aktualiesiert
    public static void aktualiesiereBuchungsCounter(int wert){
        buchungsCounter=wert;
    }

    /*
    * Alle Buchungsprofile die von dem der PID entsprechenden Benutzer
    * getaetigt wurden, werden in eine Liste gespeichert und zurueckgegeben
    * */

    public List<Buchungsprofil> getBuchungsprofile(int PID){
        List<Buchungsprofil> toReturn = new LinkedList<>();

        for (Buchungsprofil i: buchungsprofilListe){
            if (i instanceof BuchungsprofilAnwender){
                if (((BuchungsprofilAnwender) i).getAnwenderPID() == PID){
                    toReturn.add(i);
                }
            }else if (i instanceof BuchungsprofilAngestellter){
                if (((BuchungsprofilAngestellter) i).getAngestellterPID() == PID){
                    toReturn.add(i);
                }
            }
        }
        return toReturn;
    }

    //TODO: Buchungscounter erhöhen?
    //Buchung wird hinzugefuegt
    public void addBuchungsprofil(Buchungsprofil buchungsprofil){
        buchungsprofilListe.add(buchungsprofil);
        buchungsCounter++;

        //TODO: Skript starten
    }

    //Eine der ID entsprechenden Buchung wird geloescht
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
            buchungsCounter--;
            return buchungsprofilListe.remove(toRemove);
        }
    }

    //TODO:
    //Buchung wird geanert
    public boolean aendereBuchungsprofil(int buchungsID, double gepaeckGewicht) throws FlugNichtBuchbarException {
        for(Buchungsprofil i: buchungsprofilListe){
            //Entsprechende Buchung wird gesucht
            if (i.getBuchungsID()==buchungsID){
                FluegeSpeicher fluegeSpeicher = FluegeSpeicher.getInstance();
                //Der zur Buchung gehoerende Flug wird gesucht
                Flug flug = fluegeSpeicher.getFlug(i.getFlugNummer());

                //Wenn der Flug noch nicht angehoben ist darf das Gepaeckgewicht geandert werden
                if (flug.getAbflugZeit().isBefore(LocalDateTime.now())){
                    //Ist noch genug Platz? --> Wenn ja, dann aendern
                    if (flug.getFreiesGepaeckGewicht()>=gepaeckGewicht){
                        //neues gepaeckgewicht = aktuelles gepaeckgewicht - das zuvor der Buchung korrispondierenden Gepaeckgewicht + das uebrgebene Gewicht
                        flug.setZaehlerGepaeckGewicht(flug.getZaehlerGepaeckGewicht()-i.getGepaeckGewicht()+gepaeckGewicht);
                        i.setGepaeckGewicht(gepaeckGewicht);
                        return true;
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    //TODO
    //Aus Datei lesen
    //Exceptions werden weitergebenen, da die Behandlung hier nicht sinnvoll ist
    public void ausDateiLesen() throws IOException, NumberFormatException, FlugNichtBuchbarException{
        File input = new File(".\\files\\Buchungsprofile.csv");
        //Zaehler der die groesste BuchungsID zaehlt
        int maxBP = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            //TODO: Erste Zeile ueberspringen?
            //Erste Zeile (Titel) wird uebersprungen
            String s = br.readLine();
            s = br.readLine();
            String [] buchungsproperties;
            Buchungsprofil buchungsprofil = null;
            List<Mitflieger> mitfliegerList = new LinkedList<>();
            while (s!=null){
                //Jedes Arrayfeld entspricht einer Eigenschaft einer Buchung
                buchungsproperties = s.split(";");
                if (Integer.parseInt(buchungsproperties[0])>maxBP){
                    maxBP=Integer.parseInt(buchungsproperties[0]);
                }

                /*
                * Die Mitflieger werden in einem einzigen Feld des Arrays gespeichert
                * Die Mitflieger werden mit && voneinander getrennt
                * Die Eigenschaften eines Mitfliegers werden mit ## voneinander getrennt
                * */
                {
                    String[] mitflieger = buchungsproperties[4].split("&&");
                    String[] mitfliegerProperties = null;
                    LocalDate geb;
                    for (String i : mitflieger){
                        mitfliegerProperties = i.split("##");

                        //TODO: löschen
                        System.out.println("\tVorname: " + mitfliegerProperties[0]);
                        System.out.println("\tNachname: " + mitfliegerProperties[1]);
                        System.out.println("\tE-Mail: " + mitfliegerProperties[2]);
                        String[] dateProperties = mitfliegerProperties[3].split("\\.");
                        geb = LocalDate.of(Integer.parseInt(dateProperties[2]),Integer.parseInt(dateProperties[1]),Integer.parseInt(dateProperties[0]));
                        System.out.println("\tGeburtsdatum: " + geb.toString());
                        System.out.println("\tNummer: " + mitfliegerProperties[4]);

                        //TODO: LocalDateTime fi an Gebutstog??? --> Besser LocalDate
                        mitfliegerList.add(new Mitflieger(mitfliegerProperties[0],mitfliegerProperties[1], mitfliegerProperties[2], LocalDateTime.of(Integer.parseInt(dateProperties[2]),Integer.parseInt(dateProperties[1]),Integer.parseInt(dateProperties[0]),0,0),Integer.parseInt(mitfliegerProperties[4])));

                    }
                }
                //Wenn gebuchtVon-Zelle in der *.csv-Datei < 1001 wurde der Flug von einem Angestellten gebucht, sonst von einem Anwender
                if (Integer.parseInt(buchungsproperties[3])<1001){
                    buchungsprofil=new BuchungsprofilAngestellter(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[3]),mitfliegerList);
                }else {
                    buchungsprofil=new BuchungsprofilAnwender(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[3]),mitfliegerList);
                }
                buchungsprofil.setBuchungsID(Integer.parseInt(buchungsproperties[0]));
                buchungsprofilListe.add(buchungsprofil);
                mitfliegerList.clear();
                s = br.readLine();
            }
        }catch (IOException e){
            throw new IOException("Datei Buchungsprofile.csv konnte nicht gelesen werden");
        }catch (NumberFormatException f){
            throw new NumberFormatException("Formatierungsfehler in Buchungsprofile.csv");
        } catch (FlugNichtBuchbarException e) {
            throw new FlugNichtBuchbarException("Fehler in der Datei. Buchungen koennen nicht ins System geladen werden");
        }
        BuchungsprofileSpeicher.aktualiesiereBuchungsCounter(++maxBP);
    }

    //TODO
    //In Datei schreiben
    public void inDateiSchreiebn()throws IOException{
        File output = new File(".\\files\\Buchungsprofile.csv");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(output))){
            //mit Stringbuilder wird eine Zeile der *.csv-Datei Schritt fuer Schritt aufgebaut und hinausgeschrieben
            bw.write("ID;Flugnr;GepaeckGewicht;GebuchtVon;Mitflieger/Passagiere");
            StringBuilder toWrite = new StringBuilder();
            StringBuilder mitflieger = new StringBuilder();
            for (Buchungsprofil i : buchungsprofilListe){
                toWrite.append(i.getBuchungsID() + ";");
                toWrite.append(i.getFlugNummer() + ";");
                toWrite.append(i.getGepaeckGewicht() + ";");
                if (i instanceof BuchungsprofilAnwender){
                    toWrite.append(((BuchungsprofilAnwender) i).getAnwenderPID() + ";");
                    for (Mitflieger j : ((BuchungsprofilAnwender) i).getMitfliegerListe()){
                        mitflieger.append(j.getVorname() + "##");
                        mitflieger.append(j.getNachname() + "##");
                        mitflieger.append(j.getEmail() + "##");
                        mitflieger.append(j.getGeburtsDatum().getDayOfMonth() + ".");
                        mitflieger.append(j.getGeburtsDatum().getMonthValue() + ".");
                        mitflieger.append(j.getGeburtsDatum().getYear() + "##");
                        mitflieger.append(j.getIdentitaetsNummer() + "&&");
                    }
                    mitflieger.setLength(mitflieger.length()-2);
                    toWrite.append(mitflieger.toString());
                    mitflieger.setLength(0);
                }else if(i instanceof BuchungsprofilAngestellter){
                    toWrite.append(((BuchungsprofilAngestellter) i).getAngestellterPID() + ";");
                    for (Mitflieger j : ((BuchungsprofilAngestellter) i).getPassagierListe()){
                        mitflieger.append(j.getVorname() + "##");
                        mitflieger.append(j.getNachname() + "##");
                        mitflieger.append(j.getEmail() + "##");
                        mitflieger.append(j.getGeburtsDatum().getDayOfMonth() + ".");
                        mitflieger.append(j.getGeburtsDatum().getMonthValue() + ".");
                        mitflieger.append(j.getGeburtsDatum().getYear() + "##");
                        mitflieger.append(j.getIdentitaetsNummer() + "&&");
                    }
                    mitflieger.setLength(mitflieger.length()-2);
                    toWrite.append(mitflieger.toString());
                    mitflieger.setLength(0);
                }



                bw.write(toWrite.toString());
                toWrite.setLength(0);

            }


/*
            String s = br.readLine();
            s = br.readLine();
            String [] buchungsproperties;
            Buchungsprofil buchungsprofil = null;
            List<Mitflieger> mitfliegerList = new LinkedList<>();
            while (s!=null){
                buchungsproperties = s.split(";");
                if (Integer.parseInt(buchungsproperties[0])>maxBP){
                    maxBP=Integer.parseInt(buchungsproperties[0]);
                }

                {
                    String[] mitflieger = buchungsproperties[4].split("&&");
                    String[] mitfliegerProperties = null;
                    LocalDate geb;
                    for (String i : mitflieger){
                        mitfliegerProperties = i.split("##");

                        System.out.println("\tVorname: " + mitfliegerProperties[0]);
                        System.out.println("\tNachname: " + mitfliegerProperties[1]);
                        System.out.println("\tE-Mail: " + mitfliegerProperties[2]);
                        String[] dateProperties = mitfliegerProperties[3].split("\\.");
                        geb = LocalDate.of(Integer.parseInt(dateProperties[2]),Integer.parseInt(dateProperties[1]),Integer.parseInt(dateProperties[0]));
                        System.out.println("\tGeburtsdatum: " + geb.toString());
                        System.out.println("\tNummer: " + mitfliegerProperties[4]);

                        //TODO: Attribute setzten
                        mitfliegerList.add(new Mitflieger());

                    }
                }
                if (Integer.parseInt(buchungsproperties[3])<1001){
                    buchungsprofil=new BuchungsprofilAngestellter(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[3]),mitfliegerList);
                }else {
                    buchungsprofil=new BuchungsprofilAnwender(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[3]),mitfliegerList);
                }
                buchungsprofilListe.add(buchungsprofil);
                mitfliegerList.clear();
                s = br.readLine();
            }*/
        }catch (IOException e){
            throw new IOException("Datei Buchungsprofile.csv konnte nicht gelesen werden");
        }

    }

    //Getter
    public List<Buchungsprofil> getBuchungsprofilListe() {
        return buchungsprofilListe;
    }

    public static int getBuchungsCounter() {
        return buchungsCounter;
    }

    public static void setBuchungsCounter(int buchungsCounter) {
        BuchungsprofileSpeicher.buchungsCounter = buchungsCounter;
    }

    public static void main(String[] args) {
        BuchungsprofileSpeicher bps = BuchungsprofileSpeicher.getInstance();
        try {
            bps.ausDateiLesen();
        }catch (IOException e){
            e.printStackTrace();
        }catch (FlugNichtBuchbarException f){
            f.printStackTrace();
        }
    }
}
