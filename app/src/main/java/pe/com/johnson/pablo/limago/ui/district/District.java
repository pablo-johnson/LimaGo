package pe.com.johnson.pablo.limago.ui.district;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import pe.com.johnson.pablo.limago.Comisaria;

/**
 * Created by Pablo on 30/08/16.
 */
public class District extends RealmObject {

    @SerializedName("nombre")
    private String name;
    @SerializedName("comisarias")
    private RealmList<Comisaria> comisarias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Comisaria> getComisarias() {
        return comisarias;
    }

    public void setComisarias(RealmList<Comisaria> comisarias) {
        this.comisarias = comisarias;
    }
}
