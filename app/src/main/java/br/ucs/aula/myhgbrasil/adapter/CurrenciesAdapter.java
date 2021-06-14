package br.ucs.aula.myhgbrasil.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.activity.EditarCurrenciesActivity;
import br.ucs.aula.myhgbrasil.model.Currencies;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>{
    private final List<Currencies> currenciesList;
    private int rowLayout;
    private Context context;

    public CurrenciesAdapter(List<Currencies> currenciesList) {
        this.currenciesList = currenciesList;
    }

    public CurrenciesAdapter(List<Currencies> currenciesList, int rowLayout, Context context) {
        this.currenciesList = currenciesList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_currencies,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(currenciesList.get(position));
    }

    @Override
    public int getItemCount() {
        return currenciesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView coinCod, name, buy, sell, variation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            coinCod = itemView.findViewById(R.id.txtCurrenciesCod);
            name = itemView.findViewById(R.id.txtCurrenciesName);
            buy = itemView.findViewById(R.id.txtCurrenciesBuy);
            sell = itemView.findViewById(R.id.txtCurrenciesSell);
            variation = itemView.findViewById(R.id.txtCurrenciesVariation);
        }

        private void setData(Currencies currencies) {
            coinCod.setText(currencies.getId());
            name.setText(currencies.getName());
            buy.setText(String.valueOf(currencies.getBuy()));
            sell.setText(String.valueOf(currencies.getSell()));
            if(currencies.getVariation() < 0){
                variation.setText("˅ "+String.valueOf(currencies.getVariation())+'%');
                variation.setTextColor(Color.rgb(255,0,0));
            }
            else{
                variation.setText("˄ "+String.valueOf(currencies.getVariation())+'%');
                variation.setTextColor(Color.rgb(3,187,133));
            }
        }

        public void onClick(View view) {
            AppCompatActivity appCompatActivity = new AppCompatActivity();
            //Toast.makeText(view.getContext(),"Você selecionou " + currenciesList.get(getLayoutPosition()).getId(),Toast.LENGTH_LONG).show();

            Context context = view.getContext();
            Intent intent = new Intent(context, EditarCurrenciesActivity.class);
            intent.putExtra("ID", currenciesList.get(getLayoutPosition()).getId());
            context.startActivity(intent);

        }
    }

}
