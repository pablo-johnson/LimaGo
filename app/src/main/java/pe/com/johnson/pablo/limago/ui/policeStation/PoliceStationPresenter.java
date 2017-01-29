package pe.com.johnson.pablo.limago.ui.policeStation;

import javax.inject.Inject;

import io.realm.Realm;
import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.ui.common.LimaGoPresenter;

/**
 * Created by Pablo on 1/09/16.
 */
public class PoliceStationPresenter extends LimaGoPresenter<PoliceStationView> {

    @Inject
    Realm realm;

    protected PoliceStationPresenter(PoliceStationView view) {
        super(view);
    }

    public void retrievePoliceStations(String districtName) {
        District district = realm.where(District.class).equalTo("name", districtName).findFirst();
        view.updatePoliceStations(district.getPoliceStations());
    }
}
