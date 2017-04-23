package it.fallmerayer.codingGmbH.projektFlughafen.model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjektFlughafen, created by Depian Thomas on 17.04.2017.
 * (C)opright 2017 Depian Thomas - All rights reserved.
 */
public class BenutzerprofilSpeicher {
    //Listen fuer die Benutzer
    private List<Anwender> anwenderListe = new LinkedList<>();
    private List<Angestellter> angestelltenListe = new LinkedList<>();
    private List<Administrator> administratorenListe = new LinkedList<>();

    //instance fuer Singelton
    private static BenutzerprofilSpeicher instance = null;

    //Privater Konstrutor, damit er von aussen nicht aufgerufen werden kann
    private BenutzerprofilSpeicher(){}

    public static BenutzerprofilSpeicher getInstance(){
        //wenn noch keine instanz existiert --> eine erstellen
        if (instance==null){
            instance = new BenutzerprofilSpeicher();
        }
        return instance;
    }

    //TODO: Passt Exception?
    /*
    * Abhaengig vom ubergebenenen Benutzerprofil, wird das Benutzerprofil zum entsprechenden gecasted
    * und in die entsrpechende Liste aufgenommen.
    * Zur Sicherheit wird auch die ID des jeweiligen Benutzerprofils uberpruft.
    * Sie muss den ID-Kreisen laut Pflichtenheft entsprechen.
    * Ist dies nicht der Fall und das uebergebene Benutzerprofil faellt durch das If-Raster
    * wird eine IllegalArgumentException geworfen
    * */
    public void addBenutzerprofil(Benutzerprofil benutzerprofil) throws IllegalArgumentException{
        if (benutzerprofil instanceof Administrator && benutzerprofil.getPID()<101){
            administratorenListe.add(((Administrator) benutzerprofil));
            return;
        }else if(benutzerprofil instanceof Angestellter && benutzerprofil.getPID()<1001){
            angestelltenListe.add(((Angestellter) benutzerprofil));
            return;
        }else if (benutzerprofil instanceof Anwender && benutzerprofil.getPID()>1000){
            anwenderListe.add(((Anwender) benutzerprofil));
            return;
        }
        throw new IllegalArgumentException("Benutzer faehlt durch das Raster");
    }

    //TODO
    /*
    * Es wird geschaut, ob das uebergebene Benutzerprofil in einer, abhaengig von der Instanz, entsprechenden
    * Liste gespeichert ist
    * */
    public boolean containsBenutzerprofil(Benutzerprofil benutzerprofil){
        if (benutzerprofil instanceof Anwender){
            return anwenderListe.contains(((Anwender) benutzerprofil));
        }else if(benutzerprofil instanceof Angestellter){
            return angestelltenListe.contains(((Angestellter) benutzerprofil));
        }else if (benutzerprofil instanceof Administrator){
            return administratorenListe.contains(((Administrator) benutzerprofil));
        }else {
            return false;
        }
    }

    /*
    * Ueberprueft, ob ein Benutzername schon vergeben ist oder nicht
    * */
    public boolean containsBenutzername(String benutzername){
        for (Anwender i : anwenderListe){
            if (i.getBenutzerName().equals(benutzername)){
                return true;
            }
        }

        for (Angestellter i : angestelltenListe){
            if (i.getBenutzerName().equals(benutzername)){
                return true;
            }
        }

        for (Administrator i : administratorenListe){
            if (i.getBenutzerName().equals(benutzername)){
                return true;
            }
        }

        return false;
    }

    //TODO
    /*
    * Es wird jenes Benutzerprofil zurueckgegeben, welches auf die den Uebergabeparamtern entsprechenden
    * Benutzernamen und Passwort entspricht.
    * Solle es keins geben, wird eine NichtImSystemRegistriertException geworfen
    * */
    public Benutzerprofil anmelden(String benutzerName, String passwort)throws NichtImSystemRegistriertException{
        for (Anwender i : anwenderListe){
            if (i.getBenutzerName().equals(benutzerName)){
                if (i.getPasswort().equals(passwort)){
                    i.setAngemeldet(true);
                    return i;
                }else {
                    throw new NichtImSystemRegistriertException(benutzerName);
                }
            }
        }

        for (Angestellter i : angestelltenListe){
            if (i.getBenutzerName().equals(benutzerName)){
                if (i.getPasswort().equals(passwort)){
                    i.setAngemeldet(true);
                    return i;
                }else {
                    throw new NichtImSystemRegistriertException(benutzerName);
                }
            }
        }


        for (Administrator i : administratorenListe){
            if (i.getBenutzerName().equals(benutzerName)){
                if (i.getPasswort().equals(passwort)){
                    i.setAngemeldet(true);
                    return i;
                }else {
                    throw new NichtImSystemRegistriertException(benutzerName);
                }
            }
        }
        throw new NichtImSystemRegistriertException(benutzerName);
    }

    //TODO: Counter Aktualiesieren
    /*
    * Mehode um aus Datei zu lesen
    * */
    public void ausDateiLesen() throws IOException, NumberFormatException, TooMuchAngestellteException {
        File input = new File(".\\files\\Benutzerprofile.csv");
        int maxAnwender = 0;
        int maxAngestellter = 0;
        int maxAdmin = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String s = br.readLine();
            s = br.readLine();
            String [] benutzerproperties;
            String [] gebdateHilfe;


            LocalDateTime geb;

            while (s!=null){
                benutzerproperties = s.split(";");

                gebdateHilfe = benutzerproperties[4].split("\\.");
                geb = LocalDateTime.of(Integer.parseInt(gebdateHilfe[2]),Integer.parseInt(gebdateHilfe[1]),Integer.parseInt(gebdateHilfe[0]),0,0);

                try {
                    if (Integer.parseInt(benutzerproperties[0]) < 101) {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAdmin) {
                            maxAdmin = Integer.parseInt(benutzerproperties[0]);
                        }
                        administratorenListe.add(new Administrator(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6]));
                    } else if (Integer.parseInt(benutzerproperties[0]) < 1001) {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAngestellter) {
                            maxAngestellter = Integer.parseInt(benutzerproperties[0]);
                        }
                        angestelltenListe.add(new Angestellter(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6]));
                    } else {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAnwender) {
                            maxAnwender = Integer.parseInt(benutzerproperties[0]);
                        }
                        anwenderListe.add(new Anwender(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6],Integer.parseInt(benutzerproperties[7])));
                    }
                } catch (TooMuchAngestellteException e) {
                    throw new TooMuchAngestellteException("Fehler in der Datenbank");
                }

                s=br.readLine();
            }
            Anwender.aktualisiereAnwenderCounter(++maxAnwender);
            Angestellter.aktualisiereAngestellterCounter(++maxAngestellter);
            Administrator.aktualisiereAdministratorCounter(++maxAdmin);
        }catch (IOException e){
            throw new IOException("Datei Benutzerprofile.csv konnte nicht gelesen werden");
        }catch (NumberFormatException f){
            throw new NumberFormatException("Fehler in Datei Benutzerprofile.csv");
        }
    }

    //TODO
    /*
    * MEthode um in die *.csv-Datei zu schreiben
    * */
    public void inDateiSchreiben() throws IOException{
        File output = new File(".\\files\\Benutzerprofile.csv");

        int maxBP = 0;

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(output))){
            bw.write("ID;Vorname;Nachname;E-Mail;Geb.;Benutzername;Passwort;ID.Nr.");
            StringBuilder toWrite = new StringBuilder();
            for (Administrator i : administratorenListe){
                toWrite.append(i.getPID() + ";");
                toWrite.append(i.getVorname() + ";");
                toWrite.append(i.getNachname() + ";");
                toWrite.append(i.getEmail() + ";");
                toWrite.append(i.getGeburtsDatum().getDayOfMonth() + ".");
                toWrite.append(i.getGeburtsDatum().getMonthValue() + ".");
                toWrite.append(i.getGeburtsDatum().getYear() + ";");
                toWrite.append(i.getBenutzerName() + ";");
                toWrite.append(i.getPasswort() + ";");
                toWrite.append("-1");
                bw.write(toWrite.toString());
                toWrite.setLength(0);

            }

            toWrite.setLength(0);

            for (Angestellter i : angestelltenListe){
                toWrite.append(i.getPID() + ";");
                toWrite.append(i.getVorname() + ";");
                toWrite.append(i.getNachname() + ";");
                toWrite.append(i.getEmail() + ";");
                toWrite.append(i.getGeburtsDatum().getDayOfMonth() + ".");
                toWrite.append(i.getGeburtsDatum().getMonthValue() + ".");
                toWrite.append(i.getGeburtsDatum().getYear() + ";");
                toWrite.append(i.getBenutzerName() + ";");
                toWrite.append(i.getPasswort() + ";");
                toWrite.append("-1");
                bw.write(toWrite.toString());
                toWrite.setLength(0);

                bw.write(toWrite.toString());
                toWrite.setLength(0);
            }
            toWrite.setLength(0);

            for (Anwender i : anwenderListe){
                toWrite.append(i.getPID() + ";");
                toWrite.append(i.getVorname() + ";");
                toWrite.append(i.getNachname() + ";");
                toWrite.append(i.getEmail() + ";");
                toWrite.append(i.getGeburtsDatum().getDayOfMonth() + ".");
                toWrite.append(i.getGeburtsDatum().getMonthValue() + ".");
                toWrite.append(i.getGeburtsDatum().getYear() + ";");
                toWrite.append(i.getBenutzerName() + ";");
                toWrite.append(i.getPasswort() + ";");
                toWrite.append(i.getPasswort() + ";");
                toWrite.append(i.getIdentitaetsNummer());
                bw.write(toWrite.toString());
                toWrite.setLength(0);

                bw.write(toWrite.toString());
                toWrite.setLength(0);
            }
        }

    }

    //Getter
    //Setter sind aus logischer Sicht nicht von Noeten
    public List<Anwender> getAnwenderListe() {
        return anwenderListe;
    }

    public List<Angestellter> getAngestelltenListe() {
        return angestelltenListe;
    }

    public List<Administrator> getAdministratorenListe() {
        return administratorenListe;
    }


}
