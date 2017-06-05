package aktivnosti;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mdb.MySqlKategorija;
import mdb.dbmodel.Kategorija;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 04.06.17..
 */

public class UnosKategorija extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {


    public static final int TIP_OPERACIJE_NOVO = 0;
    public static final int TIP_OPERACIJE_ISPRAVI = 1;

    private ListView lsvListaKategorija;
    private Button btnSnimiKategoriju;
    private EditText etxtNazivKategorije;
    private Kategorija kategorija;
    private int tip_opr=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_kategorija);

        tip_opr=getIntent().getIntExtra("tip_ope_kat",0);


        lsvListaKategorija=(ListView)findViewById(R.id.lsvListaKategorija_unosKategorije);
        btnSnimiKategoriju=(Button)findViewById(R.id.btnSnimiKategoriju_unosKategorije);
        etxtNazivKategorije=(EditText)findViewById(R.id.etxtNazivKategorije_unosKategorije);

        lsvListaKategorija.setOnItemClickListener(this);
        btnSnimiKategoriju.setOnClickListener(this);

        ucitajKategoriUjeListu();

        registerForContextMenu(lsvListaKategorija);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ucitajZaIspravku((Kategorija) parent.getAdapter().getItem(position));


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimiKategoriju_unosKategorije:

                if(tip_opr==TIP_OPERACIJE_NOVO){
                    snimiNovo();
                }else{
                    ispraviKategoriju();
                }


                break;
        }
    }



    private void ucitajZaIspravku(Kategorija _kategorija){

        if(!_kategorija.equals(null)){
            etxtNazivKategorije.setText(_kategorija.getNaziv());
        }

    }

    private void ispraviKategoriju() {
        this.kategorija.setNaziv(etxtNazivKategorije.getText().toString());

        try {
            MySqlKategorija myKat=new MySqlKategorija(this,this.kategorija);
            myKat.updateKategoriju();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void snimiNovo() {

        try {
            MySqlKategorija myKat=new MySqlKategorija(this);
            Kategorija kategorija=new Kategorija(etxtNazivKategorije.getText().toString());
            myKat.snimiNovuKategoriju(kategorija);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void ucitajKategoriUjeListu(){


        List<Kategorija> kateg= null;
        try {
            MySqlKategorija mojeKat=new MySqlKategorija(this);
            kateg = mojeKat.getSveKategorije();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adListaKate=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getNaziveKateg(kateg));

        lsvListaKategorija.setAdapter(adListaKate);


    }


    private List<String> getNaziveKateg(List<Kategorija> _listaKat){
        List<String> lista=new ArrayList<>();
        for (Kategorija stavka : _listaKat) {
            lista.add(stavka.getNaziv().toString());
        }

        return lista;

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(110,0,0,"Obrisi kategoriju").setIcon(R.mipmap.ic_obrisi_jelo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        if (item.getGroupId() == 110) {
            switch (item.getItemId()){

                case 0:
                    try {
                        MySqlKategorija mojeKat=new MySqlKategorija(this);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return super.onContextItemSelected(item);

            }
        }
        return super.onContextItemSelected(item);
    }
}
