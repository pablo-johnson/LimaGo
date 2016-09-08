package pe.com.johnson.pablo.limago.ui.district;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.adapters.DistrictAdapter;
import pe.com.johnson.pablo.limago.interfaces.RecyclerViewClickInterface;
import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.ui.common.LimaGoFragment;
import pe.com.johnson.pablo.limago.ui.policeStation.PoliceStationFragment;
import pe.com.johnson.pablo.limago.ui.policeStation.detail.PoliceStationDetailFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class DistrictFragment extends LimaGoFragment implements DistrictView {

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.districtList)
    RecyclerView districtRecyclerView;

    private DistrictPresenter districtPresenter;
    private DistrictAdapter mAdapter;

    public DistrictFragment() {
    }

    public static Fragment newInstance() {
        return new DistrictFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        districtPresenter = new DistrictPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_district, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        districtRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DistrictAdapter(null, new RecyclerViewClickInterface<District>() {
            @Override
            public void onItemClick(District district) {
                if (district.getPoliceStations().size() == 1) {
                    fragmentListener.replaceFragment(PoliceStationDetailFragment.newInstance(district.getPoliceStations().first().getName(),
                            district.getName()), true);
                } else {
                    fragmentListener.replaceFragment(PoliceStationFragment.newInstance(district.getName()), true);
                }
            }
        });
        districtPresenter.retrieveDistricts();
        districtRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void updateDistricts(RealmResults<District> districts) {
        mAdapter.updateData(districts);
    }
}
