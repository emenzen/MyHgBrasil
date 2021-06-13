package br.ucs.aula.myhgbrasil.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.banco.BDSQLiteHelper;
import br.ucs.aula.myhgbrasil.model.Stocks;

public class EditarStocksActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_stocks);
        Intent intent = getIntent();
        final String idStocks = intent.getStringExtra("IDSTOCKS");
        bd = new BDSQLiteHelper(this);
        Stocks stocks = bd.getStocks(idStocks);
        final EditText variation = (EditText) findViewById(R.id.etStocksVariation);
        final EditText name = (EditText) findViewById(R.id.etStocksName);
        final EditText location = (EditText) findViewById(R.id.etStocksLocation);

        variation.setText(String.valueOf(stocks.getVariation()));
        name.setText(stocks.getName());
        location.setText(stocks.getLocation());

        final Button alterar = (Button) findViewById(R.id.btnAlterarStocks);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stocks stocks = new Stocks();
                stocks.setId(idStocks);
                stocks.setVariation(Double.parseDouble(variation.getText().toString()));
                stocks.setName(name.getText().toString());
                stocks.setLocation(location.getText().toString());

                bd.updateStocks(stocks);
                Intent intent = new Intent(EditarStocksActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button remover = (Button) findViewById(R.id.btnRemoverStocks);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(EditarStocksActivity.this)
                        .setTitle(R.string.confirmar_exclusao)
                        .setMessage(R.string.quer_mesmo_apagar)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Stocks stocks = new Stocks();
                                stocks.setId(idStocks);
                                bd.deleteStocks(idStocks);
                                Intent intent = new Intent(EditarStocksActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });


    }
}