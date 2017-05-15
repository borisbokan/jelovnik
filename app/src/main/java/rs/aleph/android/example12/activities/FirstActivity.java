package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import rs.aleph.android.example12.R;
import fragmenti.DetaljiFragment;
import fragmenti.ListaFragment;


public class FirstActivity extends Activity implements ListaFragment.OnItemSelectedListener{


	private boolean landscape;


	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		if(savedInstanceState==null){
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ListaFragment listaFragment= new ListaFragment();
			ft.add(R.id.exlist_fragment, listaFragment, "lista_fragment");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if (findViewById(R.id.detalji_fragment) != null) {
			landscape = true;
			getFragmentManager().popBackStack();

			DetaljiFragment detailFragment = (DetaljiFragment) getFragmentManager().findFragmentById(R.id.detalji_fragment);
			if (detailFragment == null) {
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				detailFragment = new DetaljiFragment();
				ft.replace(R.id.detalji_fragment, detailFragment, "detalji_fragment");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		}


	}


	@Override
	public void onItemSelected(int group, int position) {
		if (landscape) {
			// If the device is in the landscape mode updates detail fragment's content.
			DetaljiFragment detaljiFragment = (DetaljiFragment) getFragmentManager().findFragmentById(R.id.detalji_fragment);
			detaljiFragment.updateContent(group,position);
		} else {
			// If the device is in the portrait mode sets detail fragment's content and replaces master fragment with detail fragment in a fragment transaction.
			DetaljiFragment detaljiFragment = new DetaljiFragment();
			detaljiFragment.setContent(group,position);
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.exlist_fragment, detaljiFragment, "detalji_fragment_2");
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
	}


	@Override
	protected void onPause() {
		super.onPause();
	}



}
