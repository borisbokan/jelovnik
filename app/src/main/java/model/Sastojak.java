package model;

/**
 * Created by borcha on 08.05.17..
 */

public class Sastojak {

    private String nazivSastojka;
    private float kalorijskaVrednost;
    private String jedinicaMere;
    private float kolicina;

    private int id;

    public Sastojak(){ }

    /**
     * Parametri konstruktora
     * @param _nazivSastojka
     * @param _kalorijskaVrednost
     * @param _kolicina
     * @param _jedinicaMere
     */
    public Sastojak(String _nazivSastojka, float _kalorijskaVrednost,float _kolicina,String _jedinicaMere){

        this.nazivSastojka=_nazivSastojka;
        this.kalorijskaVrednost=_kalorijskaVrednost;
        this.kolicina=_kolicina;
        this.jedinicaMere=_jedinicaMere;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public String getNazivSastojka() {
        return nazivSastojka;
    }

    public void setNazivSastojka(String nazivSastojka) {
        this.nazivSastojka = nazivSastojka;
    }

    public float getKalorijskaVrednost() {
        return kalorijskaVrednost;
    }

    public void setKalorijskaVrednost(float kalorijskaVrednost) {
        this.kalorijskaVrednost = kalorijskaVrednost;
    }

    public float getKolicina() {
        return kolicina;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }



    public String toString(){
        return "Sastojak i kal. vred.= (" + this.id + " - " + this.nazivSastojka + " " + String.valueOf(this.kalorijskaVrednost) +")";
    }


}
