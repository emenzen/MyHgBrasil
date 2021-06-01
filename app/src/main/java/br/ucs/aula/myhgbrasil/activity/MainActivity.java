package br.ucs.aula.myhgbrasil.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.adapter.TaxesAdapter;
import br.ucs.aula.myhgbrasil.banco.BDSQLiteHelper;
import br.ucs.aula.myhgbrasil.model.Taxes;
import br.ucs.aula.myhgbrasil.model.TaxesResponse;
import br.ucs.aula.myhgbrasil.rest.ApiHgBrasil;
import br.ucs.aula.myhgbrasil.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSAO_REQUEST = 2;
    private RecyclerView recyclerView;
    private BDSQLiteHelper bd;
    private final static String API_KEY = "3c8e5da5";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BDSQLiteHelper(this);

        // Pede permissão para acessar as mídias gravadas no dispositivo
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }

        // Pede permissão para escrever arquivos no dispositivo
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }


        recyclerView = findViewById(R.id.lvTaxes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<Taxes> taxesList = new ArrayList<>();
        //Realiza a busca das taxas na API
        ApiInterface apiService =
                ApiHgBrasil.getHgBrasil().create(ApiInterface.class);

        Call<TaxesResponse> call = apiService.getTaxes(API_KEY);
        call.enqueue(new Callback<TaxesResponse>() {
            @Override
            public void onResponse(Call<TaxesResponse> call, Response<TaxesResponse> response) {
                int statusCode = response.code();
                List<Taxes> taxesList = response.body().getResults();
                taxesList.add(new Taxes("2025-05-14",2.0,3.0,4.0,5.6,6.9));
                //recyclerView.setAdapter(new TaxesAdapter(taxesList, R.layout.list_taxes, getApplicationContext()));

                bd.deleteAllTaxes();
                bd.addTaxes(taxesList);
                List<Taxes> taxesL = new ArrayList<>();
                taxesL = bd.getAllTaxes();
                recyclerView.setAdapter(new TaxesAdapter(taxesL, R.layout.list_taxes, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TaxesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        //List<Taxes> taxesL = new ArrayList<>();
        //taxesList.add(new Taxes("02-05-2021",2.0,3.0,4.0,5.6,6.9));
        //TaxesAdapter adapter = new TaxesAdapter(taxesList);
        //recyclerView.setAdapter(adapter);
        //taxesL = bd.getAllTaxes();
        //recyclerView.setAdapter(new TaxesAdapter(taxesL, R.layout.list_taxes, getApplicationContext()));
        //adapter.notifyDataSetChanged();
    }
}