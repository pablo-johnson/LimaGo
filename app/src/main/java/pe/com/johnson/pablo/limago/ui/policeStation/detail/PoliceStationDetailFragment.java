package pe.com.johnson.pablo.limago.ui.policeStation.detail;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.models.District;
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

    public static PoliceStationDetailFragment newInstance(PoliceStation policeStation, String district) {
        PoliceStationDetailFragment fragment = new PoliceStationDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(PoliceStation.POLICE_STATION, policeStation);
        args.putString(District.NAME, district);
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
        setUpViews();
        setHasOptionsMenu(true);
        return view;
    }

    private void setUpViews() {
        policeStation = (PoliceStation) getArguments().getSerializable(PoliceStation.POLICE_STATION);
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
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%s,%s (%s)",
                policeStation.getLatitude(), policeStation.getLongitude(), policeStation.getName());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            //TODO: Do Something
        }
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
