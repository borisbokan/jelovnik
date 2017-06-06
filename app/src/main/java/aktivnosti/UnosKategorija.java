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
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapteri.AdapterKategorije;
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

        btnSnimiKategoriju=(Button)findViewById(R.id.btnSnimiKategoriju_unosKategorije);
        etxtNazivKategorije=(EditText)findViewById(R.id.etxtNazivKategorije_unosKategorije);


        btnSnimiKategoriju.setOnClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ucitajZaIspravku((Kategorija) parent.getAdapter().getItem(position));
            this.kategorija=(Kategorija) parent.getAdapter().getItem(position);

    }


    private void ucitajZaIspravku(Kategorija _kategorija){
        if(!_kategorija.equals(null)){
            etxtNazivKategorije.setText(_kategorija.getNaziv());
        }
    }

    private void ispraviKategoriju() {
        this.kategorija.setNaziv(etxtNazivKategorije.getText().toString());
        try {
            MySqlKategorija myKat=new MySqlKategorija(this,kategorija);
            myKat.updateKategoriju();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void snimiNovo() {
        Kategorija kategorija=new Kategorija(etxtNazivKategorije.getText().toString());

         MySqlKategorija myKat=new MySqlKategorija(this);
         myKat.snimiNovuKategoriju(kategorija);
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

                    MySqlKategorija mojeKat=new MySqlKategorija(this);


                    return super.onContextItemSelected(item);

            }
        }
        return super.onContextItemSelected(item);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimiKategoriju_unosKategorije:

                if(tip_opr==TIP_OPERACIJE_NOVO){
                    snimiNovo();
                    Toast.makeText(this,"Kliknuo novo snimi",Toast.LENGTH_SHORT).show();
                }else{
                    ispraviKategoriju();
                    Toast.makeText(this,"Kliknuo ispravi kateg..",Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

}
