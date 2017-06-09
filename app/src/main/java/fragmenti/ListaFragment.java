package fragmenti;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mdb.MySqlJelo;
import mdb.MySqlKategorija;
import mdb.dbmodel.Jelo;
import mdb.dbmodel.Kategorija;
import model.JelovnikExpandAdapter;
import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 13.05.17..
 */

public class ListaFragment extends Fragment {

    private List<mdb.dbmodel.Kategorija> lsKategorije;
    private List<mdb.dbmodel.Jelo> lsJela;
    public static JelovnikExpandAdapter expAdapterJelovnik;

    private View vi;
    public ExpandableListView exlvJela;

     public OnItemSelectedListener listener;
    private int groupPos;
    private int selPosition;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.lista_fragment,container,false);
        exlvJela=(ExpandableListView)vi.findViewById(R.id.exlvJela);

        return vi;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MySqlKategorija dbKategorija=new MySqlKategorija(getActivity());
        lsKategorije=dbKategorija.getSveKategorije();

        MySqlJelo dbJelo=new MySqlJelo(getActivity());

        HashMap<String, List<Jelo>> expandableListDetalji = new HashMap<String, List<Jelo>>();

        for (Kategorija stavka : lsKategorije ) {

            expandableListDetalji.put(stavka.getNaziv(), dbJelo.getJelaPoKategoriji(stavka));

        }

        expAdapterJelovnik=new JelovnikExpandAdapter(getActivity(),new ArrayList<String>(expandableListDetalji.keySet()),expandableListDetalji);

        exlvJela.setAdapter(expAdapterJelovnik);
        exlvJela.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Toast.makeText(getActivity(),"Otvori grupu",Toast.LENGTH_SHORT).show();
            }
        });


        exlvJela.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Toast.makeText(getActivity(),"Zatvori grupu",Toast.LENGTH_SHORT).show();

            }
        });

        exlvJela.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groPos, int position, long l) {
                listener.onItemSelected( groPos,position);

                return false;
            }
        });


    }


    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);


        try {
            listener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }


    // Container activity must implement this interface
    public interface OnItemSelectedListener {

         void onItemSelected(int group,int position);

    }

}
