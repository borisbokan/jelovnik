package MDB.dbmodel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

import model.Sastojak;

/**
 * Created by borcha on 07.05.17..
 */
@DatabaseTable(tableName = Jelo.tJELO_tNaziv)
public class Jelo {

    public static final String tJELO_tNaziv="tJELO";
    private static final String tJELO_id ="id";
    private static final String tJELO_slika="slika";
    private static final String tJELO_naziv="naziv";
    private static final String tJELO_opis="opis";
    private static final String tJELO_cena="cena";
    private static final String tJELO_kolicina="kolicina";



    @DatabaseField(columnName = tJELO_id)
    private int id;
    @DatabaseField(columnName = tJELO_slika)
    private String slika;
    @DatabaseField(columnName = tJELO_naziv)
    private String naziv;
    @DatabaseField(columnName = tJELO_opis)
    private String opis;
    @DatabaseField(columnName = tJELO_cena)
    private double cena;
    @DatabaseField(columnName = tJELO_kolicina)
    private float kolicina;

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Kategorija kateg;


    public Jelo(){

       //obavezno
    }

    /**
     * Parametri konstruktora klase Jelo
     * @param _slika
     * @param _naziv
     * @param _opis
     * @param _cena
     * @param _kolicina
     */
    public Jelo(String _slika,String _naziv, String _opis, Double _cena,float _kolicina){

        this.slika=_slika;
        this.naziv=_naziv;
        this.opis=_opis;
        this.cena=_cena;
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

    public float getKolicina() {
        return kolicina;
    }

    public void setGramaza(float gramaza) {
        this.kolicina = gramaza;
    }

    public Kategorija getKategorija() {
        return kateg;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kateg = kategorija;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }



    public String toString(){
        return "Jelo ( id:" +  this.id + "  naziv: " + this.naziv +  " opis: " + this.opis + "  Kategorija: " + this.getNaziv() + " " + this.kolicina + " gr )";
    }
}