package It.fallmerayer.codingGmbH.projektFlughafen.Model;
/**
 * Created by Hannes Niederwolfsgruber on 10.04.2017.
 */
public class WunschlistenEintrag {

    private String flugNummer;
    private int anzahlPassagiere;
    private double gepaeckGewicht;
    private boolean buchbar;        //--> Deffinition "buchbar": ob der Flug noch buchbar ist und ob der Flug mit der gewählten Konfiguration noch buchbar ist.

    public WunschlistenEintrag(String flugNummer, int anzahlPassagiere, double gepaeckGewicht, boolean buchbar) {
        this.flugNummer = flugNummer;
        this.anzahlPassagiere = anzahlPassagiere;
        this.gepaeckGewicht = gepaeckGewicht;
        this.buchbar = buchbar;
    }

    public String getFlugNummer() {
        return flugNummer;
    }

    public void setFlugNummer(String flugNummer) {
        this.flugNummer = flugNummer;
    }

    public int getAnzahlPassagiere() {
        return anzahlPassagiere;
    }

    public void setAnzahlPassagiere(int anzahlPassagiere) {
        this.anzahlPassagiere = anzahlPassagiere;
    }

    public double getGepaeckGewicht() {
        return gepaeckGewicht;
    }

    public void setGepaeckGewicht(double gepaeckGewicht) {
        this.gepaeckGewicht = gepaeckGewicht;
    }

    public boolean isBuchbar() {
        return buchbar;
    }

    public void setBuchbar(boolean buchbar) {
        this.buchbar = buchbar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WunschlistenEintrag)) return false;

        WunschlistenEintrag that = (WunschlistenEintrag) o;

        if (getAnzahlPassagiere() != that.getAnzahlPassagiere()) return false;
        if (Double.compare(that.getGepaeckGewicht(), getGepaeckGewicht()) != 0) return false;
        if (isBuchbar() != that.isBuchbar()) return false;
        return getFlugNummer() != null ? getFlugNummer().equals(that.getFlugNummer()) : that.getFlugNummer() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getFlugNummer() != null ? getFlugNummer().hashCode() : 0;
        result = 31 * result + getAnzahlPassagiere();
        temp = Double.doubleToLongBits(getGepaeckGewicht());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isBuchbar() ? 1 : 0);
        return result;
    }
}
