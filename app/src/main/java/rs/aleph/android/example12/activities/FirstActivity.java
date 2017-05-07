package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Jelo;
import model.Kategorija;
import rs.aleph.android.example12.R;

// Each activity extends Activity class
public class FirstActivity extends Activity {

	public static final String DETALJI_POZ_KEY="position";

	private ExpandableListAdapter exAdapter;
	private String[] kategorije;

	private ArrayList<Jelo> jela;
	public static AdapterJela adJela;

	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Each lifecycle method should call the method it overrides
		super.onCreate(savedInstanceState);
		// setContentView method draws UI
		setContentView(R.layout.activity_main);
		ListView lvJela=(ListView)findViewById(R.id.lvJela);

		//Iz resursa array
		kategorije=getResources().getStringArray(R.array.kategorije);

		//Unos podataka (imitacija povlacenja podataka iz DB)
		inicirajPodatke();

		//ArrayAdapter<String> kate=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,kategorije);

		adJela=new AdapterJela(this,R.layout.stavka_liste_jela,jela);
		lvJela.setAdapter(adJela);


		lvJela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				Intent intdetalji=new Intent(FirstActivity.this,SecondActivity.class);
				intdetalji.putExtra(DETALJI_POZ_KEY,i);

				startActivity(intdetalji);
			}
		});



	}


	private void inicirajPodatke(){


		//Stavljam u kolekciju generickog tipa, kategorija.
		ArrayList<Kategorija> obKategorije=new ArrayList<>();
		for (String stavka : kategorije) {
			obKategorije.add(new Kategorija(stavka));

		}



		Jelo jelo1=new Jelo("cevapi.jpg","Cevapi-mali","Cevapi mala porcija, 5 komada",385.00,"Mleveno meso(govedje i svinjsko meso), Beli luk...",250.00f);
			jelo1.setKategorija(obKategorije.get(1));



		Jelo jelo2=new Jelo("corba.jpg","Prolecna corba","Sargarepa corba",180.00,"Mlevena (pasirana) sargarepa, pavlaka, persun...",250.00f);
			jelo2.setKategorija(obKategorije.get(0));


		Jelo jelo3=new Jelo("krempita.jpg","Krempita","Kremasti kolac",220.00," Lisnato blok testo , mleka, vode, brašno, gustin, secer pavlaka...",125.00f);
			jelo3.setKategorija(obKategorije.get(2));


		Jelo jelo4=new Jelo("kuvani_pasulj.jpeg","Pasulj","Kuvani pasulj prebranac",245.00,"Pasulj prebranac, paprika, brasno, sargarepa, krompir, luk...",300.00f);
			jelo4.setKategorija(obKategorije.get(0));



		Jelo jelo5=new Jelo("pljeskavica.jpg","Pljeskavica","Leskovacka pljeskvica",210.00,"Mleveno svinjsko meso, lepinja, soja, biber, paprika, luk...",165.00f);
			jelo5.setKategorija(obKategorije.get(1));


		Jelo jelo6=new Jelo("riba_sa_rostilja.jpg","Smudj","Grilovani smuđ",430.00,"Kriške smudja, krompir, blitva, limun...",250.00f);
			jelo6.setKategorija(obKategorije.get(1));


		Jelo jelo7=new Jelo("rostilj_kobasica.jpg","Kobasica","Roštilj kobasica",350.00,"Svinjsko meso, paprika, luk, biber...",200.00f);
			jelo7.setKategorija(obKategorije.get(1));


		Jelo jelo8=new Jelo("sarma.jpg","Sarma","Domaca sarma",260.00,"Mleveno svinjsko meso, pirinac, biber, luk,kiseli kupus..",280.00f);
			jelo8.setKategorija(obKategorije.get(0));


		Jelo jelo9=new Jelo("sladoled.jpg","Sladoled","Sladoled porcija (3 kugle)",160.00,"Mleko, secer, voda, aroma....",200.00f);
			jelo9.setKategorija(obKategorije.get(2));


		//Stavljam sva unesena jela u Kategorija klasu radi povezivanja

			jela=new ArrayList<>();
			jela.add(jelo1);
			jela.add(jelo2);
			jela.add(jelo3);
			jela.add(jelo4);
			jela.add(jelo5);
			jela.add(jelo6);
			jela.add(jelo7);
			jela.add(jelo8);
			jela.add(jelo9);


	}


}
