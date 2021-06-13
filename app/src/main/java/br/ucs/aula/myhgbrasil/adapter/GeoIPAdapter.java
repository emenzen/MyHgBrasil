package br.ucs.aula.myhgbrasil.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.activity.EditarTaxesActivity;
import br.ucs.aula.myhgbrasil.model.Geoip;
import br.ucs.aula.myhgbrasil.model.Taxes;


public class GeoIPAdapter extends RecyclerView.Adapter<GeoIPAdapter.ViewHolder> {
    private final List<Geoip> geoipsList;
    private int rowLayout;
    private Context context;

    public GeoIPAdapter(List<Geoip> geoipsList) {
        this.geoipsList = geoipsList;
    }

    public GeoIPAdapter(List<Geoip> geoipsList, int rowLayout, Context context) {
        this.geoipsList = geoipsList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public GeoIPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main,viewGroup,false);
        return new GeoIPAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoIPAdapter.ViewHolder viewHolder, int position) {
        viewHolder.setData(geoipsList.get(position));
    }

    @Override
    public int getItemCount() {
        return geoipsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewGeoip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewGeoip = itemView.findViewById(R.id.textViewGeoip);
        }

        private void setData(Geoip geoip) {
            textViewGeoip.setText(geoip.getCity() + "-" + geoip.getRegionCode() + " ," + geoip.getCountryName());
        }

        public void onClick(View view) {

        }
    }
}
