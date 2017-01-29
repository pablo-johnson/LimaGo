package pe.com.johnson.pablo.limago.di;

import javax.inject.Singleton;

import dagger.Component;
import pe.com.johnson.pablo.limago.ui.district.DistrictPresenter;
import pe.com.johnson.pablo.limago.ui.landing.LandingActivity;
import pe.com.johnson.pablo.limago.ui.landing.LandingPresenter;
import pe.com.johnson.pablo.limago.ui.policeStation.PoliceStationPresenter;
import pe.com.johnson.pablo.limago.ui.policeStation.detail.PoliceStationDetailPresenter;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(LandingActivity activity);

    void inject(DistrictPresenter districtPresenter);

    void inject(PoliceStationPresenter policeStationPresenter);

    void inject(PoliceStationDetailPresenter policeStationDetailPresenter);

    void inject(LandingPresenter landingPresenter);
}
