package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import model.Jelo;
import model.JelovnikExpandAdapter;
import model.Sastojak;
import rs.aleph.android.example12.R;

// Each activity extends Activity class
public class SecondActivity extends Activity implements View.OnClickListener {

    private JelovnikExpandAdapter exadJelaDetalji;
    private TextView txvNaziv,txvOpis,txvCena,txvKategorija;
    private Button btnPoruci;
    private ImageView imgSlika;
    private Spinner spkategorije;
    private ListView lsvSastojci;

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int vrednostPoz=getIntent().getExtras().getInt(FirstActivity.DETALJI_POZ_KEY);
        int vrednostGropuPoz=getIntent().getExtras().getInt(FirstActivity.DETALJI_GROUP_POZ_KEY);
        Toast.makeText(this,"Test izabrane stavke: " + String.valueOf(vrednostPoz),Toast.LENGTH_LONG).show();

        imgSlika=(ImageView)findViewById(R.id.slikaJela);
        txvNaziv=(TextView)findViewById(R.id.txtNazivJelo_detalji);
        txvKategorija=(TextView)findViewById(R.id.txtKategorija_detalji);
        txvOpis=(TextView)findViewById(R.id.txtOpis_detalji);
        txvCena=(TextView)findViewById(R.id.txtCena_detalji);
        btnPoruci=(Button)findViewById(R.id.btnPoruci_detalji);
        spkategorije=(Spinner)findViewById(R.id.spKategorije_detalji);
        lsvSastojci=(ListView)findViewById(R.id.lsvSastojci_detalji);


        String[] kategorije=this.getResources().getStringArray(R.array.kategorije);
        ArrayAdapter<String> spAdapterKategorije=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,kategorije);
        spkategorije.setAdapter(spAdapterKategorije);


        //Slusaoc dogadjaja dugmeta poruci
        btnPoruci.setOnClickListener(this);

        //Adapter iz prve aktivnosti
        exadJelaDetalji=FirstActivity.expAdapterJelovnik;

        Jelo jelo=(Jelo)exadJelaDetalji.getChild(vrednostGropuPoz,vrednostPoz);

        InputStream inpStr=null;
        try {
            inpStr=getAssets().open(jelo.getSlika().toString());
            Drawable drawable = Drawable.createFromStream(inpStr, null);
            imgSlika.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DecimalFormat decFor=new DecimalFormat("#.00");
        String cena=decFor.format(jelo.getCena());


        txvNaziv.setText(jelo.getNaziv());
        txvOpis.setText(jelo.getOpis());
        txvKategorija.setText(jelo.getKategorija().getNaziv());
        txvCena.setText(cena + " din");


        ArrayList<Sastojak> sastojci=jelo.getArlSastojciKalorijskeVrednosti();
        ArrayAdapter<Sastojak> adSastojci=new ArrayAdapter<Sastojak>(this,android.R.layout.simple_list_item_1,sastojci);
        lsvSastojci.setAdapter(adSastojci);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnPoruci_detalji:
                Toast.makeText(this,"Kliknuo poruci jelo..",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
