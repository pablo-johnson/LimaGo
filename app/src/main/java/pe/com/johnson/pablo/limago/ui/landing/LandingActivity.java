package pe.com.johnson.pablo.limago.ui.landing;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.ui.search.SearchActivity;
import pe.com.johnson.pablo.limago.ui.common.LimaGoActivity;
import pe.com.johnson.pablo.limago.ui.district.DistrictFragment;
import pe.com.johnson.pablo.limago.utils.PreferencesManager;

public class LandingActivity extends LimaGoActivity implements LandingView {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar(toolbar);
        LandingPresenter landingPresenter = new LandingPresenter(this);
        if (!PreferencesManager.get().isDataLoaded()) {
            loadInitialDataFile(landingPresenter);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DistrictFragment.newInstance()).commit();
        }
    }

    private void loadInitialDataFile(LandingPresenter landingPresenter) {
        try {
            landingPresenter.loadInitialData(getAssets().open("comisarias/comisarias.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                View searchMenuView = toolbar.findViewById(R.id.menu_search);
                Bundle options = ActivityOptions.makeSceneTransitionAnimation(this, searchMenuView,
                        getString(R.string.transition_search_back)).toBundle();
                startActivityForResult(new Intent(this, SearchActivity.class), 0, options);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
