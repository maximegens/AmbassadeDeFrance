package com.maximegens.ambassadedefrance.views;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.maximegens.ambassadedefrance.R;
import com.maximegens.ambassadedefrance.beans.Ambassade;
import com.maximegens.ambassadedefrance.beans.Ambassades;
import com.maximegens.ambassadedefrance.adapters.AdapterItemAmbassade;

/**
 * Classe AmbassadeListFragment.
 */
public class AmbassadeListFragment extends Fragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private int mActivatedPosition = ListView.INVALID_POSITION;
    ListView listeAmabassade;

    /**
     * Constructeur du Fragement.
     */
    public AmbassadeListFragment() {
    }

    // La Callback qui notifiera l'activity d'un changement.
    private Callbacks mCallbacks = sAmbassadeCallbacks;

    /**
     * Interface Callback.
     */
    public interface Callbacks {
         void onItemSelected(Ambassade ambassade);
    }

    /**
     * La methode de notification.
     */
    private static Callbacks sAmbassadeCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Ambassade ambassade) {
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myInflatedView = inflater.inflate(R.layout.liste_ambassades_view, container,false);

        listeAmabassade = (ListView)myInflatedView.findViewById(R.id.list_view_ambassades);
        AdapterItemAmbassade adapterListeAmbassade = new AdapterItemAmbassade(getActivity(),Ambassades.lesAmbassades);
        listeAmabassade.setAdapter(adapterListeAmbassade);

        listeAmabassade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mCallbacks.onItemSelected(Ambassades.lesAmbassades.get(position));
            }
        });

        return myInflatedView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sAmbassadeCallbacks;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialise et enregistrement la position de l'item selectionné
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Permet de rendre "activer" l'item qui à été cliqué par l'user et sauvegarder la position de l'item cliqué dans mActivatedPosition.
     * @param position : position de l'item cliqué.
     */
    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            listeAmabassade.setItemChecked(mActivatedPosition, false);
        } else {
            listeAmabassade.setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    /**
     * Active le mode "selectionné" pour un item. Permet de voir visuellement quel item est sélectionné ( uniquement pour twoPanel)
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        listeAmabassade.setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

}
