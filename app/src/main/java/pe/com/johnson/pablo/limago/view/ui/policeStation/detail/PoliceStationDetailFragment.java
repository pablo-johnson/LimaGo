package pe.com.johnson.pablo.limago.view.ui.policeStation.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.johnson.pablo.limago.LimaGoApplication;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.domain.models.District;
import pe.com.johnson.pablo.limago.domain.models.PoliceStation;
import pe.com.johnson.pablo.limago.view.ui.common.LocationFragment;

public class PoliceStationDetailFragment extends LocationFragment implements OnMapReadyCallback, PoliceStationDetailView {

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

    private PoliceStationDetailPresenter policeStationPresenter;
    private PoliceStation policeStation;
    private GoogleMap map;

    public PoliceStationDetailFragment() {
        // Required empty public constructor
    }

    public static PoliceStationDetailFragment newInstance(String policeStationName, String district) {
        PoliceStationDetailFragment fragment = new PoliceStationDetailFragment();
        Bundle args = new Bundle();
        args.putString(PoliceStation.NAME, policeStationName);
        args.putString(District.NAME, district);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        policeStationPresenter = new PoliceStationDetailPresenter(this);
        ((LimaGoApplication) getActivity().getApplication()).getNetComponent().inject(policeStationPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_police_station_detail, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        policeStationPresenter.retrievePoliceStation(getArguments().getString(PoliceStation.NAME));
        return view;
    }

    private void setUpViews(PoliceStation policeStation) {
        nameView.setText(policeStation.getName());
        addressView.setText(policeStation.getAddress());
        phoneView.setText(policeStation.getTelephone());
        fragmentListener.setTitle(getArguments().getString(District.NAME));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this); //when you already implement OnMapReadyCallback in your fragment
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        requestPermissions();
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.setMyLocationEnabled(true);
        MapsInitializer.initialize(this.getActivity());
        map = googleMap;
    }

    @Override
    public void updatePoliceStation(PoliceStation policeStation) {
        this.policeStation = policeStation;
        if (!policeStationHasLocation()) {
            mapView.setVisibility(View.GONE);
        }
        setUpViews(policeStation);
    }

    @Override
    protected void onLocationConnected(LatLng myLocation) {
        zoomMap(myLocation);
        addPoliceStationMarker();
    }

    private void addPoliceStationMarker() {
        if (policeStationHasLocation()) {
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.police_station))
                    .position(new LatLng(Double.parseDouble(policeStation.getLatitude()), Double.parseDouble(policeStation.getLongitude())))
                    .title(policeStation.getName()));
            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    return true;
                }
            });
        }
    }

    private boolean policeStationHasLocation() {
        return !policeStation.getLatitude().isEmpty() && !policeStation.getLongitude().isEmpty();
    }

    private void zoomMap(LatLng myLocation) {
        if (policeStationHasLocation()) {
            LatLngBounds bounds = new LatLngBounds.Builder()
                    .include(myLocation)
                    .include(new LatLng(Double.parseDouble(policeStation.getLatitude()), Double.parseDouble(policeStation.getLongitude()))).build();

            Point displaySize = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(displaySize);

            map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_call) {
            if (policeStation.getTelephone().split("-").length == 1) {
                callNumber(policeStation.getTelephone());
            } else {
                showChooseNumberDialog(policeStation.getTelephone().split(" - "));
            }
        } else if (item.getItemId() == R.id.action_go) {
            showRouteOnGoogleMaps();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRouteOnGoogleMaps() {
        String uri = "geo:0,0?q=" + android.net.Uri.encode(String.format("%s@%s,%s",
                policeStation.getName(), policeStation.getLatitude(), policeStation.getLongitude()), "UTF-8");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    private void showChooseNumberDialog(final String[] numbers) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.entityNumbers)
                .setItems(numbers, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        callNumber(numbers[which]);
                    }
                });
        builder.create().show();
    }

    private void callNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        startActivity(intent);
    }
}
