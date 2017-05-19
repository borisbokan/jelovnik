package fragmenti;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Jelo;
import model.Sastojak;
import rs.aleph.android.example12.R;


/**
 * Created by borcha on 13.05.17..
 */

public class DetaljiFragment extends Fragment implements View.OnClickListener,ListaFragment.OnItemSelectedListener {


    private TextView txvNaziv,txvOpis,txvCena,txvKategorija;
    private Button btnPoruci;
    private ImageView imgSlika;
    private Spinner spkategorije;
    private ListView lsvSastojci;

    View vi;
    private Jelo selJelo;
    private int position;
    private int groPos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.detalji_fragmet,container,false);

        imgSlika=(ImageView)vi.findViewById(R.id.slikaJela);
        txvNaziv=(TextView)vi.findViewById(R.id.txtNazivJelo_detalji);
        txvKategorija=(TextView)vi.findViewById(R.id.txtKategJela_detalji);
        txvOpis=(TextView)vi.findViewById(R.id.txtOpis_detalji);
        txvCena=(TextView)vi.findViewById(R.id.txtCena_detalji);
        lsvSastojci=(ListView)vi.findViewById(R.id.lsvsastojci_detalji);


        return vi;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putInt("groPos", position);
        outState.putInt("selPos",groPos);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            this.groPos=savedInstanceState.getInt("groPos",0);
            this.position = savedInstanceState.getInt("selPos", 0);
        }

        selJelo=(Jelo)ListaFragment.expAdapterJelovnik.getChild(this.groPos,this.position);

        InputStream inpStr=null;
        try {
            inpStr=getActivity().getAssets().open(selJelo.getSlika().toString());
            Drawable drawable = Drawable.createFromStream(inpStr, null);
            imgSlika.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DecimalFormat decFor=new DecimalFormat("#.00");
        String cena=decFor.format(selJelo.getCena());

        ArrayList<Sastojak> sastojci=selJelo.getArlSastojciKalorijskeVrednosti();
        ArrayAdapter<Sastojak> adSastojci=new ArrayAdapter<Sastojak>(getActivity(),android.R.layout.simple_list_item_1,sastojci);
        lsvSastojci.setAdapter(adSastojci);


        txvNaziv.setText(selJelo.getNaziv());
        txvOpis.setText(selJelo.getOpis());
        txvKategorija.setText(selJelo.getKategorija().getNaziv());
        txvCena.setText(cena + " din");



            FloatingActionButton fabDodaj=(FloatingActionButton)getView().findViewById(R.id.fabDodaja_detalji);
            fabDodaj.setOnClickListener(this);

         }



    public void setContent(final int _groPos,final int _position) {
        this.groPos=_groPos;
        this.position=_position;

    }

    // Called to update fragment's content.
    public void updateContent(final int _groPos,final int _position) {

        selJelo=(Jelo)ListaFragment.expAdapterJelovnik.getChild(_groPos,_position);

        InputStream inpStr=null;
        try {
            inpStr=getActivity().getAssets().open(selJelo.getSlika().toString());
            Drawable drawable = Drawable.createFromStream(inpStr, null);
            imgSlika.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DecimalFormat decFor=new DecimalFormat("#.00");
        String cena=decFor.format(selJelo.getCena());

        ArrayList<Sastojak> sastojci=selJelo.getArlSastojciKalorijskeVrednosti();
        ArrayAdapter<Sastojak> adSastojci=new ArrayAdapter<Sastojak>(getActivity(),android.R.layout.simple_list_item_1,sastojci);
        lsvSastojci.setAdapter(adSastojci);


        txvNaziv.setText(selJelo.getNaziv());
        txvOpis.setText(selJelo.getOpis());
        txvKategorija.setText(selJelo.getKategorija().getNaziv());
        txvCena.setText(cena + " din");



        getActivity().getActionBar().setTitle(selJelo.getNaziv());
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.fabDodaja_detalji:
                Toast.makeText(getActivity(),"Kliknuo poruci jelo..",Toast.LENGTH_SHORT).show();
                break;

        }
    }



    @Override
    public void onItemSelected(int group, int position) {
        this.groPos=group;
        this.position=position;
        updateContent(group,position);
    }



}
