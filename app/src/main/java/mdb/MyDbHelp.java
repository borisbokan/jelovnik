package mdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;

/**
 * Created by borcha on 02.06.17..
 */

public class MyDbHelp extends OrmLiteSqliteOpenHelper {

    private static final String DBNAME="jelovnik.db";
    private static final int DB_VER=1;

    private Dao<Jelo, Integer> daoJelo=null;
    private Dao<Kategorija, Integer> daoKateg = null;

    public MyDbHelp(Context context) {
           super(context, DBNAME, null,DB_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource conn) {

        Log.i(MyDbHelp.class.getName(), "onCreate");
        try {
            TableUtils.createTable(conn, Kategorija.class);
            TableUtils.createTable(conn, Jelo.class);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource conn, int oldVersion, int newVersion) {

        Log.i(MyDbHelp.class.getName(), "onUpdate");
        try {
            TableUtils.dropTable(conn,Kategorija.class,true);
            TableUtils.dropTable(conn,Jelo.class,true);

            onCreate(database,conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Dao<Jelo,Integer> getDaoJelo() throws SQLException {

        if(daoJelo==null) {
            daoJelo = getDao(Jelo.class);
        }

        return daoJelo;
    }


    public Dao<Kategorija,Integer> getDaoKategorija() throws SQLException{

        if(daoKateg==null){
            daoKateg=getDao(Kategorija.class);
        }

        return daoKateg;
    }

    @Override
    public void close() {
        Log.i("baza","Zatvorena");
        daoKateg=null;
        daoJelo=null;

        super.close();
    }


}
