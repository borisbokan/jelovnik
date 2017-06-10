package fragmenti;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import aktivnosti.UnosIspravkaJela;
import mdb.MySqlJelo;
import mdb.dbmodel.Jelo;
import pomocne.Slike;
import rs.aleph.android.jelovnik.R;


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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.detalji_fragmet,container,false);

        imgSlika=(ImageView)vi.findViewById(R.id.slikaJela);
        txvNaziv=(TextView)vi.findViewById(R.id.txtNazivJelo_detalji);
        txvKategorija=(TextView)vi.findViewById(R.id.txtKategJela_detalji);
        txvOpis=(TextView)vi.findViewById(R.id.txtOpis_detalji);
        txvCena=(TextView)vi.findViewById(R.id.txtCena_detalji);

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

        DecimalFormat decFor=new DecimalFormat("#.00");
        String cena=decFor.format(selJelo.getCena());

        Bitmap btmSlika= Slike.getSlikaIzFajla(selJelo.getSlika());

        imgSlika.setImageBitmap(btmSlika);
        txvNaziv.setText(selJelo.getNaziv());
        txvOpis.setText(selJelo.getOpis());
        txvKategorija.setText(selJelo.getKategorija().getNaziv());
        txvCena.setText(cena + " din");


         FloatingActionButton fabDodaj=(FloatingActionButton)getView().findViewById(R.id.fabDodaja_detalji);
            fabDodaj.setOnClickListener(this);

         }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalji,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
          case R.id.menu_det_prepravi_jelo:
              Intent ispraviJelo=new Intent(getActivity(), UnosIspravkaJela.class);
              ispraviJelo.putExtra("tip_ope", UnosIspravkaJela.TIP_OPERACIJE_ISPRAVI);
              ispraviJelo.putExtra("id_jelo",selJelo.getId());
              startActivity(ispraviJelo);

              Log.i("sel_jelo_id",String.valueOf(selJelo.getId()));

              return super.onOptionsItemSelected(item);

          case R.id.menu_det_obrisi_jelo:

              MySqlJelo dbJelo=new MySqlJelo(getActivity());
              dbJelo.setJelo(selJelo);
              dbJelo.obrisiJelo();


              return super.onOptionsItemSelected(item);
          }
        return super.onOptionsItemSelected(item);
    }

    public void setContent(final int _groPos, final int _position) {
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


        txvNaziv.setText(selJelo.getNaziv());
        txvOpis.setText(selJelo.getOpis());
        txvKategorija.setText(selJelo.getKategorija().getNaziv());
        txvCena.setText(cena + " din");


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
