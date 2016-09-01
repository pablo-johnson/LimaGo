package pe.com.johnson.pablo.limago.ui.landing;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.ui.common.LimaGoActivity;
import pe.com.johnson.pablo.limago.ui.district.DistrictFragment;
import pe.com.johnson.pablo.limago.utils.PreferencesManager;

public class LandingActivity extends LimaGoActivity implements LandingView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LandingPresenter landingPresenter = new LandingPresenter(this);
        if (!PreferencesManager.get().isDataLoaded()) {
            try {
                landingPresenter.loadInitialData(getAssets().open("comisarias/comisarias.json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DistrictFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
