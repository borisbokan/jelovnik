package adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mdb.dbmodel.Kategorija;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 05.06.17..
 */

public class AdapterKategorije extends ArrayAdapter<Kategorija> {

    LayoutInflater inflater;


    public AdapterKategorije(Context context,List<Kategorija> lsKategorije) {
        super(context, R.layout.stavka_kategorije,lsKategorije);


    }


    @Override
    public View getView(int position,View view, ViewGroup parent) {

        inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.stavka_kategorije,null);

        Kategorija kate=getItem(position);
        TextView txvNazivKategorije=(TextView)view.findViewById(R.id.txvKategorja_stavkaKategorije);
        txvNazivKategorije.setText(kate.getNaziv());

        return view;
    }




}
