package aktivnosti;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mdb.MySqlJelo;
import mdb.MySqlKategorija;
import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;
import pomocne.Slike;
import pomocne.infoPoruka;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 02.06.17..
 */

public class UnosIspravkaJela extends Activity implements View.OnClickListener,MySqlJelo.ISnimiNovoJelo,MySqlJelo.IPrepraviJelo {


    public static final int UZMI_SLIKU_FAJL =1 ;
    public static final int UZMI_SLIKU_KAMERA =2 ;

    public static final int TIP_OPERACIJE_NOVO=0;
    public static final int TIP_OPERACIJE_ISPRAVI=1;

    ImageView imgSlika;
    ImageButton imgbDodajSliku;
    Button btnSnimi,btnOdustajem;
    EditText etxtNaziv,etxtOpis,etxtCena;
    Spinner spKategorije;
    private int tipOperacije=0;
    private Jelo jelo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_ispravka_jela);

        imgSlika=(ImageView)findViewById(R.id.imgSlika_UnIsJela);
        imgbDodajSliku=(ImageButton)findViewById(R.id.imgBtnSlikaJela_UnIsJela);
        btnOdustajem=(Button)findViewById(R.id.btnOdustajem_UnIsJela);
        btnSnimi=(Button)findViewById(R.id.btnSnimi_UnIsJela);
        spKategorije=(Spinner)findViewById(R.id.spKategorije_UnIsJela);
        etxtNaziv=(EditText)findViewById(R.id.etxtNazivJela_UnIsJela);
        etxtOpis=(EditText)findViewById(R.id.etxtOpis_UnIsJela);
        etxtCena=(EditText)findViewById(R.id.etxtCena_UnIsJela);


        tipOperacije=getIntent().getIntExtra("tip_ope",0);

        jelo=(Jelo)getIntent().getExtras().get("jelo");

        MySqlKategorija myDbKate= null;
        try {
            myDbKate = new MySqlKategorija(this);
            List<Kategorija> lsKate=myDbKate.getSveKategorije();
            List<String> stavkeKategorije=new ArrayList<>();

            for (Kategorija stavka : lsKate) {
                stavkeKategorije.add(stavka.getNaziv());
            }

            ArrayAdapter<String> adKatego=new ArrayAdapter<String>(this,R.layout.stavka_kategorije,stavkeKategorije);
            spKategorije.setAdapter(adKatego);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        btnOdustajem.setOnClickListener(this);
        btnSnimi.setOnClickListener(this);
        imgbDodajSliku.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btnOdustajem_UnIsJela:


                Intent intPovratak=new Intent(this,FirstActivity.class);
                startActivity(intPovratak);
                break;


            case R.id.btnSnimi_UnIsJela:

                if(tipOperacije==0){
                    noviUnos();
                }else{
                    ispravka();
                }

                break;


            case R.id.imgBtnSlikaJela_UnIsJela:
                Intent uzmiSliku = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(uzmiSliku , UZMI_SLIKU_FAJL);
                break;


            default:
                break;
        }

    }


    private void noviUnos() {

        Jelo jeloNovo=new Jelo();

        jeloNovo.setSlika(Slike.getBytes(imgSlika));
        jeloNovo.setNaziv(etxtNaziv.getText().toString());
        jeloNovo.setOpis(etxtOpis.getText().toString());
        jeloNovo.setCena(Double.valueOf(etxtCena.getText().toString()));

        MySqlJelo unosJela= null;
        try {
            unosJela = new MySqlJelo(this);
            unosJela.snimiNovoJelo(jeloNovo);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void ispravka() {
        Jelo jeloNovo=new Jelo();

        jeloNovo.setSlika(Slike.getBytes(imgSlika));
        jeloNovo.setNaziv(etxtNaziv.getText().toString());
        jeloNovo.setOpis(etxtOpis.getText().toString());
        jeloNovo.setCena(Float.valueOf(etxtCena.getText().toString()));

        MySqlJelo unosJela= null;
        try {
            unosJela = new MySqlJelo(this);
            unosJela.snimiNovoJelo(jeloNovo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void OnPrepraviJelo(int uspesno) {
        if(uspesno==1){
            infoPoruka.newInstance(this,"Poruka ispravke jela","Uspeno ipravljeno jelo");
        }

    }

    @Override
    public void OnSnimiNovoJelo(int uspesno) {
        if(uspesno==1){
            infoPoruka.newInstance(this,"Poruka unos jela","Uspeno snimljeno jelo");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UZMI_SLIKU_KAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgSlika.setImageBitmap(photo);
            }else{
                imgSlika.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        }

        if (requestCode == UZMI_SLIKU_FAJL) {
            if (resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.MediaColumns.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                imgSlika.setImageURI(selectedImage);
                // String picturePath contains the path of selected Image
            }else{
                imgSlika.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            }

        }
    }
}
