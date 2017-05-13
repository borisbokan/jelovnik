package fragmenti;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.aleph.android.example12.R;


/**
 * Created by borcha on 13.05.17..
 */

public class detalji_fragment extends Fragment {

    View vi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.detalji_fragmet,container,false);


        return vi;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



}
