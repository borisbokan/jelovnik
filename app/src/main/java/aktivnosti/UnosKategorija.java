package aktivnosti;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


    public static final int TIP_OPERACIJE_NOVO = 11;
    public static final int TIP_OPERACIJE_ISPRAVI = 12;

    private ListView lsvListaKategorija;
    private Button btnSnimiKategoriju;
    private EditText etxtNazivKategorije;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_kategorija);



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



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimiKategoriju_unosKategorije:

                try {
                    MySqlKategorija myKat=new MySqlKategorija(this);
                    Kategorija kategorija=new Kategorija(etxtNazivKategorije.getText().toString());
                    myKat.snimiNovuKategoriju(kategorija);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
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
}
