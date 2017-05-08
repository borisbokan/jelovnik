package model;

/**
 * Created by borcha on 08.05.17..
 */

public class Sastojak {

    private String nazivSastojka;
    private float kalorijskaVrednost;
    private int id;

    public Sastojak(){ }

    public Sastojak(String _nazivSastojka, float _kalorijskaVrednost){
        this.nazivSastojka=_nazivSastojka;
        this.kalorijskaVrednost=_kalorijskaVrednost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String toString(){
        return "Sastojak i kal. vred.= (" + this.id + " - " + this.nazivSastojka + " " + this.kalorijskaVrednost +")";
    }


}
