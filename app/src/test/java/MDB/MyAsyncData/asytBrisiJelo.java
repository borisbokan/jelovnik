package MDB.MyAsyncData;

import android.os.AsyncTask;

import MDB.dbmodel.Jelo;

/**
 * Created by borcha on 02.06.17..
 */

public class asytBrisiJelo extends AsyncTask<Jelo,Void,Jelo>{


    public asytBrisiJelo() {
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
