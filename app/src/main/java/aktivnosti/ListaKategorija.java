package aktivnosti;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapteri.AdapterKategorije;
import mdb.MySqlKategorija;
import mdb.dbmodel.Kategorija;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 06.06.17..
 */

public class ListaKategorija extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lsKategorija;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorije);


        lsKategorija=(ListView)findViewById(R.id.lsvKategorije);
        registerForContextMenu(lsKategorija);
        lsKategorija.setOnItemClickListener(this);

        ucitajKategorijeUListu();
    }


    private void ucitajKategorijeUListu(){

        MySqlKategorija mojeKat=new MySqlKategorija(this);
        List<Kategorija> kateg = mojeKat.getSveKategorije();


        AdapterKategorije adKategorije=new AdapterKategorije(this,kateg);
        lsKategorija.setAdapter(adKategorije);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add(11,0,0,R.string.nova_kategorija).setIcon(R.mipmap.ic_dodaj_kateg);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(13,0,0,R.string.ispravi_kategoriju).setIcon(R.mipmap.ic_ispravi_cekic);
        menu.add(13,1,1,R.string.obrisi_kategoriju).setIcon(R.mipmap.ic_obrisi_jelo);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
