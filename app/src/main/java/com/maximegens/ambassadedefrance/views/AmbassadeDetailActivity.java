package com.maximegens.ambassadedefrance.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import com.maximegens.ambassadedefrance.MainActivity;
import com.maximegens.ambassadedefrance.R;

/**
 * Classe AmbassadeDetailActivity.
 */
public class AmbassadeDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambassade_detail_activity);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(AmbassadeDetailFragment.ARG_ITEM_ID,
                    getIntent().getParcelableExtra(AmbassadeDetailFragment.ARG_ITEM_ID));
            AmbassadeDetailFragment fragment = new AmbassadeDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.ambassade_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
