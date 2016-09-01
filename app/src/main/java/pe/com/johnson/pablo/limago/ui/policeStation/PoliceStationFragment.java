package pe.com.johnson.pablo.limago.ui.policeStation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.adapters.PoliceStationAdapter;
import pe.com.johnson.pablo.limago.interfaces.RecyclerViewClickInterface;
import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.models.PoliceStation;
import pe.com.johnson.pablo.limago.ui.common.LimaGoFragment;
import pe.com.johnson.pablo.limago.ui.policeStation.detail.PoliceStationDetailFragment;

public class PoliceStationFragment extends LimaGoFragment implements PoliceStationView {

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.policeStationList)
    RecyclerView policeRecyclerView;

    private PoliceStationPresenter policeStationPresenter;
    private PoliceStationAdapter mAdapter;

    public PoliceStationFragment() {
        // Required empty public constructor
    }

    public static PoliceStationFragment newInstance(String districtName) {
        PoliceStationFragment fragment = new PoliceStationFragment();
        Bundle args = new Bundle();
        args.putString(District.NAME, districtName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        policeStationPresenter = new PoliceStationPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_police_station, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        policeRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PoliceStationAdapter(null, new RecyclerViewClickInterface<PoliceStation>() {
            @Override
            public void onItemClick(PoliceStation policeStation) {
                fragmentListener.replaceFragment(PoliceStationDetailFragment.newInstance(policeStation), true);
            }
        });
        policeStationPresenter.retrievePoliceStations(getArguments().getString(District.NAME));
        policeRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void updatePoliceStations(RealmList<PoliceStation> policeStations) {
        mAdapter.updateData(policeStations);
    }
}
