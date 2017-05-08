package rs.aleph.android.example12.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.sax.SAXTransformerFactory;

import model.Jelo;
import model.JelovnikExpandAdapter;
import model.Kategorija;
import model.Sastojak;
import rs.aleph.android.example12.R;

// Each activity extends Activity class
public class FirstActivity extends Activity {

	public static final String DETALJI_POZ_KEY="position";
	public static final String DETALJI_GROUP_POZ_KEY="gro_position";



	private String[] kategorije;

	private ArrayList<Jelo> jela;
	private ArrayList<Kategorija> obKategorije;

	public static JelovnikExpandAdapter expAdapterJelovnik;

	// onCreate method is a lifecycle method called when he activity is starting
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Each lifecycle method should call the method it overrides
		super.onCreate(savedInstanceState);
		// setContentView method draws UI
		setContentView(R.layout.activity_main);
		ExpandableListView exlvJela=(ExpandableListView)findViewById(R.id.exlvJela);

		//Iz resursa array
		kategorije=getResources().getStringArray(R.array.kategorije);

		//Stavljam u kolekciju generickog tipa, kategorija.
		obKategorije=new ArrayList<>();
		for (String stavka : kategorije) {
			obKategorije.add(new Kategorija(stavka));

		}

		//Unos podataka (imitacija povlacenja podataka iz DB)
		inicirajPodatke();


		HashMap<String, List<Jelo>> expandableListDetail = new HashMap<String, List<Jelo>>();


		expandableListDetail.put(obKategorije.get(0).getNaziv(),obKategorije.get(0).getJela());
		expandableListDetail.put(obKategorije.get(1).getNaziv(),obKategorije.get(1).getJela());
		expandableListDetail.put(obKategorije.get(2).getNaziv(),obKategorije.get(2).getJela());

		expAdapterJelovnik=new JelovnikExpandAdapter(this,new ArrayList<String>(expandableListDetail.keySet()),expandableListDetail);

		exlvJela.setAdapter(expAdapterJelovnik);
		exlvJela.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int i) {
				Toast.makeText(getBaseContext(),"Otvori grupu",Toast.LENGTH_SHORT).show();
			}
		});


		exlvJela.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int i) {
				Toast.makeText(getBaseContext(),"Zatvori grupu",Toast.LENGTH_SHORT).show();
			}
		});

		exlvJela.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView expandableListView, View view, int groPos, int position, long l) {
				Intent intdetalji=new Intent(FirstActivity.this,SecondActivity.class);
				intdetalji.putExtra(DETALJI_POZ_KEY,position);
				intdetalji.putExtra(DETALJI_GROUP_POZ_KEY,groPos);
				startActivity(intdetalji);
				return false;
			}
		});



		/*exlvJela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				Intent intdetalji=new Intent(FirstActivity.this,SecondActivity.class);
				intdetalji.putExtra(DETALJI_POZ_KEY,i);

				startActivity(intdetalji);
			}
		});*/

	}


	private void inicirajPodatke(){



		//Moguci sastojci
		Sastojak sol=new Sastojak("Sol",4.34f,99.99f,"gr");
		Sastojak biber=new Sastojak("Biber",1.25f,99.99f,"gr");
		Sastojak paprika_slatka=new Sastojak("Paprika slatka",1.00f,100.00f,"gr");
		Sastojak paprika_ljuta=new Sastojak("Paprika ljuta",0.89f,100.00f,"gr");
		Sastojak brasno_belo=new Sastojak("Brasno belo",0.89f,100.00f,"gr");
		Sastojak brasno_kukurzno=new Sastojak("Brasno kukurzno",0.89f,100.00f,"gr");
		Sastojak secer=new Sastojak("Secer",0.89f,100.00f,"gr");
		Sastojak soja=new Sastojak("Soja",0.89f,100.00f,"gr");
		Sastojak kim=new Sastojak("Kim",1.22f,100.00f,"gr");
		Sastojak limun=new Sastojak("Limun",5.65f,100.00f,"gr");
		Sastojak mlev_svinj_meso=new Sastojak("Mleveno svinjsko meso",45.12f,100.00f,"gr");
		Sastojak mlev_govedj_meso=new Sastojak("Mleveno govedina",37.29f,100.00f,"gr");
		Sastojak pilece_meso=new Sastojak("Pilece meso",28.54f,100.00f,"gr");
		Sastojak kupus_kis=new Sastojak("Kupus kiseli",2.29f,1100.00f,"gr");
		Sastojak kupus_sla=new Sastojak("Kupus slatki",8.12f,100.00f,"gr");
		Sastojak mleko=new Sastojak("Mleko",2.34f,100.00f,"gr");
		Sastojak blitva=new Sastojak("Blitva",9.34f,100.00f,"gr");
		Sastojak krompir=new Sastojak("Krompir",12.12f,100.00f,"gr");
		Sastojak riza=new Sastojak("Riza",15.23f,100.00f,"gr");
		Sastojak origano=new Sastojak("Origano",0.89f,100.00f,"gr");
		Sastojak cimet=new Sastojak("Cimet",3.42f,100.00f,"gr");
		Sastojak beli_luk=new Sastojak("Beli luka",11.23f,100.00f,"gr");
		Sastojak crni_luk=new Sastojak("Crni luka",23.22f,100.00f,"gr");
		Sastojak kecap=new Sastojak("Kecap",23.23f,100.00f,"gr");
		Sastojak paradajz_svezi=new Sastojak("Paradaz svezi",3.34f,100.00f,"gr");
		Sastojak kako=new Sastojak("Kakao",12.34f,100.00f,"");
		Sastojak vanila=new Sastojak("Vanila",23.37f,100.00f,"gr");
		Sastojak jaja=new Sastojak("Jaja",22.50f,100.00f,"gr");
		Sastojak margarin=new Sastojak("Margarin",45.23f,100.00f,"gr");
		Sastojak sargarepa=new Sastojak("Sargarepa",12.19f,100.00f,"gr");
		Sastojak mrkva=new Sastojak("Mrkva",34.50f,100.00f,"gr");
		Sastojak pavlaka=new Sastojak("Pavlaka",21.15f,100.00f,"gr");
		Sastojak persun =new Sastojak("Persun",0.14f,100.00f,"gr");
		Sastojak lisnato_testo =new Sastojak("Lisnato testo",0.5f,100.00f,"gr");
		Sastojak voda =new Sastojak("Voda",2.18f,100.00f,"gr");
		Sastojak gustin =new Sastojak("Gustin",7.10f,100.00f,"gr");
		Sastojak pasulj=new Sastojak("Pasulj",35.19f,100.00f,"gr");
		Sastojak lepinja=new Sastojak("Lepinja",12.52f,100,"gr");
		Sastojak aroma_vanila=new Sastojak("Aroma vanila",17.50f,100,"gr");
		Sastojak aroma_cokolada=new Sastojak("Aroma cokolada",19.55f,100,"gr");

		Jelo jelo1=new Jelo("cevapi.jpg","Cevapi-mali","Cevapi mala porcija, 5 komada",385.00,250.00f);
		jelo1.setKategorija(obKategorije.get(1));
		obKategorije.get(1).addJelo(jelo1);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo1
		jelo1.addArlSastojciKalorijskeVrednosti(mlev_govedj_meso);
		jelo1.addArlSastojciKalorijskeVrednosti(mlev_svinj_meso);
		jelo1.addArlSastojciKalorijskeVrednosti(beli_luk);
		jelo1.addArlSastojciKalorijskeVrednosti(sol);
		jelo1.addArlSastojciKalorijskeVrednosti(biber);

		Jelo jelo2=new Jelo("corba.jpg","Prolecna corba","Sargarepa corba",180.00,250.00f);
		jelo2.setKategorija(obKategorije.get(0));
		obKategorije.get(0).addJelo(jelo2);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo2
		jelo2.addArlSastojciKalorijskeVrednosti(sargarepa);
		jelo2.addArlSastojciKalorijskeVrednosti(pavlaka);
		jelo2.addArlSastojciKalorijskeVrednosti(persun);

		Jelo jelo3=new Jelo("krempita.jpg","Krempita","Kremasti kolac",220.00,125.00f);
		jelo3.setKategorija(obKategorije.get(2));
		obKategorije.get(2).addJelo(jelo3);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo3
		jelo3.addArlSastojciKalorijskeVrednosti(lisnato_testo);
		jelo3.addArlSastojciKalorijskeVrednosti(mleko);
		jelo3.addArlSastojciKalorijskeVrednosti(voda);
		jelo3.addArlSastojciKalorijskeVrednosti(brasno_belo);
		jelo3.addArlSastojciKalorijskeVrednosti(gustin);
		jelo3.addArlSastojciKalorijskeVrednosti(secer);
		jelo3.addArlSastojciKalorijskeVrednosti(pavlaka);


		Jelo jelo4=new Jelo("kuvani_pasulj.jpeg","Pasulj","Kuvani pasulj",245.00,300.00f);
		jelo4.setKategorija(obKategorije.get(0));
		obKategorije.get(0).addJelo(jelo4);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo4
		jelo4.addArlSastojciKalorijskeVrednosti(pasulj);
		jelo4.addArlSastojciKalorijskeVrednosti(paprika_ljuta);
		jelo4.addArlSastojciKalorijskeVrednosti(brasno_belo);
		jelo4.addArlSastojciKalorijskeVrednosti(sargarepa);
		jelo4.addArlSastojciKalorijskeVrednosti(krompir);
		jelo4.addArlSastojciKalorijskeVrednosti(crni_luk);

		Jelo jelo5=new Jelo("pljeskavica.jpg","Pljeskavica","Leskovacka pljeskvica",210.00,165.00f);
		jelo5.setKategorija(obKategorije.get(1));
		obKategorije.get(1).addJelo(jelo5);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo5
		jelo5.addArlSastojciKalorijskeVrednosti(mlev_svinj_meso);
		jelo5.addArlSastojciKalorijskeVrednosti(mlev_govedj_meso);
		jelo5.addArlSastojciKalorijskeVrednosti(lepinja);
		jelo5.addArlSastojciKalorijskeVrednosti(soja);
		jelo5.addArlSastojciKalorijskeVrednosti(biber);
		jelo5.addArlSastojciKalorijskeVrednosti(paprika_ljuta);
		jelo5.addArlSastojciKalorijskeVrednosti(crni_luk);

		Jelo jelo6=new Jelo("riba_sa_rostilja.jpg","Smudj","Grilovani smuđ",430.00,250.00f);
		jelo6.setKategorija(obKategorije.get(1));
		obKategorije.get(1).addJelo(jelo6);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo6
		jelo6.addArlSastojciKalorijskeVrednosti(krompir);
		jelo6.addArlSastojciKalorijskeVrednosti(blitva);
		jelo6.addArlSastojciKalorijskeVrednosti(limun);


		Jelo jelo7=new Jelo("rostilj_kobasica.jpg","Kobasica","Roštilj kobasica",350.00,200.00f);
		jelo7.setKategorija(obKategorije.get(1));
		obKategorije.get(1).addJelo(jelo7);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo7
		jelo7.addArlSastojciKalorijskeVrednosti(mlev_svinj_meso);
		jelo7.addArlSastojciKalorijskeVrednosti(paprika_ljuta);
		jelo7.addArlSastojciKalorijskeVrednosti(paprika_slatka);
		jelo7.addArlSastojciKalorijskeVrednosti(crni_luk);
		jelo7.addArlSastojciKalorijskeVrednosti(beli_luk);

		Jelo jelo8=new Jelo("sarma.jpg","Sarma","Domaca sarma",260.00,280.00f);
		jelo8.setKategorija(obKategorije.get(0));
		obKategorije.get(0).addJelo(jelo8);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo8
		jelo8.addArlSastojciKalorijskeVrednosti(mlev_svinj_meso);
		jelo8.addArlSastojciKalorijskeVrednosti(riza);
		jelo8.addArlSastojciKalorijskeVrednosti(biber);
		jelo8.addArlSastojciKalorijskeVrednosti(crni_luk);
		jelo8.addArlSastojciKalorijskeVrednosti(beli_luk);
		jelo8.addArlSastojciKalorijskeVrednosti(kupus_kis);


		Jelo jelo9=new Jelo("sladoled.jpg","Sladoled","Sladoled porcija (3 kugle)",160.00,200.00f);
		jelo9.setKategorija(obKategorije.get(2));
		obKategorije.get(2).addJelo(jelo9);//Dodavanje jela u kategoriju
		//Dodavanje sastojaka u jelo9
		jelo9.addArlSastojciKalorijskeVrednosti(mleko);
		jelo9.addArlSastojciKalorijskeVrednosti(secer);
		jelo9.addArlSastojciKalorijskeVrednosti(voda);
		jelo9.addArlSastojciKalorijskeVrednosti(aroma_vanila);
		jelo9.addArlSastojciKalorijskeVrednosti(aroma_cokolada);


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
