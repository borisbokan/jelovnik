package adapteri;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import rs.aleph.android.example12.*;
import java.util.ArrayList;

import model.NavigacioniMeni;

/**
 * Created by borcha on 17.05.17..
 */

public class DrawMeniAdapter extends ArrayAdapter<NavigacioniMeni> {

    private Context cont;
    private ArrayList<NavigacioniMeni> stavke;
    private ImageView imgvIkonaStavke;
    private TextView txvTitl,txvOpis;


    public DrawMeniAdapter(Context context, ArrayList<NavigacioniMeni> _stavke) {
        super(context, R.layout.draw_meni_stavka,_stavke);
        cont=context;
        stavke=_stavke;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater ly=(LayoutInflater)cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=ly.inflate(R.layout.draw_meni_stavka,null);

        imgvIkonaStavke=(ImageView)convertView.findViewById(R.id.imgvIkona_drwrmenu_item);
        txvTitl=(TextView)convertView.findViewById(R.id.txvTitl_drwrmenu_item);
        txvOpis=(TextView)convertView.findViewById(R.id.txvOpis_drwrmenu_item);

        NavigacioniMeni navMeni=getItem(position);

        imgvIkonaStavke.setImageResource(navMeni.getIkona());
        txvTitl.setText(navMeni.getNaslov());
        txvOpis.setText(navMeni.getOpis());

        convertView.setTag(navMeni);

        return convertView;
    }


    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public NavigacioniMeni getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(NavigacioniMeni item) {
        return super.getPosition(item);
    }

}
