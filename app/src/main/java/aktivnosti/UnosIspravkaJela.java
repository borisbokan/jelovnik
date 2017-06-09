package aktivnosti;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.List;
import mdb.MySqlJelo;
import mdb.MySqlKategorija;
import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;
import pomocne.infoPoruka;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 02.06.17..
 */

public class UnosIspravkaJela extends Activity implements View.OnClickListener,MySqlJelo.ISnimiNovoJelo,MySqlJelo.IPrepraviJelo, AdapterView.OnItemSelectedListener {


    public static final int UZMI_SLIKU_FAJL =1 ;
    public static final int UZMI_SLIKU_KAMERA =2 ;

    public static final int TIP_OPERACIJE_NOVO=0;
    public static final int TIP_OPERACIJE_ISPRAVI=1;

    ImageView imgSlika;
    ImageButton imgbDodajSliku;
    Button btnSnimi,btnOdustajem;
    EditText etxtNaziv,etxtOpis,etxtCena;
    Spinner spKategorije;
    private int tipOperacije;
    private Jelo jelo;

    private Kategorija selKategorija;
    private int id;


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
        id=getIntent().getIntExtra("id_jelo",0);


        if(tipOperacije==TIP_OPERACIJE_ISPRAVI){
            getJeloPoID(id);
            pripremiZaIspravku(id);
        }

            MySqlKategorija dbKateg=new MySqlKategorija(this);
            List<Kategorija> lista= dbKateg.getSveKategorije();

            ArrayAdapter<Kategorija> adListaKate=new ArrayAdapter<Kategorija>(this,android.R.layout.simple_spinner_dropdown_item,lista);
            spKategorije.setAdapter(adListaKate);

        spKategorije.setOnItemSelectedListener(this);
        btnOdustajem.setOnClickListener(this);
        btnSnimi.setOnClickListener(this);
        imgbDodajSliku.setOnClickListener(this);


    }

    private void getJeloPoID(int _id) {
        MySqlJelo dbJelo=new MySqlJelo(this);
        jelo=dbJelo.getJeloPoId(_id);
    }

    private void pripremiZaIspravku(int _id) {
         MySqlJelo dbJelo=new MySqlJelo(this);
         jelo=dbJelo.getJeloPoId(_id);

         etxtNaziv.setText(jelo.getNaziv());
         etxtCena.setText(String.valueOf(jelo.getCena()));
         spKategorije.setSelection(getJeloNaPoziciji());


    }

    private int getJeloNaPoziciji() {

        for (int i=0;i<spKategorije.getCount();i++) {
            Kategorija kategorija=(Kategorija)spKategorije.getItemAtPosition(i);
              if(kategorija.equals(this.jelo.getKategorija().getNaziv())){
                  return i;
              }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btnOdustajem_UnIsJela:

                Intent intPovratak=new Intent(this,FirstActivity.class);
                startActivity(intPovratak);
                break;

            case R.id.btnSnimi_UnIsJela:

                if(tipOperacije==TIP_OPERACIJE_NOVO){
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

        jeloNovo.setSlika("Slika iz fajla");
        jeloNovo.setNaziv(etxtNaziv.getText().toString());
        jeloNovo.setOpis(etxtOpis.getText().toString());
        jeloNovo.setCena(Double.valueOf(etxtCena.getText().toString()));
        jeloNovo.setKategorija(selKategorija);

        MySqlJelo unosJela= null;

        unosJela = new MySqlJelo(this);
        unosJela.snimiNovoJelo(jeloNovo);


    }

    private void ispravka() {
        Jelo jeloNovo=new Jelo();

        jeloNovo.setSlika("Slika fajl");
        jeloNovo.setNaziv(etxtNaziv.getText().toString());
        jeloNovo.setOpis(etxtOpis.getText().toString());
        jeloNovo.setCena(Float.valueOf(etxtCena.getText().toString()));
        jeloNovo.setKategorija(selKategorija);

        MySqlJelo unosJela= null;

        unosJela = new MySqlJelo(this);
        unosJela.snimiNovoJelo(jeloNovo);
        unosJela.setOnPrepraviJelo(new MySqlJelo.IPrepraviJelo() {
            @Override
            public void OnPrepraviJelo(int uspesno) {
                if(uspesno==1){
                    infoPoruka.newInstance(getBaseContext(),"Obavestenje o ispravci jela","Uspesna ispravka.");
                }else{
                    infoPoruka.newInstance(getBaseContext(),"Obavestenje o ispravci jela","Neupesna ispravka jela");
                }
            }
        });
         finish();

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selKategorija=(Kategorija)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selKategorija=(Kategorija)parent.getItemAtPosition(0);
    }
}
