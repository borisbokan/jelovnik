package MyAsyncData;

import android.os.AsyncTask;

import mdb.dbmodel.Jelo;

/**
 * Created by borcha on 02.06.17..
 */

public class asytSnimiNovoJelo extends AsyncTask<Jelo,Void,Jelo>{


    public asytSnimiNovoJelo() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Jelo jelo) {
        super.onPostExecute(jelo);
    }

    @Override
    protected Jelo doInBackground(Jelo... jelos) {
        return null;
    }
}
