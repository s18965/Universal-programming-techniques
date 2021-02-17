/**
 *
 *  @author Stachurski Filip S18965
 *
 */

package zad1;


public class Purchase {

    private String identyfikator;
    private String imie;
    private String nazwisko;
    private String nazwaTowaru;
    private double zakupionaIlość;
    private double cena;
    private String fullLine;

    public Purchase(String fullLine) {
        this.fullLine = fullLine;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(String identyfikator) {
        this.identyfikator = identyfikator;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public double getZakupionaIlość() {
        return zakupionaIlość;
    }

    public void setZakupionaIlość(double zakupionaIlość) {
        this.zakupionaIlość = zakupionaIlość;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getFullLine() {
        return fullLine;
    }

    public void setFullLine(String fullLine) {
        this.fullLine = fullLine;
    }
}