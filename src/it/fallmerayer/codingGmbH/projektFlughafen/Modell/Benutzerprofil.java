package it.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.time.LocalDateTime;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public abstract class Benutzerprofil extends Person{

    private String benutzerName;
    private String passwort;
    private int PID;
    private boolean angemeldet;

    //"angemeldet" wird standardmäßig auf "false" gesetzt. Die "PID" der einzelnen "Benutzerprofile" werden in den jeweiligen Subklassen gesetzt und nicht dem Konstruktor übergeben.
    public Benutzerprofil(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) {
        super(vorname, nachname, email, geburtsDatum);
        this.benutzerName = benutzerName;
        this.passwort = passwort;
        this.angemeldet = false;
    }

    //Wenn ein "Benutzerprofil" aus der Datei gelesen wird, muss eine "PID" übergeben werden.
    public Benutzerprofil(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int PID) {
        super(vorname, nachname, email, geburtsDatum);
        this.benutzerName = benutzerName;
        this.passwort = passwort;
        this.PID = PID;
        this.angemeldet = false;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public boolean isAngemeldet() {
        return angemeldet;
    }

    public void setAngemeldet(boolean angemeldet) {
        this.angemeldet = angemeldet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzerprofil)) return false;
        if (!super.equals(o)) return false;

        Benutzerprofil that = (Benutzerprofil) o;

        if (getPID() != that.getPID()) return false;
        if (isAngemeldet() != that.isAngemeldet()) return false;
        if (getBenutzerName() != null ? !getBenutzerName().equals(that.getBenutzerName()) : that.getBenutzerName() != null)
            return false;
        return getPasswort() != null ? getPasswort().equals(that.getPasswort()) : that.getPasswort() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getBenutzerName() != null ? getBenutzerName().hashCode() : 0);
        result = 31 * result + (getPasswort() != null ? getPasswort().hashCode() : 0);
        result = 31 * result + getPID();
        result = 31 * result + (isAngemeldet() ? 1 : 0);
        return result;
    }
}
