package mdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.query.In;
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



    public MyDbHelp(Context context) {

        super(context, DBNAME, null,DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource conn) {

        try {
            TableUtils.createTable(conn, Kategorija.class);
            TableUtils.createTable(conn, Jelo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource conn, int oldVersion, int newVersion) {

        try {

            TableUtils.dropTable(conn,Jelo.class,true);
            TableUtils.dropTable(conn,Kategorija.class,true);
            onCreate(database,conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public ConnectionSource getConnectionSource() {
        return super.getConnectionSource();
    }


    public Dao<Jelo,Integer> getDaoJelo(){

       Dao<Jelo, Integer> daoJelo=null;
        try {
            if(daoJelo==null){
                daoJelo=DaoManager.createDao(connectionSource,Jelo.class);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daoJelo;
    }


    public Dao<Kategorija,Integer> getDaoKategorija(){

        Dao<Kategorija, Integer> daoKateg = null;
        try {
            daoKateg=DaoManager.createDao(connectionSource,Kategorija.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daoKateg;
    }



    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
