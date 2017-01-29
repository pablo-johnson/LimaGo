package pe.com.johnson.pablo.limago.view.ui.policeStation.detail;

import pe.com.johnson.pablo.limago.domain.models.PoliceStation;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoView;

/**
 * Created by Pablo on 1/09/16.
 */
public interface PoliceStationDetailView extends LimaGoView {
    void updatePoliceStation(PoliceStation policeStation);
}
