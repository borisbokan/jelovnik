package MDB.MyAsyncData;

import android.content.Context;
import android.os.AsyncTask;

import MDB.MySqlJelo;
import MDB.dbmodel.Jelo;

/**
 * Created by borcha on 02.06.17..
 */

public class asytPrepraviJelo extends AsyncTask<Jelo,Void,Integer> implements MySqlJelo.IPrepraviJelo{


    private final Context cont;

    public asytPrepraviJelo(Context _cont) {
        super();
        this.cont=_cont;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Jelo... jelos) {
        //Todo snimi u bazu
        return null;

    }

    @Override
    protected void onPostExecute(Integer rez) {
        super.onPostExecute(rez);
        OnPrepraviJelo(rez);
    }

    @Override
    public void OnPrepraviJelo(int uspesno) {

    }
}
