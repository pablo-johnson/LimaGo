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

import javax.inject.Inject;

import pe.com.johnson.pablo.limago.LimaGoApplication;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.interfaces.SharedPreferencesHelper;
import pe.com.johnson.pablo.limago.ui.search.SearchActivity;
import pe.com.johnson.pablo.limago.ui.common.LimaGoActivity;
import pe.com.johnson.pablo.limago.ui.district.DistrictFragment;

public class LandingActivity extends LimaGoActivity implements LandingView {

    private Toolbar toolbar;

    @Inject
    SharedPreferencesHelper sharedPreferencesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        initializeDagger();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar(toolbar);
        LandingPresenter landingPresenter = new LandingPresenter(this);

        ((LimaGoApplication)getApplication()).getNetComponent().inject(landingPresenter);
        if (!sharedPreferencesApi.isInitialDataLoaded()) {
            loadInitialDataFile(landingPresenter);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, DistrictFragment.newInstance()).commit();
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_landing;
    }

    private void initializeDagger() {
        LimaGoApplication application = (LimaGoApplication) getApplication();
        application.getNetComponent().inject(this);
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
