package It.fallmerayer.codingGmbH.projektFlughafen.Model;



import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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


    //Buchung wird hinzugefuegt
    public void addBuchungsprofil(Buchungsprofil buchungsprofil) throws NoSuchElementException, IOException {
        buchungsprofilListe.add(buchungsprofil);
        BenutzerprofilSpeicher bps = BenutzerprofilSpeicher.getInstance();
        FluegeSpeicher fs = FluegeSpeicher.getInstance();
        Benutzerprofil benutzerprofil = null;
        StringBuilder command = new StringBuilder();
        command.append("cmd /c start PowerShell.exe -ExecutionPolicy Unrestricted -File .\\files\\email_versenden_buchung.ps1");

        if (buchungsprofil instanceof BuchungsprofilAnwender){
            for (Benutzerprofil i : bps.getAnwenderListe()){
                if (i.getPID() == ((BuchungsprofilAnwender) buchungsprofil).getAnwenderPID()){
                    benutzerprofil = i;
                }
            }
            if (benutzerprofil == null){
                throw new NoSuchElementException("Kein Anwender mit der ID " + ((BuchungsprofilAnwender) buchungsprofil).getAnwenderPID() + "  bekannt");
            }
            command.append(" -zu \"" + benutzerprofil.getEmail() + "\"");
            command.append(" -vorname \"" + benutzerprofil.getVorname() + "\"");
            command.append(" -nachname \"" + benutzerprofil.getNachname() + "\"");
            command.append(" -startFlughafen \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getStartFlughafen().getStadt() + "\"");
            command.append(" -zielFlughafen \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getZielFlughafen().getStadt() + "\"");
            if (fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute()<10){
                command.append(" -abflugZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getHour() + ":0" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute() + "\"");
            }else {
                command.append(" -abflugZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getHour() + ":" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute() + "\"");
            }

            if (fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute()<10){
                command.append(" -ankunftZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getHour() + ":0" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute() + "\"");
            }else {
                command.append(" -ankunftZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getHour() + ":" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute() + "\"");
            }

            command.append(" -abflugsDatum \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getDayOfMonth() + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMonthValue() + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getYear() +"\"");
            command.append(" -preisBuchung \"" + buchungsprofil.calculatePreis() + "\"");
            command.append(" -gepaeckGewicht \"" + buchungsprofil.getGepaeckGewicht() + "\"");
            command.append(" -flugGesellschaft \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getFlugzeug().getFlugGesellschaft() + "\"");
            command.append(" -buchungsID \"" + buchungsprofil.getBuchungsID() + "\"");

        }

        if (buchungsprofil instanceof BuchungsprofilAngestellter){
            if (((BuchungsprofilAngestellter) buchungsprofil).getAngestellterPID() < 101){
                for (Benutzerprofil i : bps.getAdministratorenListe()){
                    if (i.getPID() == ((BuchungsprofilAngestellter) buchungsprofil).getAngestellterPID()){
                        benutzerprofil = i;
                    }
                }
            } else {
                for (Benutzerprofil i : bps.getAngestelltenListe()){
                    if (i.getPID() == ((BuchungsprofilAngestellter) buchungsprofil).getAngestellterPID()){
                        benutzerprofil = i;
                    }
                }
            }
            if (benutzerprofil == null){
                throw new NoSuchElementException("Kein Angestellter mit der ID " + ((BuchungsprofilAngestellter) buchungsprofil).getAngestellterPID() + " bekannt");
            }
            command.append(" -zu \"" + ((BuchungsprofilAngestellter) buchungsprofil).getPassagierListe().get(0).getEmail() + "\"");
            command.append(" -vorname \"" + ((BuchungsprofilAngestellter) buchungsprofil).getPassagierListe().get(0).getVorname() + "\"");
            command.append(" -nachname \"" + ((BuchungsprofilAngestellter) buchungsprofil).getPassagierListe().get(0).getNachname() + "\"");
            command.append(" -startFlughafen \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getStartFlughafen().getStadt() + "\"");
            command.append(" -zielFlughafen \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getZielFlughafen().getStadt() + "\"");
            if (fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute()<10){
                command.append(" -abflugZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getHour() + ":0" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute() + "\"");
            }else {
                command.append(" -abflugZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getHour() + ":" + fs.getFlug(buchungsprofil.getFlugNummer()).getAbflugZeit().getMinute() + "\"");
            }

            if (fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute()<10){
                command.append(" -ankunftZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getHour() + ":0" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute() + "\"");
            }else {
                command.append(" -ankunftZeit \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getHour() + ":" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMinute() + "\"");
            }

            command.append(" -abflugsDatum \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getDayOfMonth() + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getMonthValue() + fs.getFlug(buchungsprofil.getFlugNummer()).getAnkunftZeit().getYear() +"\"");
            command.append(" -preisBuchung \"" + buchungsprofil.calculatePreis() + "\"");
            command.append(" -gepaeckGewicht \"" + buchungsprofil.getGepaeckGewicht() + "\"");
            command.append(" -flugGesellschaft \"" + fs.getFlug(buchungsprofil.getFlugNummer()).getFlugzeug().getFlugGesellschaft() + "\"");
            command.append(" -buchungsID \"" + buchungsprofil.getBuchungsID() + "\"");
        }

        try{
            Runtime.getRuntime().exec(command.toString());
        }catch(IOException e){
            throw new IOException("PowerShell-Skript konnte nicht gestartet werden");
        }
    }


    //Eine der ID entsprechenden Buchung wird geloescht
    public boolean loescheBuchungsprofil(int buchungsID){
        int toRemove = -1;
        int index = 0;
        for (Buchungsprofil i: buchungsprofilListe){
            if (i.getBuchungsID()==buchungsID){
                toRemove=index;
            }
            index++;
        }

        if (toRemove==-1){
            return false;
        }else {
            buchungsCounter--;
            return buchungsprofilListe.remove(buchungsprofilListe.get(toRemove));
        }
    }

    public Buchungsprofil getBuchungsprofil(int buchungsID) throws NoSuchElementException{
        for (Buchungsprofil i : buchungsprofilListe) {
            if (i.getBuchungsID() == buchungsID){
                return i;
            }
        }
        throw new NoSuchElementException("Keine Buchung mit der ID " + buchungsID + " bekannt");
    }


    //Buchung wird geandert
    public boolean aendereBuchungsprofil(int buchungsID, double gepaeckGewicht) throws FlugNichtBuchbarException, IOException {
        for(Buchungsprofil i: buchungsprofilListe){
            //Entsprechende Buchung wird gesucht
            if (i.getBuchungsID()==buchungsID){
                FluegeSpeicher fluegeSpeicher = FluegeSpeicher.getInstance();
                //Der zur Buchung gehoerende Flug wird gesucht
                Flug flug = fluegeSpeicher.getFlug(i.getFlugNummer());

                //Wenn der Flug noch nicht angehoben ist darf das Gepaeckgewicht geandert werden
                if (flug.getAbflugZeit().isAfter(LocalDateTime.now())){
                    //Ist noch genug Platz? --> Wenn ja, dann aendern
                    if (flug.getFreiesGepaeckGewicht()+i.getGepaeckGewicht()>=gepaeckGewicht){
                        //neues gepaeckgewicht = aktuelles gepaeckgewicht - das zuvor der Buchung korrispondierenden Gepaeckgewicht + das uebrgebene Gewicht
                        flug.setZaehlerGepaeckGewicht(flug.getZaehlerGepaeckGewicht()-i.getGepaeckGewicht()+gepaeckGewicht);



                        BenutzerprofilSpeicher bps = BenutzerprofilSpeicher.getInstance();
                        FluegeSpeicher fs = FluegeSpeicher.getInstance();
                        Benutzerprofil benutzerprofil=null;
                        StringBuilder command = new StringBuilder();
                        command.append("cmd /c start PowerShell.exe -ExecutionPolicy Unrestricted -File .\\files\\email_versenden_gepaeckgewicht_aenderung.ps1");


                        if (i instanceof BuchungsprofilAnwender) {
                            for (Benutzerprofil j : bps.getAnwenderListe()) {
                                if (((BuchungsprofilAnwender) i).getAnwenderPID() == j.getPID()) {
                                    benutzerprofil = j;
                                }
                            }
                            if (benutzerprofil == null) {
                                throw new NoSuchElementException("Kein Anwender mit der ID " + ((BuchungsprofilAnwender) i).getAnwenderPID() + "  bekannt");
                            }
                            command.append(" -zu \"" + benutzerprofil.getEmail() + "\"");
                            command.append(" -vorname \"" + benutzerprofil.getVorname() + "\"");
                            command.append(" -nachname \"" + benutzerprofil.getNachname() + "\"");
                            command.append(" -buchungsID \"" + i.getBuchungsID() + "\"");
                            command.append(" -vorherigesGepaeckGewicht \"" + i.getGepaeckGewicht() + "\"");
                            command.append(" -neuesGepaeckGewicht \"" + gepaeckGewicht + "\"");
                            System.out.println("E-Mail wird an " + benutzerprofil.getEmail() + "geschickt");
                        }

                        if (i instanceof BuchungsprofilAngestellter){
                            for (Benutzerprofil j : bps.getAngestelltenListe()){
                                if (((BuchungsprofilAngestellter) i).getAngestellterPID() == j.getPID()){
                                    benutzerprofil = j;
                                }
                            }
                            if (benutzerprofil == null){
                                throw new NoSuchElementException("Kein Anwender mit der ID " + ((BuchungsprofilAngestellter) i).getAngestellterPID() + "  bekannt");
                            }

                            command.append(" -zu \"" + ((BuchungsprofilAngestellter) i).getPassagierListe().get(0).getEmail() + "\"");
                            command.append(" -vorname \"" + ((BuchungsprofilAngestellter) i).getPassagierListe().get(0).getVorname() + "\"");
                            command.append(" -nachname \"" + ((BuchungsprofilAngestellter) i).getPassagierListe().get(0).getNachname() + "\"");
                            command.append(" -buchungsID \"" + i.getBuchungsID() + "\"");
                            command.append(" -vorherigesGepaeckGewicht \"" + i.getGepaeckGewicht() + "\"");
                            command.append(" -neuesGepaeckGewicht \"" + gepaeckGewicht + "\"");
                        }
                        try{
                            Runtime.getRuntime().exec(command.toString());
                        }catch(IOException e){
                            throw new IOException("PowerShell-Skript konnte nicht gestartet werden");
                        }

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


    //Aus Datei lesen
    //Exceptions werden weitergebenen, da die Behandlung hier nicht sinnvoll ist
    public void ausDateiLesen() throws IOException, NumberFormatException{
        File input = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "Buchungsprofile.csv");
        //File input = new File(".\\files\\Buchungsprofile.csv");
        //Zaehler der die groesste BuchungsID zaehlt
        int maxBP = 0;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input),"UTF-8"))){

            //Erste Zeile (Titel) wird uebersprungen
            String s = br.readLine();
            s = br.readLine();
            String [] buchungsproperties;
            Buchungsprofil buchungsprofil = null;
            List<Mitflieger> mitfliegerList = new LinkedList<>();
            while (s!=null){
                if (!s.equals("")){
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

                    if (buchungsproperties.length>4){
                        {
                            String[] mitflieger = buchungsproperties[4].split("&&");
                            String[] mitfliegerProperties = null;
                            for (String i : mitflieger){
                                mitfliegerProperties = i.split("##");

                                String[] dateProperties = mitfliegerProperties[3].split("\\.");

                                mitfliegerList.add(new Mitflieger(mitfliegerProperties[0],mitfliegerProperties[1], mitfliegerProperties[2], LocalDateTime.of(Integer.parseInt(dateProperties[2]),Integer.parseInt(dateProperties[1]),Integer.parseInt(dateProperties[0]),0,0),Integer.parseInt(mitfliegerProperties[4])));

                            }
                        }
                    }


                    //Wenn gebuchtVon-Zelle in der *.csv-Datei < 1001 wurde der Flug von einem Angestellten gebucht, sonst von einem Anwender
                    if (Integer.parseInt(buchungsproperties[3])<1001){
                        buchungsprofil=new BuchungsprofilAngestellter(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[0]),mitfliegerList,Integer.parseInt(buchungsproperties[3]));
                    }else {
                        buchungsprofil=new BuchungsprofilAnwender(buchungsproperties[1],Double.parseDouble(buchungsproperties[2]),Integer.parseInt(buchungsproperties[0]),mitfliegerList,Integer.parseInt(buchungsproperties[3]));
                    }
                    buchungsprofil.setBuchungsID(Integer.parseInt(buchungsproperties[0]));
                    buchungsprofilListe.add(buchungsprofil);

                    mitfliegerList = new LinkedList<>();
                }
                s = br.readLine();
            }
        }catch (IOException e){
            throw new IOException("Datei Buchungsprofile.csv konnte nicht gelesen werden");
        }catch (NumberFormatException f){
            throw new NumberFormatException("Formatierungsfehler in Buchungsprofile.csv");
        }
        BuchungsprofileSpeicher.aktualiesiereBuchungsCounter(++maxBP);
    }


    //In Datei schreiben
    public void inDateiSchreiebn()throws IOException{
        //File output = new File(".\\files\\Buchungsprofile.csv");
        File output = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "Buchungsprofile.csv");

        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output),"UTF-8"))){
            //mit Stringbuilder wird eine Zeile der *.csv-Datei Schritt fuer Schritt aufgebaut und hinausgeschrieben
            bw.write("ID;Flugnr;GepaeckGewicht;GebuchtVon;Mitflieger/Passagiere"+System.lineSeparator());
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
                    if (mitflieger.length()>2){
                        mitflieger.setLength(mitflieger.length()-2);
                    }
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
                    if (mitflieger.length()>2){
                        mitflieger.setLength(mitflieger.length()-2);
                    }
                    toWrite.append(mitflieger.toString());
                    mitflieger.setLength(0);
                }


                toWrite.append(System.lineSeparator());
                bw.write(toWrite.toString());
                toWrite.setLength(0);

            }
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

        /*try {
            BenutzerprofilSpeicher.getInstance().ausDateiLesen();
            FluegeSpeicher.getInstance().ausDateiLesen();
            BuchungsprofileSpeicher.getInstance().addBuchungsprofil(new BuchungsprofilAnwender("EJ1222",0,10002));
        } catch (IOException | FlugNichtBuchbarException | TooMuchAngestellteException e) {
            e.printStackTrace();
        }*/
        FluegeSpeicher fs = FluegeSpeicher.getInstance();
        BuchungsprofileSpeicher bps = BuchungsprofileSpeicher.getInstance();
        BenutzerprofilSpeicher benutzerprofilSpeicher = BenutzerprofilSpeicher.getInstance();
        List<Mitflieger> mitfliegerList = new LinkedList<>();
        mitfliegerList.add(new Mitflieger("Test","User","thomas.depian99@gmail.com",LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)),2015));
        mitfliegerList.add(new Mitflieger("Testi","User","test.user@gmail.com",LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0)),2018));


        try {
            fs.ausDateiLesen();
            bps.ausDateiLesen();
            benutzerprofilSpeicher.ausDateiLesen();
            //System.out.println(BuchungsprofileSpeicher.getBuchungsCounter());
            //bps.addBuchungsprofil(new BuchungsprofilAnwender("EJ1222",0,10002));
            //bps.addBuchungsprofil(new BuchungsprofilAnwender("EJ1222",0,105,mitfliegerList));
            //System.out.println(bps.loescheBuchungsprofil(13));
            Angestellter angestellter=null;
            for (Angestellter i : benutzerprofilSpeicher.getAngestelltenListe()){
                if (i.getPID()==105){
                    angestellter=i;
                }
            }
            //bps.getBuchungsprofilListe().add(new BuchungsprofilAngestellter("EJ1222",0,105,mitfliegerList));

            //bps.addBuchungsprofil(new BuchungsprofilAngestellter("EJ1222",0,105,mitfliegerList));
            /*BuchungsprofilAngestellter buchungsprofilAngestellter = angestellter.bucheFlugFuerKunde("EJ1222",0,mitfliegerList);
            bps.aendereBuchungsprofil(buchungsprofilAngestellter.getBuchungsID(),10);*/

            System.out.println(bps.loescheBuchungsprofil(14));

            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            for (Buchungsprofil i: bps.getBuchungsprofilListe()){
                System.out.println("///////");
                System.out.println(i.getBuchungsID());
                System.out.println(i.getFlugNummer());
                System.out.println(i.getGepaeckGewicht());
                if (i instanceof BuchungsprofilAnwender) {
                    System.out.println("---Anwender---");

                    System.out.println(((BuchungsprofilAnwender) i).getAnwenderPID());

                    for (Mitflieger j : ((BuchungsprofilAnwender) i).getMitfliegerListe()){
                        System.out.println("-----");
                        System.out.println(j.getVorname());
                        System.out.println(j.getNachname());
                        System.out.println(j.getEmail());
                        System.out.println(j.getGeburtsDatum());
                        System.out.println(j.getIdentitaetsNummer());
                    }
                }
                if (i instanceof BuchungsprofilAngestellter){
                    System.out.println("---Angestellter---");
                    System.out.println(((BuchungsprofilAngestellter) i).getAngestellterPID());
                    for (Mitflieger j : ((BuchungsprofilAngestellter) i).getPassagierListe()){
                        System.out.println("-----");
                        System.out.println(j.getVorname());
                        System.out.println(j.getNachname());
                        System.out.println(j.getEmail());
                        System.out.println(j.getGeburtsDatum());
                        System.out.println(j.getIdentitaetsNummer());
                    }
                }
            }

          //bps.inDateiSchreiebn();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*

        bps.aendereBuchungsprofil();
        */




        /*try {
            Runtime.getRuntime().exec("powershell /c  .\\..\\files\\.\\.\\email_versenden_buchung.ps1 -zu \"thomas.depian99@gmail.com\" -vorname \"Thomas\" -nachname \"Depian\" -buchungsID \"123456\" -vorherigesGepaeckGewicht \"13,3\" -neuesGepaeckGewicht \"15,4\"");

            /*
            * PS C:\Users\thoma> .\email_versenden_buchung.ps1 -zu "thomas.depian99@gmail.com" -vorname "Thomas" -nachname "Depian" -b
uchungsID "123456" -vorherigesGepaeckGewicht "13,3" -neuesGepaeckGewicht "15,4"*/
        /*}catch (IOException e){
            e.printStackTrace();
        }*/


        /*try {
            bps.ausDateiLesen();
        }catch (IOException e){
            e.printStackTrace();
        }catch (FlugNichtBuchbarException f){
            f.printStackTrace();
        }*/
    }
}
