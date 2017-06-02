package MDB.MyAsyncData;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import MDB.MyDbHelp;
import MDB.dbmodel.Jelo;
import MDB.dbmodel.Kategorija;
import pomocne.infoPoruka;

/**
 * Created by borcha on 02.06.17..
 */

public class MySqlKategorija {


    private  Context cont;
    private  Kategorija kategorija;
    private int id=0;
    private Dao<Kategorija,Integer> daoKategorija=null;
    private Dao<Jelo,Integer> daoJelo=null;
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
     * @param _kategorija
     */
    public MySqlKategorija(Context _cont, Kategorija _kategorija) throws SQLException {
        dbHelp=new MyDbHelp(_cont);
        this.cont=_cont;
        this.kategorija=_kategorija;
        this.daoKategorija= DaoManager.createDao(dbHelp.getConnectionSource(),Kategorija.class);
        this.daoJelo= DaoManager.createDao(dbHelp.getConnectionSource(),Jelo.class);

    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _kategorija
     * @param _id
     */
    public MySqlKategorija(Context _cont, Kategorija _kategorija, int _id) throws SQLException {
        this.cont=_cont;
        this.kategorija=_kategorija;
        this.id=_id;
        this.daoKategorija= DaoManager.createDao(dbHelp.getConnectionSource(),Kategorija.class);
        this.daoJelo= DaoManager.createDao(dbHelp.getConnectionSource(),Jelo.class);
    }


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void updateKategoriju() throws SQLException{
        if(getId()!=0){
            //TODO. Uraditi Sql upit za update
            int rez=daoKategorija.updateId(this.kategorija,getId());
            PrepraviKategoriju.OnPrepraviKategoriju(rez);

            this.dbHelp.close();
        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }

    /**
     * Brisanje jela
     */
    public void obrisiKategoriju() throws SQLException {
        if(getId()!=0){
            //TODO. Uraditi Sql upit za delete
            int rez=daoKategorija.deleteById(getId());//Brisem zapis po ID jela
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
    public void snimiNovuKategoriju(Jelo _kategorija) throws SQLException{

        if(!_kategorija.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez=daoKategorija.create(this.kategorija);
            SnimiNovuKategoriju.OnSnimiNovuKategoriju(rez);
            this.dbHelp.close();

        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat jelo ima  null vrednsot");
        }



    }


    //Vraca listu svih objekata Jelo
    private List<Kategorija> getSveKategorije() throws SQLException {
        return daoKategorija.queryForAll();
    }

    //Trazi vrednost jela po ID zapisu
    public Kategorija getKategorijaPoId(int _id) throws SQLException {

        return daoKategorija.queryForId(_id);
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Jelo> getJelaPoKategoriji(Kategorija _kategorija) throws SQLException {

        QueryBuilder upit=daoJelo.queryBuilder().join(daoKategorija.queryBuilder());
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
