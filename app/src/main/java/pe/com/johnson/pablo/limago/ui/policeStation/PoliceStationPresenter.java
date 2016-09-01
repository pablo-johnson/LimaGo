package pe.com.johnson.pablo.limago.ui.policeStation;

import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.ui.common.LimaGoPresenter;
import pe.com.johnson.pablo.limago.utils.RealmClient;

/**
 * Created by Pablo on 1/09/16.
 */
public class PoliceStationPresenter extends LimaGoPresenter<PoliceStationView> {

    protected PoliceStationPresenter(PoliceStationView view) {
        super(view);
    }

    public void retrievePoliceStations(String districtName) {
        District district = RealmClient.getRealmClient().where(District.class).equalTo("name", districtName).findFirst();
        view.updatePoliceStations(district.getPoliceStations());
    }
}
