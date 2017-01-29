package pe.com.johnson.pablo.limago.domain.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoliceStation extends RealmObject implements Serializable {

    public static final transient String POLICE_STATION = "policeStation";
    public static final String NAME = "policeStationName";

    @PrimaryKey
    @SerializedName("nombre")
    private String name;
    @SerializedName("direccion")
    private String address;
    @SerializedName("telefono")
    private String telephone;
    @SerializedName("latitud")
    private String latitude;
    @SerializedName("longitud")
    private String longitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
