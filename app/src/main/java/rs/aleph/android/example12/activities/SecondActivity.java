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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;

import model.Jelo;
import model.JelovnikExpandAdapter;
import model.Sastojak;
import rs.aleph.android.example12.R;

// Each activity extends Activity class
public class SecondActivity extends Activity implements View.OnClickListener {

    private JelovnikExpandAdapter exadJelaDetalji;
    private TextView txvNaziv,txvOpis,txvSadrzaj,txvCena,txvKategorija;
    private Button btnPoruci;
    private ImageView imgSlika;

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
        txvKategorija=(TextView)findViewById(R.id.txtOpisJelo_detalji);
        txvOpis=(TextView)findViewById(R.id.txtOpis_detalji);
        txvSadrzaj=(TextView)findViewById(R.id.txtSastav_detalji);
        txvCena=(TextView)findViewById(R.id.txtCena_detalji);
        btnPoruci=(Button)findViewById(R.id.btnPoruci_detalji);

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

        //Uzimanje sastojaka jela
        String sastojci="";

        for (Sastojak sastojak : jelo.getArlSastojciKalorijskeVrednosti()) {
            sastojci+=sastojak.getNazivSastojka() + " - (kCal:" + String.valueOf(sastojak.getKalorijskaVrednost()) + "/" + String.valueOf(sastojak.getKolicina()) + " " + sastojak.getJedinicaMere() +")\n";
        }

        txvNaziv.setText(jelo.getNaziv());
        txvOpis.setText(jelo.getOpis());
        txvKategorija.setText(jelo.getKategorija().getNaziv());
        txvSadrzaj.setText(sastojci);
        txvCena.setText(cena + " din");


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
