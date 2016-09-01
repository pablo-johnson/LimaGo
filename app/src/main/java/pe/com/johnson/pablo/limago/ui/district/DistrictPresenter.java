package pe.com.johnson.pablo.limago.ui.district;

import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.models.District;
import pe.com.johnson.pablo.limago.ui.common.LimaGoPresenter;
import pe.com.johnson.pablo.limago.utils.RealmClient;

/**
 * Created by Pablo on 31/08/16.
 */
public class DistrictPresenter extends LimaGoPresenter<DistrictView> {

    protected DistrictPresenter(DistrictView view) {
        super(view);
    }


    public void retrieveDistricts() {
        RealmResults<District> districts = RealmClient.getRealmClient().where(District.class).findAll();
        view.updateDistricts(districts);
        /*
        districts.addChangeListener(new RealmChangeListener<RealmResults<District>>() {
            @Override
            public void onChange(RealmResults<District> districts) {
                view.updateDistricts(districts);
            }
        });
        */

    }
}
