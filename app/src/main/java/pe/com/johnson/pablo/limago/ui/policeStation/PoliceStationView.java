package pe.com.johnson.pablo.limago.ui.policeStation;

import io.realm.RealmList;
import pe.com.johnson.pablo.limago.models.PoliceStation;
import pe.com.johnson.pablo.limago.ui.common.LimaGoView;

/**
 * Created by Pablo on 1/09/16.
 */
public interface PoliceStationView extends LimaGoView {
    void updatePoliceStations(RealmList<PoliceStation> policeStations);
}
