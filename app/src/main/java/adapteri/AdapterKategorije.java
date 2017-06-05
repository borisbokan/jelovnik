package adapteri;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import mdb.dbmodel.Kategorija;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 05.06.17..
 */

public class AdapterKategorije extends ArrayAdapter<Kategorija> {


    private final Context cont;
    TextView txvNazivKategorije;

    public AdapterKategorije(Context context,List<Kategorija> lsKategorije) {
        super(context, R.layout.stavka_kategorije,lsKategorije);
        cont=context;
    }


    @Override
    public View getView(int position,View vi, ViewGroup parent) {

        txvNazivKategorije=(TextView)vi.findViewById(R.id.txvKategorja_stavkaKategorije);

        return vi;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Kategorija getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Kategorija item) {
        return super.getPosition(item);
    }
}
