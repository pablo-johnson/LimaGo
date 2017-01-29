package pe.com.johnson.pablo.limago.view.ui.district;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.domain.models.District;
import pe.com.johnson.pablo.limago.view.ui.common.LimaGoPresenter;

/**
 * Created by Pablo on 31/08/16.
 */
public class DistrictPresenter extends LimaGoPresenter<DistrictView> {

    @Inject
    Realm realm;

    protected DistrictPresenter(DistrictView view) {
        super(view);
    }


    public void retrieveDistricts() {
        RealmResults<District> districts = realm.where(District.class).findAll();
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
