package com.maximegens.ambassadedefrance;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.maximegens.ambassadedefrance.Beans.Ambassade;
import com.maximegens.ambassadedefrance.Beans.Ambassades;

/**
 * A list fragment representing a list of LesAmbassades. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link AmbassadeDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class AmbassadeListFragment extends Fragment {

    ListView listeAmabassade;

    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private int mActivatedPosition = ListView.INVALID_POSITION;


    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sAmbassadeCallbacks;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
         void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sAmbassadeCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AmbassadeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myInflatedView = inflater.inflate(R.layout.liste_ambassades_view, container,false);
        listeAmabassade = (ListView)myInflatedView.findViewById(R.id.list_view_ambassades);
        listeAmabassade.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<Ambassade> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, android.R.id.text1, Ambassades.lesAmbassades);
        listeAmabassade.setAdapter(adapter);

        listeAmabassade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mCallbacks.onItemSelected(String.valueOf(Ambassades.lesAmbassades.get(position).getId()));
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
        // Activities containing this fragment must implement its callbacks.
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
            // Serialize and persist the activated item position.
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

}
