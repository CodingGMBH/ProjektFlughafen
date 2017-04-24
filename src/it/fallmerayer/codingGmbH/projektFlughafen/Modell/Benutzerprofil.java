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

    //Bei Konstruktor wird "angemeldet" standardmäßig auf false gesetzt. Die "PID" muss übergeben werden, da man nicht weiß, ob es ein Anwender, Angestellter oder Administrator ist.
    public Benutzerprofil(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort, int PID) {
        super(vorname, nachname, email, geburtsDatum);
        this.benutzerName = benutzerName;
        this.passwort = passwort;
        this.PID = PID;
        this.angemeldet = false;
    }

    //Dieser Konstruktor wird für den Angestellten und Administrator benötigt, da die "PID" erst nach dem "super()" Aufruf im Konstruktor gesetzt wird.
    public Benutzerprofil(String vorname, String nachname, String email, LocalDateTime geburtsDatum, String benutzerName, String passwort) {
        super(vorname, nachname, email, geburtsDatum);
        this.benutzerName = benutzerName;
        this.passwort = passwort;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Benutzerprofil that = (Benutzerprofil) o;

        if (PID != that.PID) return false;
        if (angemeldet != that.angemeldet) return false;
        if (benutzerName != null ? !benutzerName.equals(that.benutzerName) : that.benutzerName != null) return false;
        return passwort != null ? passwort.equals(that.passwort) : that.passwort == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (benutzerName != null ? benutzerName.hashCode() : 0);
        result = 31 * result + (passwort != null ? passwort.hashCode() : 0);
        result = 31 * result + PID;
        result = 31 * result + (angemeldet ? 1 : 0);
        return result;
    }
}
