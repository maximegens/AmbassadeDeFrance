package com.maximegens.ambassadedefrance.views;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.maximegens.ambassadedefrance.R;
import com.maximegens.ambassadedefrance.beans.Ambassade;

/**
 * Classe AmbassadeDetailFragment
 */
public class AmbassadeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Ambassade mItem;

    /**
     * Constructeur.
     */
    public AmbassadeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = getArguments().getParcelable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ambassade_detail_fragment, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.ambassade_detail)).setText(mItem.getNom());
        }

        return rootView;
    }
}
