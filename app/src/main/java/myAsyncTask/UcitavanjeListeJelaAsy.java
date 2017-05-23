package myAsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

public class UcitavanjeListeJelaAsy extends AsyncTask<Void,Void,Void> {

    private Activity activity;


    public UcitavanjeListeJelaAsy(Activity _act) {
        super();
        this.activity = _act;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity, "Pocetak ucitavanja liste jela", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void[] objects) {

        try {
            Thread.sleep(6000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    protected void onPostExecute(Void o) {
        super.onPostExecute(o);

        Toast.makeText(activity, "Zavrseno citavanja liste jela", Toast.LENGTH_SHORT).show();
    }

}