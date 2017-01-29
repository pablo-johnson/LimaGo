package pe.com.johnson.pablo.limago.view.ui.search;

import android.os.Bundle;

import butterknife.OnClick;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoActivity;

public class SearchActivity extends LimaGoActivity {

    @OnClick(R.id.searchback)
    public void onSearchBackClick() {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.searchContainer, SearchFragment.newInstance()).commit();
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search;
    }

}
