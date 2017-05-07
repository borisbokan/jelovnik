package model;

import java.util.ArrayList;

/**
 * Created by borcha on 07.05.17..
 */

public class Kategorija {

    int id;
    private String naziv;
    public ArrayList<Jelo> jela;

    public Kategorija(){

    }

    public Kategorija(String _nazivKategorije){
        this.naziv=_nazivKategorije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


    public ArrayList<Jelo> getJela() {
        return jela;
    }

    public void setJela(ArrayList<Jelo> jela) {
        this.jela = jela;
    }

    public void addJelo(Jelo _jelo) {
       this.jela.add(_jelo);
    }

    public String toString(){
        return "Kategorija ( " + this.id + " - " + this.naziv + " ) ";
    }
}
