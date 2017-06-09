package aktivnosti;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import java.sql.SQLException;
import java.util.List;
import mdb.MyDbHelp;
import mdb.MySqlKategorija;
 import mdb.dbmodel.Kategorija;
import pomocne.infoPoruka;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 06.06.17..
 */

public class ListaKategorija extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lsKategorija;
    private Kategorija selKategorija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorije);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(R.string.app_name);

        if (actionBar != null) {

            actionBar.show();
        }

        lsKategorija=(ListView)findViewById(R.id.lsvKategorije);
        registerForContextMenu(lsKategorija);


        ucitajKategorijeUListu();

        lsKategorija.setOnItemClickListener(this);

    }


    private void ucitajKategorijeUListu(){


        MySqlKategorija msQlKat=new MySqlKategorija(this);
        List<Kategorija> lista=msQlKat.getSveKategorije();
        ArrayAdapter<Kategorija> adListaKate=new ArrayAdapter<Kategorija>(this,android.R.layout.simple_list_item_1,lista);
        lsKategorija.setAdapter(adListaKate);

        adListaKate.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(111,0,0,"Dodaj kategoriju").setIcon(R.mipmap.ic_dodaj_kateg);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId()==111){
            switch (item.getItemId()){
                case 0:

                    Intent inDodajKatego=new Intent(this,UnosKategorija.class);
                    startActivity(inDodajKatego);

                    return super.onOptionsItemSelected(item);


                default:

                    return super.onOptionsItemSelected(item);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        menu.add(13,0,0,R.string.ispravi_kategoriju).setIcon(R.drawable.ic_action_prepravi);
        menu.add(13,1,1,R.string.obrisi_kategoriju).setIcon(R.drawable.ic_action_obrisi);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getGroupId()==13){
            switch (item.getItemId()){
                case 0:

                    Intent ispKate=new Intent(this,UnosKategorija.class);
                    ispKate.putExtra("tip_opr",UnosKategorija.TIP_OPERACIJE_ISPRAVI);
                    ispKate.putExtra("id_kat",selKategorija.getId());
                    startActivity(ispKate);

                     return super.onContextItemSelected(item);

                case 1:

                      MySqlKategorija mysqKat=new MySqlKategorija(this,selKategorija);
                      mysqKat.obrisiKategoriju();
                     /* mysqKat.setOnObrisiKategoriju(new MySqlKategorija.IObrisiKategoriju() {
                          @Override
                          public void OnObrisiKategoriju(int uspesno) {
                              if(uspesno==1){
                                  infoPoruka.newInstance(getBaseContext(),"Poruka brisanja kategorije","Uspesno obrisana kategorija " + selKategorija.getNaziv());
                                  ucitajKategorijeUListu();
                              }else{
                                  infoPoruka.newInstance(getBaseContext(),"Poruka greske","Ne uspesno obrisana kategorija " + selKategorija.getNaziv());
                              }
                          }
                      });*/

                      ucitajKategorijeUListu();


                    return super.onContextItemSelected(item);


                default:

                    return super.onContextItemSelected(item);
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selKategorija=(Kategorija)parent.getAdapter().getItem(position);
        Toast.makeText(this,"Test sel kategorija.. " + selKategorija + " " + selKategorija.getNaziv() + " ID: " + selKategorija.getId(), Toast.LENGTH_LONG).show();

        openContextMenu(lsKategorija);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ucitajKategorijeUListu();
    }


}
