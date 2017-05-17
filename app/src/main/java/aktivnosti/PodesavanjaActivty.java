package aktivnosti;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import rs.aleph.android.example12.R;

/**
 * Created by androiddevelopment on 16.5.17..
 */

public class PodesavanjaActivty extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
