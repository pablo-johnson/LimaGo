package pe.com.johnson.pablo.limago.view.ui.policeStation;

import io.realm.RealmList;
import pe.com.johnson.pablo.limago.domain.models.PoliceStation;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoView;

/**
 * Created by Pablo on 1/09/16.
 */
public interface PoliceStationView extends LimaGoView {
    void updatePoliceStations(RealmList<PoliceStation> policeStations);
}
