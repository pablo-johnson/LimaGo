package pe.com.johnson.pablo.limago.view.ui.policeStation.detail;

import javax.inject.Inject;

import io.realm.Realm;
import pe.com.johnson.pablo.limago.domain.models.PoliceStation;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoPresenter;

/**
 * Created by Pablo on 1/09/16.
 */
public class PoliceStationDetailPresenter extends LimaGoPresenter<PoliceStationDetailView> {

    @Inject
    Realm realm;

    protected PoliceStationDetailPresenter(PoliceStationDetailView view) {
        super(view);
    }

    public void retrievePoliceStation(String policeStationName) {
        PoliceStation policeStation = realm.where(PoliceStation.class).equalTo("name", policeStationName).findFirst();
        if (policeStation != null) {
            view.updatePoliceStation(policeStation);
        }
    }
}
