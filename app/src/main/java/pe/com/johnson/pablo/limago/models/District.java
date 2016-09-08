package pe.com.johnson.pablo.limago.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pablo on 30/08/16.
 */
public class District extends RealmObject {

    public static final String NAME = "districtName";
    @SerializedName("nombre")
    @PrimaryKey
    private String name;
    @SerializedName("comisarias")
    private RealmList<PoliceStation> policeStations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<PoliceStation> getPoliceStations() {
        return policeStations;
    }

    public void setPoliceStations(RealmList<PoliceStation> policeStations) {
        this.policeStations = policeStations;
    }
}
