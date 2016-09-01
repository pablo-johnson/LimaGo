package pe.com.johnson.pablo.limago.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.models.PoliceStation;

/**
 * Created by Pablo on 30/08/16.
 */
public class PoliceStationAdapter extends RecyclerView.Adapter<PoliceStationAdapter.ViewHolder> {

    RealmList<PoliceStation> mPoliceStations;

    public PoliceStationAdapter(RealmList<PoliceStation> districts) {
        mPoliceStations = districts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_police_station, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.policeStationName.setText(mPoliceStations.get(holder.getAdapterPosition()).getName());
    }

    @Override
    public int getItemCount() {
        return mPoliceStations == null ? 0 : mPoliceStations.size();
    }

    public void updateData(RealmList<PoliceStation> districts) {
        mPoliceStations = districts;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.districtName)
        TextView policeStationName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
