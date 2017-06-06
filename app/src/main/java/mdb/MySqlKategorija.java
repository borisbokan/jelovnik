package mdb;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;
import pomocne.infoPoruka;

/**
 * Created by borcha on 02.06.17..
 */

public class MySqlKategorija extends MyDbHelp{


    private  Context cont;
    private  Kategorija kategorija;
    private int id=0;
    private MyDbHelp dbHelp;

    //Intefejsi
    public  ISnimiNovuKategoriju SnimiNovuKategoriju;
    public  IPrepraviKategoriju PrepraviKategoriju;
    public  IObrisiKategoriju ObrisiKategoriju;



    public  void setOnSnimiNovuKategoriju(ISnimiNovuKategoriju _snimiKategoriju){
        SnimiNovuKategoriju=_snimiKategoriju;

    }

    public  void setOnPrepraviKategoriju(IPrepraviKategoriju _prepraviKategoriju){
        PrepraviKategoriju=_prepraviKategoriju;

    }

    public void setOnObrisiKategoriju(IObrisiKategoriju _obrisiKategoriju){
        ObrisiKategoriju=_obrisiKategoriju;

    }

    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont

     */
    public MySqlKategorija(Context _cont){
        super(_cont);
        this.cont=_cont;

    }


    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _kategorija
     */
    public MySqlKategorija(Context _cont, Kategorija _kategorija) throws SQLException {
        super(_cont);

        this.cont = _cont;
        this.kategorija=_kategorija;

    }


    //Uzimam refer...na MyDbHelper
 /*   private MyDbHelp getDbHelp(){
        if(dbHelp==null){
            dbHelp= OpenHelperManager.getHelper(this.cont,MyDbHelp.class);
        }
        return dbHelp;

    }*/


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void updateKategoriju() {
        if(getId()!=0){
            //TODO. Uraditi Sql upit za update
            int rez= 0;
            try {
                rez = getDaoKategorija().updateId(this.kategorija,getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
           // PrepraviKategoriju.OnPrepraviKategoriju(rez);


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }

    /**
     * Brisanje jela
     */
    public void obrisiKategoriju()  {
        if(getId()!=0){
            //TODO. Uraditi Sql upit za delete
            int rez= 0;//Brisem zapis po ID jela
            try {
                getDaoKategorija().deleteById(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //ObrisiKategoriju.OnObrisiKategoriju(rez);


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }




    /**
     * Unos novog jela
     * @param _kategorija
     */
    public void snimiNovuKategoriju(Kategorija _kategorija) {

        if(!_kategorija.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez= 0;
            try {
                rez = getDaoKategorija().create(_kategorija);

                if(rez==1){
                    infoPoruka.newInstance(cont,"Poruka o gresci","Grupa pod nazivom " + _kategorija.getNaziv().toString() + " snimljena.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //SnimiNovuKategoriju.OnSnimiNovuKategoriju(rez);
        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat jelo ima  null vrednost");
        }

    }


    //Vraca listu svih objekata Jelo
    public List<Kategorija> getSveKategorije() {
        List<Kategorija> lista=new ArrayList<>();
        try {
            lista=getDaoKategorija().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Trazi vrednost jela po ID zapisu
    public Kategorija getKategorijaPoId(int _id) throws SQLException {

        return getDaoKategorija().queryForId(_id);
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Jelo> getJelaPoKategoriji(Kategorija _kategorija) throws SQLException {

        QueryBuilder upit=getDaoJelo().queryBuilder().join(getDaoKategorija().queryBuilder());
        Where<Jelo,Integer> where=upit.where().idEq(_kategorija);

        return where.query();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }


    public int getBrojKategorija(){
        int br=0;
        try {
            br=getDaoKategorija().queryForAll().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return br;
    }


    //***********************Intefejs -> dogadjaji **************************************
    public interface IPrepraviKategoriju{
        void OnPrepraviKategoriju(int uspesno);
    }

    public interface IObrisiKategoriju{
        void OnObrisiKategoriju(int uspesno);
    }

    public interface ISnimiNovuKategoriju{
        void OnSnimiNovuKategoriju(int uspesno);
    }



}
