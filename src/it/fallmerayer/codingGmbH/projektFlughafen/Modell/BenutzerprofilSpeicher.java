package it.fallmerayer.codingGmbH.projektFlughafen.Modell;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


    /*
    * Mehode um aus Datei zu lesen
    * */
    public void ausDateiLesen() throws IOException, NumberFormatException {
        File input = new File(".\\files\\Benutzerprofile.csv");
        int maxAnwender = 1000;
        int maxAngestellter = 100;
        int maxAdmin = 0;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input),"UTF-8"))){
            String s = br.readLine();
            s = br.readLine();
            String [] benutzerproperties;
            String [] gebdateHilfe;
            String [] wunschlisteHilfe;
            String [] wunschlistenproperties;
            Anwender anwender;


            LocalDateTime geb;

            while (s!=null){
                if (!s.equals("")){
                    benutzerproperties = s.split(";");
                    gebdateHilfe = benutzerproperties[4].split("\\.");
                    geb = LocalDateTime.of(Integer.parseInt(gebdateHilfe[2]),Integer.parseInt(gebdateHilfe[1]),Integer.parseInt(gebdateHilfe[0]),0,0);


                    if (Integer.parseInt(benutzerproperties[0]) < 101) {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAdmin) {
                            maxAdmin = Integer.parseInt(benutzerproperties[0]);
                        }

                        administratorenListe.add(new Administrator(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6],Integer.parseInt(benutzerproperties[0])));
                    } else if (Integer.parseInt(benutzerproperties[0]) < 1001) {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAngestellter) {
                            maxAngestellter = Integer.parseInt(benutzerproperties[0]);
                        }

                        angestelltenListe.add(new Angestellter(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6],Integer.parseInt(benutzerproperties[0])));
                    } else {
                        if (Integer.parseInt(benutzerproperties[0]) > maxAnwender) {
                            maxAnwender = Integer.parseInt(benutzerproperties[0]);
                        }

                        anwender = new Anwender(benutzerproperties[1], benutzerproperties[2], benutzerproperties[3], geb, benutzerproperties[5], benutzerproperties[6],Integer.parseInt(benutzerproperties[0]),Integer.parseInt(benutzerproperties[7]));
                        if (!benutzerproperties[8].equals("-1") | benutzerproperties.length<7){
                            wunschlisteHilfe = benutzerproperties[8].split("&&");
                            for (String j: wunschlisteHilfe){
                                wunschlistenproperties = j.split("##");
                                anwender.legeFlugInWunschliste(wunschlistenproperties[0],Integer.parseInt(wunschlistenproperties[1]),Double.parseDouble(wunschlistenproperties[2]));
                            }
                        }
                        anwenderListe.add(anwender);
                    }
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


    /*
    * Methode um in die *.csv-Datei zu schreiben
    * */
    public void inDateiSchreiben() throws IOException{
        File output = new File(".\\files\\Benutzerprofile.csv");

        int maxBP = 0;

        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output),"UTF-8"))){
            bw.write("ID;Vorname;Nachname;E-Mail;Geb.;Benutzername;Passwort;ID.Nr.;Wunschliste" + System.lineSeparator());
            StringBuilder toWrite = new StringBuilder();
            StringBuilder wunschliste = new StringBuilder();
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
                toWrite.append("-1");
                toWrite.append(System.lineSeparator());
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
                toWrite.append("-1;");
                toWrite.append("-1");
                toWrite.append(System.lineSeparator());
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
                toWrite.append(i.getIdentitaetsNummer() + ";");

                if (i.getWunschliste().isEmpty()){
                    toWrite.append("-1");
                }else {
                    for (WunschlistenEintrag j: i.getWunschliste()){
                        wunschliste.append(j.getFlugNummer() + "##");
                        wunschliste.append(j.getAnzahlPassagiere() + "##");
                        wunschliste.append(j.getGepaeckGewicht() + "&&");
                    }
                    wunschliste.setLength(wunschliste.length()-2);
                    toWrite.append(wunschliste.toString());
                }




                toWrite.append(System.lineSeparator());
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


    public static void main(String[] args) {
        BenutzerprofilSpeicher bps = BenutzerprofilSpeicher.getInstance();
        Anwender a = new Anwender("Gibs","mi","hoi.du@gmailcom",LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)),"HoiDU","hoooo",12345678);


        try {
            FluegeSpeicher.getInstance().ausDateiLesen();



            bps.ausDateiLesen();
            //bps.addBenutzerprofil(a);
            a.legeFlugInWunschliste("KL",0,0);
            a.legeFlugInWunschliste("EJ1222",1,0);

            /*try {
                Administrator admin = ((Administrator) bps.anmelden("cooloAdmin", "tadaa"));
                if (admin.isAngemeldet()){
                    System.out.println(admin.getVorname() + " " + admin.getNachname() + " ist angemeldet.");
                }
            } catch (NichtImSystemRegistriertException e) {
                e.printStackTrace();
            }*/
            /*bps.addBenutzerprofil(new Administrator("admin","test","admmin@larcherairline.com",LocalDateTime.of(1999,3,3,0,0),"cooloAdmin","tadaa"));
            bps.addBenutzerprofil(new Angestellter("angestellter","test","angestellter@larcherairline.com",LocalDateTime.of(1999,3,5,0,0),"cooloAngestellter","ha"));
            bps.addBenutzerprofil(new Angestellter("angestellter2","test2","angestellter2@larcherairline.com",LocalDateTime.of(1996,3,5,0,0),"cooloAngestellter2","ha"));
            bps.addBenutzerprofil(new Angestellter("angestellter3","test3","angestellter3@larcherairline.com",LocalDateTime.of(1997,3,5,0,0),"cooloAngestellter3","ha"));
            bps.addBenutzerprofil(new Angestellter("angestellter4","test4","angestellter4@larcherairline.com",LocalDateTime.of(1998,3,5,0,0),"cooloAngestellter4","ha"));*/
            //bps.inDateiSchreiben();

            //System.out.println(bps.containsBenutzerprofil(a));
            //System.out.println(bps.containsBenutzerprofil((new Angestellter("angestellter","test","angestellter@larcherairline.com",LocalDateTime.of(1999,3,5,0,0),"cooloAngestellter01","ha"))));
            System.out.println("---Administratoren---");
            for (Administrator i : bps.administratorenListe){
                System.out.println(i.getPID());
                System.out.println(i.getVorname());
                System.out.println(i.getNachname());
                System.out.println(i.getEmail());
                System.out.println(i.getGeburtsDatum());
                System.out.println(i.getBenutzerName());
                System.out.println(i.getPasswort());
                System.out.println("-1");
                System.out.println("-1");
            }

            System.out.println("---Angestelltern---");
            for (Angestellter i : bps.angestelltenListe){
                System.out.println(i.getPID());
                System.out.println(i.getVorname());
                System.out.println(i.getNachname());
                System.out.println(i.getEmail());
                System.out.println(i.getGeburtsDatum());
                System.out.println(i.getBenutzerName());
                System.out.println(i.getPasswort());
                System.out.println("-1");
                System.out.println("-1");
            }

            System.out.println("---Anwender---");
            for (Anwender i : bps.anwenderListe){
                System.out.println(i.getPID());
                System.out.println(i.getVorname());
                System.out.println(i.getNachname());
                System.out.println(i.getEmail());
                System.out.println(i.getGeburtsDatum());
                System.out.println(i.getBenutzerName());
                System.out.println(i.getPasswort());
                System.out.println(i.getIdentitaetsNummer());
                System.out.println("-Wunschliste-");
                for (WunschlistenEintrag j: i.getWunschliste()){
                    System.out.println(j.getFlugNummer());
                    System.out.println(j.getAnzahlPassagiere());
                    System.out.println(j.getGepaeckGewicht());
                    System.out.println("---");
                }
                System.out.println("++++");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
