package pe.com.johnson.pablo.limago.ui.policeStation.detail;

import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.models.PoliceStation;
import pe.com.johnson.pablo.limago.ui.common.LimaGoPresenter;
import pe.com.johnson.pablo.limago.ui.policeStation.PoliceStationView;
import pe.com.johnson.pablo.limago.utils.RealmClient;

/**
 * Created by Pablo on 1/09/16.
 */
public class PoliceStationDetailPresenter extends LimaGoPresenter<PoliceStationDetailView> {

    protected PoliceStationDetailPresenter(PoliceStationDetailView view) {
        super(view);
    }

    public void retrievePoliceStation(String policeStationName) {
        PoliceStation policeStation = RealmClient.getRealmClient().where(PoliceStation.class).equalTo("name", policeStationName).findFirst();
        if (policeStation!=null){
            view.updatePoliceStation(policeStation);
        }
    }
}
