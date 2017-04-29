package It.fallmerayer.codingGmbH.projektFlughafen.Modell;
import java.time.LocalDateTime;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class Mitflieger extends Person{

    private int identitaetsNummer;

    //Der Konstruktor muss mit allen Attributen erstellt werden.
    public Mitflieger(String vorname, String nachname, String email, LocalDateTime geburtsDatum, int identitaetsNummer) {
        super(vorname, nachname, email, geburtsDatum);
        this.identitaetsNummer = identitaetsNummer;
    }

    public int getIdentitaetsNummer() {
        return identitaetsNummer;
    }

    public void setIdentitaetsNummer(int identitaetsNummer) {
        this.identitaetsNummer = identitaetsNummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mitflieger)) return false;
        if (!super.equals(o)) return false;

        Mitflieger that = (Mitflieger) o;

        return getIdentitaetsNummer() == that.getIdentitaetsNummer();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIdentitaetsNummer();
        return result;
    }
}
