package aktivnosti;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import mdb.MySqlKategorija;
import mdb.dbmodel.Kategorija;
import pomocne.infoPoruka;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 04.06.17..
 */

public class UnosKategorija extends Activity implements  View.OnClickListener {


    public static final int TIP_OPERACIJE_NOVO = 0;
    public static final int TIP_OPERACIJE_ISPRAVI = 1;

    Kategorija kategorija;
    private Button btnSnimiKategoriju;
    private EditText etxtNazivKategorije;
    private int tip_opr=0;
    private int id_kat=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_kategorija);

        tip_opr=getIntent().getIntExtra("tip_opr",0);
        id_kat=getIntent().getIntExtra("id_kat",0);

        btnSnimiKategoriju=(Button)findViewById(R.id.btnSnimiKategoriju_unosKategorije);
        etxtNazivKategorije=(EditText)findViewById(R.id.etxtNazivKategorije_unosKategorije);


        btnSnimiKategoriju.setOnClickListener(this);


        if(tip_opr==TIP_OPERACIJE_ISPRAVI){
            this.kategorija=getKategorijaPoId(id_kat);
            ucitajZaIspravku(kategorija);
        }

    }

    private Kategorija getKategorijaPoId(int _idKategorije) {

        MySqlKategorija mysqlKat=new MySqlKategorija(this);
        final Kategorija kategorija=mysqlKat.getKategorijaPoId(_idKategorije);

        return kategorija;
    }


    private void ucitajZaIspravku(Kategorija _kategorija){

        if(!_kategorija.equals(null)){
            etxtNazivKategorije.setText(_kategorija.getNaziv());
        }
    }

    private void ispraviKategoriju() {
            this.kategorija.setNaziv(etxtNazivKategorije.getText().toString());

            MySqlKategorija myKat=new MySqlKategorija(this,this.kategorija);
            myKat.prepraviKategoriju();


    }

    private void snimiNovo() {
         final Kategorija kategorija=new Kategorija(etxtNazivKategorije.getText().toString());
         MySqlKategorija myKat=new MySqlKategorija(this);
         myKat.snimiNovuKategoriju(kategorija);
         myKat.setOnSnimiNovuKategoriju(new MySqlKategorija.ISnimiNovuKategoriju() {
             @Override
             public void OnSnimiNovuKategoriju(int uspesno) {
                 if(uspesno==1){
                     infoPoruka.newInstance(getBaseContext(),"Poruka snimanja","Uspesno snimljena kategorija " + kategorija.getNaziv());
                 } else{
                     infoPoruka.newInstance(getBaseContext(),"Poruka greske","Ne uspesno snimljena kategorija " + kategorija.getNaziv());
                 }
             }
         });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimiKategoriju_unosKategorije:

                if(tip_opr==TIP_OPERACIJE_NOVO){
                    snimiNovo();
                    finish();
                    //Toast.makeText(this,"Kliknuo novo snimi",Toast.LENGTH_SHORT).show();
                }else{
                    ispraviKategoriju();
                   // Toast.makeText(this,"Kliknuo ispravi kateg..",Toast.LENGTH_SHORT).show();
                    finish();
                }


                break;
        }
    }

}
