package pe.com.johnson.pablo.limago.view.ui.district;

import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.domain.models.District;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoView;

/**
 * Created by Pablo on 31/08/16.
 */
public interface DistrictView extends LimaGoView {
    void updateDistricts(RealmResults<District> districts);
}
