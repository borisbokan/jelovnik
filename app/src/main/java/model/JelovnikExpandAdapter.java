package model;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import rs.aleph.android.jelovnik.R;

/**
 * Created by borcha on 08.05.17..
 */

public class JelovnikExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<Jelo>> expandableListDetail;

    public JelovnikExpandAdapter(Context _context, List<String> _expandableListTitle, HashMap<String, List<Jelo>> _expandableListDetail){

        this.expandableListDetail=_expandableListDetail;
        this.expandableListTitle=_expandableListTitle;
        this.context=_context;
    }


    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.expandableListDetail.get(this.expandableListTitle.get(i))
                .size();
    }

    @Override
    public Object getGroup(int i) {
        return this.expandableListTitle.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.expandableListDetail.get(this.expandableListTitle.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) view
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int lsPos, int expLsPos, boolean b, View view, ViewGroup viewGroup) {
        Jelo expandedListText =  (Jelo)getChild(lsPos,expLsPos);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.stavka_liste_jela, null);
        }
        TextView expandedListTextView = (TextView) view
                .findViewById(R.id.txtNazivJela_lista);
        expandedListTextView.setText(expandedListText.getNaziv());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
