package mdb.dbmodel;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Created by borcha on 07.05.17..
 */

@DatabaseTable(tableName = Kategorija.tKATEGORIJA)
public class Kategorija {

    public static final String tKATEGORIJA="tKATEGORIJA";
    public static final String tKATEGORIJA_ID="id";
    public static final String tKATEGORIJA_naziv="naziv";

    @DatabaseField(columnName=tKATEGORIJA_ID,generatedId = true)
    int id;
    @DatabaseField(columnName = tKATEGORIJA_naziv)
    private String naziv;


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


    public String toString(){
        return  this.naziv;
    }
}
