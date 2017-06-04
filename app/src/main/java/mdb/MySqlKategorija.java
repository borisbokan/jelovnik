package mdb;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import mdb.MyDbHelp;
import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;
import pomocne.infoPoruka;

/**
 * Created by borcha on 02.06.17..
 */

public class MySqlKategorija {


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
    public MySqlKategorija(Context _cont) throws SQLException {
        dbHelp=new MyDbHelp(_cont);
        this.cont=_cont;

    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont

     * @param _id
     */
    public MySqlKategorija(Context _cont, int _id) throws SQLException {
        this.cont = _cont;
        this.id = _id;

             }


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void updateKategoriju() {
        if(getId()!=0){
            //TODO. Uraditi Sql upit za update
            int rez= 0;
            try {
                rez = dbHelp.getDaoKategorija().updateId(this.kategorija,getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrepraviKategoriju.OnPrepraviKategoriju(rez);

            this.dbHelp.close();
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
                rez = dbHelp.getDaoKategorija().deleteById(getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObrisiKategoriju.OnObrisiKategoriju(rez);

            this.dbHelp.close();
        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }


    /**
     * Unos novog jela
     * @param _kategorija
     */
    public void snimiNovuKategoriju(Kategorija _kategorija) throws SQLException {

        if(!_kategorija.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez= 0;

                rez = dbHelp.getDaoKategorija().create(_kategorija);
                //SnimiNovuKategoriju.OnSnimiNovuKategoriju(rez);

            this.dbHelp.close();

        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat jelo ima  null vrednost");
        }



    }


    //Vraca listu svih objekata Jelo
    public List<Kategorija> getSveKategorije() throws SQLException {

        return dbHelp.getDaoKategorija().queryForAll();
    }

    //Trazi vrednost jela po ID zapisu
    public Kategorija getKategorijaPoId(int _id) throws SQLException {

        return dbHelp.getDaoKategorija().queryForId(_id);
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Jelo> getJelaPoKategoriji(Kategorija _kategorija) throws SQLException {

        QueryBuilder upit=dbHelp.getDaoJelo().queryBuilder().join(dbHelp.getDaoKategorija().queryBuilder());
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
