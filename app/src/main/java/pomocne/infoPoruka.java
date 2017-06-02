package pomocne;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import rs.aleph.android.jelovnik.R;

/**
 * Created by User on 10.5.2016.
 */
public class infoPoruka extends AlertDialog.Builder {

    static Context cont;
    static String naslov;
    static String poruka;

    public infoPoruka(Context context) {
        super(context);
    }

    public static AlertDialog.Builder newInstance(Context _context, String _naslov, String _poruka) {
        AlertDialog.Builder poru=new AlertDialog.Builder(_context);

        cont=_context;
        naslov=_naslov;
        poruka=_poruka;

       poru.setTitle(naslov);
       poru.setMessage(poruka);
       poru.setPositiveButton(cont.getString(R.string.ok), new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
               return;
           }
       }).show();

        return poru;
    }

    public void  prikazi(){
        show();

    }



}
