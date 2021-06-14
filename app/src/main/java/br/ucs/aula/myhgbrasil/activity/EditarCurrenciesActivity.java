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
import br.ucs.aula.myhgbrasil.model.Currencies;

public class EditarCurrenciesActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_currencies);
        Intent intent = getIntent();
        final String idCurrencies = intent.getStringExtra("ID");
        bd = new BDSQLiteHelper(this);
        Currencies currencies = bd.getCurrencies(idCurrencies);
        final EditText codigo = (EditText) findViewById(R.id.etCurrenciesCod);
        final EditText name = (EditText) findViewById(R.id.etCurrenciesName);
        final EditText buy = (EditText) findViewById(R.id.etCurrenciesBuy);
        final EditText sell = (EditText) findViewById(R.id.etCurrenciesSell);
        final EditText variation = (EditText) findViewById(R.id.etCurrenciesVariation);

        codigo.setText(currencies.getId());
        name.setText(currencies.getName());
        buy.setText(String.valueOf(currencies.getBuy()));
        sell.setText(String.valueOf(currencies.getSell()));
        variation.setText(String.valueOf(currencies.getVariation()));

        final Button alterar = (Button) findViewById(R.id.btnAlterarCurrencies);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Currencies currencies = new Currencies();
                currencies.setId(idCurrencies);
                currencies.setName(name.getText().toString());
                currencies.setBuy(Double.parseDouble(buy.getText().toString()));
                currencies.setSell(Double.parseDouble(sell.getText().toString()));
                currencies.setVariation(Double.parseDouble(variation.getText().toString()));

                bd.updateCurrencies(currencies);
                Intent intent = new Intent(EditarCurrenciesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button remover = (Button) findViewById(R.id.btnRemoverCurrencies);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(EditarCurrenciesActivity.this)
                        .setTitle(R.string.confirmar_exclusao)
                        .setMessage(R.string.quer_mesmo_apagar)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                bd.deleteCurrencies(idCurrencies);
                                Intent intent = new Intent(EditarCurrenciesActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}