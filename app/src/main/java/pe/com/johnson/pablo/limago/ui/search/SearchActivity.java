package pe.com.johnson.pablo.limago.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.ui.common.LimaGoActivity;

public class SearchActivity extends LimaGoActivity {

    @OnClick(R.id.searchback)
    public void onSearchBackClick() {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.searchContainer, SearchFragment.newInstance()).commit();
        }
    }

}
