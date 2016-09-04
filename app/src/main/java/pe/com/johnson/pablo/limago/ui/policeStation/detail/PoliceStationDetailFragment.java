package pe.com.johnson.pablo.limago.ui.policeStation.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.models.PoliceStation;
import pe.com.johnson.pablo.limago.ui.common.LimaGoFragment;

public class PoliceStationDetailFragment extends LimaGoFragment implements OnMapReadyCallback {

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.mapview)
    MapView mapView;
    @BindView(R.id.address)
    TextView addressView;
    @BindView(R.id.name)
    TextView nameView;
    @BindView(R.id.phone)
    TextView phoneView;

    private PoliceStation policeStation;

    public PoliceStationDetailFragment() {
        // Required empty public constructor
    }

    public static PoliceStationDetailFragment newInstance(PoliceStation policeStation) {
        PoliceStationDetailFragment fragment = new PoliceStationDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(PoliceStation.POLICE_STATION, policeStation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_police_station_detail, container, false);
        ButterKnife.bind(this, view);
        policeStation = (PoliceStation) getArguments().getSerializable(PoliceStation.POLICE_STATION);
        nameView.setText(policeStation.getName());
        addressView.setText(policeStation.getAddress());
        phoneView.setText(policeStation.getTelephone());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        //googleMap.setMyLocationEnabled(true);
        MapsInitializer.initialize(this.getActivity());

        LatLng policeStationLocation = new LatLng(Double.valueOf(policeStation.getLatitude()), Double.valueOf(policeStation.getLongitude()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(policeStationLocation));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
