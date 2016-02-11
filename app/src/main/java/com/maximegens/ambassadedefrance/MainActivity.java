package com.maximegens.ambassadedefrance;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maximegens.ambassadedefrance.Beans.Ambassade;

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity
implements AmbassadeListFragment.Callbacks {

    // Permet de savoir si l'application contient deux panel.
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        if (findViewById(R.id.ambassade_detail_container) != null) {
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((AmbassadeListFragment) getFragmentManager()
                    .findFragmentById(R.id.ambassade_list))
                    .setActivateOnItemClick(true);
        }
    }

    /**
     * CallBack permettant de recevoir l'evenement de changement depuis le fragment.
     * @param ambassade : l'ambassade passé en paramétre.
     */
    @Override
    public void onItemSelected(Ambassade ambassade) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(AmbassadeDetailFragment.ARG_ITEM_ID, ambassade);
            AmbassadeDetailFragment fragment = new AmbassadeDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.ambassade_detail_container, fragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(this, AmbassadeDetailActivity.class);
            detailIntent.putExtra(AmbassadeDetailFragment.ARG_ITEM_ID, ambassade);
            startActivity(detailIntent);
        }
    }
}
