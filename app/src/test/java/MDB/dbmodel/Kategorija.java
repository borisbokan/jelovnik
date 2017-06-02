package MDB.dbmodel;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.jar.Attributes;

import javax.print.attribute.standard.MediaSize;

/**
 * Created by borcha on 07.05.17..
 */

@DatabaseTable(tableName = Kategorija.tKATEGORIJA)
public class Kategorija {

    public static final String tKATEGORIJA="Kategorija";
    public static final String tKATEGORIJA_ID="id";
    public static final String tKATEGORIJA_naziv="naziv";

    @DatabaseField(columnName=tKATEGORIJA_ID,generatedId = true)
    int id;
    @DatabaseField(columnName = tKATEGORIJA_naziv)
    private String naziv;
    @DatabaseField(foreignColumnName = "kateg",foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    public ForeignCollection<Jelo> jela;



    public Kategorija(){
         //Obavezno!!

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

     public void addJelo(Jelo _jelo) {
       this.jela.add(_jelo);
    }

    public String toString(){
        return "Kategorija ( " + this.id + " - " + this.naziv + " ) ";
    }
}
