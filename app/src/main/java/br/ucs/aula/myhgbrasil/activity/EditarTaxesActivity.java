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
import br.ucs.aula.myhgbrasil.model.Taxes;

public class EditarTaxesActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_taxes);
        Intent intent = getIntent();
        final String dates = intent.getStringExtra("DATE");
        bd = new BDSQLiteHelper(this);
        Taxes taxes = bd.getTaxes(dates);
        final EditText date = (EditText) findViewById(R.id.etDate);
        final EditText cdi = (EditText) findViewById(R.id.etCdi);
        final EditText selic = (EditText) findViewById(R.id.etSelic);
        final EditText dailyFactor = (EditText) findViewById(R.id.etDaily_factor);
        final EditText cdiDaily = (EditText) findViewById(R.id.etCdi_daily);
        final EditText selicDaily = (EditText) findViewById(R.id.etSelic_daily);

        date.setText(taxes.getDate());
        cdi.setText(String.valueOf(taxes.getCdi()));
        selic.setText(String.valueOf(taxes.getSelic()));
        dailyFactor.setText(String.valueOf(taxes.getDailyFactor()));
        cdiDaily.setText(String.valueOf(taxes.getCdiDaily()));
        selicDaily.setText(String.valueOf(taxes.getSelicDaily()));


        final Button alterar = (Button) findViewById(R.id.btnAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taxes taxes = new Taxes();
                taxes.setDate(date.getText().toString());
                taxes.setCdi(Double.parseDouble(cdi.getText().toString()));
                taxes.setSelic(Double.parseDouble(selic.getText().toString()));
                taxes.setCdiDaily(Double.parseDouble(cdiDaily.getText().toString()));
                taxes.setSelicDaily(Double.parseDouble(selicDaily.getText().toString()));
                taxes.setDailyFactor(Double.parseDouble(dailyFactor.getText().toString()));

                bd.updateTaxes(taxes);
                Intent intent = new Intent(EditarTaxesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button remover = (Button) findViewById(R.id.btnRemover);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(EditarTaxesActivity.this)
                        .setTitle(R.string.confirmar_exclusao)
                        .setMessage(R.string.quer_mesmo_apagar)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Taxes taxes = new Taxes();
                                taxes.setDate(dates);
                                bd.deleteTaxes(taxes);
                                Intent intent = new Intent(EditarTaxesActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}