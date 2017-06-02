package MDB;

import android.content.Context;
import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import MDB.MyAsyncData.asytPrepraviJelo;
import MDB.dbmodel.Jelo;
import MDB.dbmodel.Kategorija;
import pomocne.infoPoruka;

/**
 * Created by borcha on 02.06.17..
 */

public class MySqlJelo {


    private  Context cont;
    private  Jelo jelo;
    private int id=0;
    private Dao<Jelo,Integer> daoJelo=null;
    private Dao<Kategorija,Integer> daoKategorija=null;
    private MyDbHelp dbHelp;

    //Intefejsi
    public  ISnimiNovoJelo SnimiNovoJelo;
    public  IPrepraviJelo PrepraviJelo;
    public  IObrisiJelo ObrisiJelo;


    public  void setOnSnimiNovoJelo(ISnimiNovoJelo _snimiJelo){
        SnimiNovoJelo=_snimiJelo;

    }

    public  void setOnPrepraviJelo(IPrepraviJelo _prepraviJelo){
        PrepraviJelo=_prepraviJelo;

    }

    public void setOnObrisiJelo(IObrisiJelo _obrisiJelo){
        ObrisiJelo=_obrisiJelo;

    }

    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont
     * @param _jelo
     */
    public MySqlJelo(Context _cont, Jelo _jelo) throws SQLException {
        dbHelp=new MyDbHelp(_cont);
        this.cont=_cont;
        this.jelo=_jelo;
        this.daoKategorija= DaoManager.createDao(dbHelp.getConnectionSource(),Kategorija.class);
        this.daoJelo= DaoManager.createDao(dbHelp.getConnectionSource(),Jelo.class);


    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _jelo
     * @param _id
     */
    public MySqlJelo(Context _cont, Jelo _jelo, int _id) throws SQLException {
        this.cont=_cont;
        this.jelo=_jelo;
        this.id=_id;
        this.daoKategorija= DaoManager.createDao(dbHelp.getConnectionSource(),Kategorija.class);
        this.daoJelo= DaoManager.createDao(dbHelp.getConnectionSource(),Jelo.class);

    }


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void updateJelo() throws SQLException{
        if(getId()!=0){
            //TODO. Uraditi Sql upit za update
            int rez=daoJelo.updateId(jelo,getId());
            PrepraviJelo.OnPrepraviJelo(rez);

            this.dbHelp.close();
        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }

    /**
     * Brisanje jela
     */
    public void obrisiJelo() throws SQLException {
        if(getId()!=0){
            //TODO. Uraditi Sql upit za delete
            int rez=daoJelo.deleteById(getId());//Brisem zapis po ID jela
            ObrisiJelo.OnObrisiJelo(rez);

            this.dbHelp.close();
        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Jelo");

        }

    }


    /**
     * Unos novog jela
     * @param _jelo
     */
    public void snimiNovoJelo(Jelo _jelo) throws SQLException{

        if(!_jelo.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez=daoJelo.create(this.jelo);
            SnimiNovoJelo.OnSnimiNovoJelo(rez);
            this.dbHelp.close();

        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat jelo ima  null vrednsot");
        }



    }


    //Vraca listu svih objekata Jelo
    private List<Jelo> getSvaJela() throws SQLException {
        return daoJelo.queryForAll();
    }

    //Trazi vrednost jela po ID zapisu
    public Jelo getJeloPoId(int _id) throws SQLException {

        return daoJelo.queryForId(_id);
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Jelo> getJelaPoKategoriji(Kategorija _kategorija) throws SQLException {

        QueryBuilder upit=daoJelo.queryBuilder().join(daoKategorija.queryBuilder());
        Where<Jelo,Integer> where=upit.where().idEq(_kategorija);

        return where.query();
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //***********************Intefejs -> dogadjaji **************************************
    public interface IPrepraviJelo{
        void OnPrepraviJelo(int uspesno);
    }

    public interface IObrisiJelo{
        void OnObrisiJelo(int uspesno);
    }

    public interface ISnimiNovoJelo{
        void OnSnimiNovoJelo(int uspesno);
    }



}
