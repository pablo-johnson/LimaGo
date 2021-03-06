package pe.com.johnson.pablo.limago.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import pe.com.johnson.pablo.limago.R;
import pe.com.johnson.pablo.limago.view.interfaces.RecyclerViewClickInterface;
import pe.com.johnson.pablo.limago.domain.models.District;

/**
 * Created by Pablo on 30/08/16.
 */
public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {

    private final RecyclerViewClickInterface mInterface;
    RealmResults<District> mDistricts;

    public DistrictAdapter(RealmResults<District> districts, RecyclerViewClickInterface districtInterface) {
        mDistricts = districts;
        mInterface = districtInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_district, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.districtName.setText(mDistricts.get(holder.getAdapterPosition()).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.onItemClick(mDistricts.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDistricts == null ? 0 : mDistricts.size();
    }

    public void updateData(RealmResults<District> districts) {
        mDistricts = districts;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.districtName)
        TextView districtName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
