package pe.com.johnson.pablo.limago.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.ui.common.LimaGoFragment;

/**
 * Created by pjohnson on 18/01/17.
 */

public class SearchFragment extends LimaGoFragment implements SearchView {
    public static SearchFragment newInstance() {
        return newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
