package model;

import java.util.ArrayList;

/**
 * Created by borcha on 07.05.17..
 */

public class Jelo {

    private int id;
    private String slika;
    private String naziv;
    private String opis;
    private double cena;
    private String sastojci;
    private Kategorija kategorija;
    private float kolicina;
    private ArrayList<Sastojak> arlSastojciKalorijskeVrednosti;

    public Jelo(){
            //Jelo+Jelo(konstruct) = bit
    }

    /**
     * Parametri konstruktora klase Jelo
     * @param _slika
     * @param _naziv
     * @param _opis
     * @param _cena
     * @param _sastojci
     * @param _kolicina
     */
    public Jelo(String _slika,String _naziv, String _opis, Double _cena,String _sastojci,float _kolicina){

        this.slika=_slika;
        this.naziv=_naziv;
        this.opis=_opis;
        this.cena=_cena;
        this.sastojci=_sastojci;
        this.kolicina=_kolicina;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public float getKolicina() {
        return kolicina;
    }

    public void setGramaza(float gramaza) {
        this.kolicina = gramaza;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }

    public ArrayList<Sastojak> getArlSastojciKalorijskeVrednosti() {
        return arlSastojciKalorijskeVrednosti;
    }

    public void setArlSastojciKalorijskeVrednosti(ArrayList<Sastojak> arlSastojciKalorijskeVrednosti) {
        this.arlSastojciKalorijskeVrednosti = arlSastojciKalorijskeVrednosti;
    }

    //pomocna metodaa
    public void addArlSastojciKalorijskeVrednosti(Sastojak _sastojak) {
        arlSastojciKalorijskeVrednosti.add(_sastojak);
    }

    public String toString(){
        return "Jelo ( id:" +  this.id + "  naziv: " + this.naziv +  " opis: " + this.opis + "  Kategorija: " + this.kategorija.getNaziv() + " " + this.kolicina + " gr )";
    }
}
