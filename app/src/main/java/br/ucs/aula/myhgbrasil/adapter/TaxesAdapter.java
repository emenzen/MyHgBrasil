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
import br.ucs.aula.myhgbrasil.model.Taxes;

public class TaxesAdapter extends RecyclerView.Adapter<TaxesAdapter.ViewHolder>{
    private final List<Taxes> taxesList;
    private int rowLayout;
    private Context context;

    public TaxesAdapter(List<Taxes> taxesList) {
        this.taxesList = taxesList;
    }

    public TaxesAdapter(List<Taxes> taxesList, int rowLayout, Context context) {
        this.taxesList = taxesList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_taxes,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(taxesList.get(position));
    }

    @Override
    public int getItemCount() {
        return taxesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtDate, txtCdi, txtSelic, txtDaily_factor, txtSelic_daily, txtCdi_daily;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtCdi = itemView.findViewById(R.id.txtCdi);
            txtSelic = itemView.findViewById(R.id.txtSelic);
            txtDaily_factor = itemView.findViewById(R.id.txtDaily_factor);
            txtSelic_daily = itemView.findViewById(R.id.txtSelic_daily);
            txtCdi_daily = itemView.findViewById(R.id.txtCdi_daily);
        }

        private void setData(Taxes taxes) {
            txtDate.setText(taxes.getDate());
            txtCdi.setText(String.valueOf(taxes.getCdi()));
            txtSelic.setText(String.valueOf(taxes.getSelic()));
            txtDaily_factor.setText(String.valueOf(taxes.getDailyFactor()));
            txtSelic_daily.setText(String.valueOf(taxes.getSelicDaily()));
            txtCdi_daily.setText(String.valueOf(taxes.getCdiDaily()));
        }

        public void onClick(View view) {
            AppCompatActivity appCompatActivity = new AppCompatActivity();
            //Toast.makeText(view.getContext(),"Você selecionou " + elementos.get(getLayoutPosition()).getCodigo(),Toast.LENGTH_LONG).show();

            Context context = view.getContext();
            Intent intent = new Intent(context, EditarTaxesActivity.class);
            intent.putExtra("IDTAXES", taxesList.get(getLayoutPosition()).getIdTaxes());
            context.startActivity(intent);

        }
    }

}
