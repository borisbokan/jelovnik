package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import rs.aleph.android.example12.R;
import fragmenti.DetaljiFragment;
import fragmenti.ListaFragment;


public class FirstActivity extends Activity implements ListaFragment.OnItemSelectedListener{


	private boolean landscape;


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){
			case R.id.menu_dodaj_jelo:

				Toast.makeText(this, "Kliknuo dodaj jelo", Toast.LENGTH_SHORT).show();

				return super.onOptionsItemSelected(item);


			case R.id.menu_prepravi_jelo:

				Toast.makeText(this, "Kliknuo prepravi jelo", Toast.LENGTH_SHORT).show();
				return super.onOptionsItemSelected(item);


			case R.id.menu_obrisi_jelo:
				Toast.makeText(this, "Kliknuo obrisi jelo", Toast.LENGTH_SHORT).show();

				return super.onOptionsItemSelected(item);



			default:

				return super.onOptionsItemSelected(item);


		}




	}

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
