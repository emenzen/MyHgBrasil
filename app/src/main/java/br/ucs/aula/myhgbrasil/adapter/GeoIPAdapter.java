package br.ucs.aula.myhgbrasil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.model.Geoip;

public class GeoIPAdapter extends RecyclerView.Adapter<GeoIPAdapter.ViewHolder>{
    private final List<Geoip> geoipList;
    private int rowLayout;
    private Context context;

    public GeoIPAdapter(List<Geoip> geoipList) {
        this.geoipList = geoipList;
    }

    public GeoIPAdapter(List<Geoip> geoipList, int rowLayout, Context context) {
        this.geoipList = geoipList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_geoip,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(geoipList.get(position));
    }

    @Override
    public int getItemCount() {
        return geoipList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txGeoip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txGeoip = itemView.findViewById(R.id.txtGeoip);
        }

        private void setData(Geoip geoip) {
            txGeoip.setText(geoip.getCity()+"-"+geoip.getRegionCode()+","+geoip.getCountryName());
        }

        public void onClick(View view) {
        }
    }

}
