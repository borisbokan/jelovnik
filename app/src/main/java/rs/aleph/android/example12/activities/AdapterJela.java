package rs.aleph.android.example12.activities;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.Jelo;
import rs.aleph.android.example12.R;

/**
 * Created by borcha on 07.05.17..
 */

public class AdapterJela extends ArrayAdapter<Jelo> {

    private View vi;
    private Context cont;
    private TextView tvNazivJela,tvKategorija;


    public AdapterJela(Context _context, int resource,ArrayList<Jelo> _jela) {
        super(_context, resource,_jela);
        cont=_context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater lyInf=(LayoutInflater) cont.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        vi=lyInf.inflate(R.layout.stavka_liste_jela,null);
        tvNazivJela=(TextView)vi.findViewById(R.id.txtNazivJela_lista);
        tvKategorija=(TextView)vi.findViewById(R.id.txtKategorija_lista);

        tvNazivJela.setText(getItem(position).getNaziv());
        tvKategorija.setText(getItem(position).getKategorija().getNaziv());

        vi.setTag(tvNazivJela);

        return vi;
    }


}
