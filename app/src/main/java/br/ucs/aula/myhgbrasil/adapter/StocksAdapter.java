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
import br.ucs.aula.myhgbrasil.activity.EditarStocksActivity;
import br.ucs.aula.myhgbrasil.model.Stocks;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.ViewHolder>{
    private final List<Stocks> stocksList;
    private int rowLayout;
    private Context context;

    public StocksAdapter(List<Stocks> stocksList) {
        this.stocksList = stocksList;
    }

    public StocksAdapter(List<Stocks> stocksList, int rowLayout, Context context) {
        this.stocksList = stocksList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_stocks,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setData(stocksList.get(position));
    }

    @Override
    public int getItemCount() {
        return stocksList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name, location, variation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.txtStocksName);
            location = itemView.findViewById(R.id.txtStocksLocation);
            variation = itemView.findViewById(R.id.txtStocksVariation);
        }

        private void setData(Stocks stocks) {
            name.setText(stocks.getName());
            location.setText(stocks.getLocation());
            if(stocks.getVariation() < 0){
                variation.setText("˅ "+String.valueOf(stocks.getVariation())+'%');
                variation.setTextColor(Color.rgb(255,0,0));
            }
            else{
                variation.setText("˄ "+String.valueOf(stocks.getVariation())+'%');
                variation.setTextColor(Color.rgb(3,187,133));
            }
        }

        public void onClick(View view) {
            AppCompatActivity appCompatActivity = new AppCompatActivity();
            //Toast.makeText(view.getContext(),"Você selecionou " + stocksList.get(getLayoutPosition()).getId(),Toast.LENGTH_LONG).show();

            Context context = view.getContext();
            Intent intent = new Intent(context, EditarStocksActivity.class);
            intent.putExtra("IDSTOCKS", stocksList.get(getLayoutPosition()).getId());
            context.startActivity(intent);

        }
    }

}
