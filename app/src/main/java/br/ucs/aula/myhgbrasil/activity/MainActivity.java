package br.ucs.aula.myhgbrasil.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.ucs.aula.myhgbrasil.R;
import br.ucs.aula.myhgbrasil.adapter.CurrenciesAdapter;
import br.ucs.aula.myhgbrasil.adapter.GeoIPAdapter;
import br.ucs.aula.myhgbrasil.adapter.StocksAdapter;
import br.ucs.aula.myhgbrasil.adapter.TaxesAdapter;
import br.ucs.aula.myhgbrasil.banco.BDSQLiteHelper;
import br.ucs.aula.myhgbrasil.model.Currencies;
import br.ucs.aula.myhgbrasil.model.Geoip;
import br.ucs.aula.myhgbrasil.model.GeoipResponse;
import br.ucs.aula.myhgbrasil.model.Quotations;
import br.ucs.aula.myhgbrasil.model.QuotationsResponse;
import br.ucs.aula.myhgbrasil.model.Stocks;
import br.ucs.aula.myhgbrasil.model.Taxes;
import br.ucs.aula.myhgbrasil.model.TaxesResponse;
import br.ucs.aula.myhgbrasil.rest.ApiHgBrasil;
import br.ucs.aula.myhgbrasil.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSAO_REQUEST = 2;
    private RecyclerView recyclerViewTaxes;
    private RecyclerView recyclerViewStocks;
    private RecyclerView recyclerViewCurrencies;
    private RecyclerView recyclerViewGeoip;
    private BDSQLiteHelper bd;
    private final static String API_KEY = "f9709a1b";
    private final static String API_ADDRESS = "remote";
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

        final Button apiButton = (Button) findViewById(R.id.apiButton);
        apiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnline()){
                    bd.deleteAllCurrencies();
                    bd.deleteAllGeoips();
                    bd.deleteAllStocks();
                    bd.deleteAllTaxes();
                }else
                {
                    fillWithAPIData();
                }
            }
        });

        recyclerViewTaxes = findViewById(R.id.lvTaxes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTaxes.setLayoutManager(layoutManager);
        List<Taxes> taxesL = new ArrayList<>();
        taxesL = bd.getAllTaxes();
        recyclerViewTaxes.setAdapter(new TaxesAdapter(taxesL, R.layout.list_taxes, getApplicationContext()));


        recyclerViewStocks = findViewById(R.id.lvStocks);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewStocks.setLayoutManager(layoutManager2);
        List<Stocks> stocksList = new ArrayList<>();
        stocksList = bd.getAllStocks();
        recyclerViewStocks.setAdapter(new StocksAdapter(stocksList, R.layout.list_stocks, getApplicationContext()));


        recyclerViewCurrencies = findViewById(R.id.lvCurrencies);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        layoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCurrencies.setLayoutManager(layoutManager3);
        List<Currencies> currenciesList = new ArrayList<>();
        currenciesList = bd.getAllCurrencies();
        recyclerViewCurrencies.setAdapter(new CurrenciesAdapter(currenciesList, R.layout.list_currencies, getApplicationContext()));

        recyclerViewGeoip = findViewById(R.id.lvGeoip);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this);
        layoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewGeoip.setLayoutManager(layoutManager4);
        List<Geoip> geoipList = new ArrayList<>();
        geoipList = bd.getAllGeoip();
        recyclerViewGeoip.setAdapter(new GeoIPAdapter(geoipList, R.layout.list_geoip, getApplicationContext()));

//        if(false){
//            ApiInterface apiService =
//                    ApiHgBrasil.getHgBrasil().create(ApiInterface.class);
//
//            Call<TaxesResponse> call = apiService.getTaxes(API_KEY);
//            call.enqueue(new Callback<TaxesResponse>() {
//                @Override
//                public void onResponse(Call<TaxesResponse> call, Response<TaxesResponse> response) {
//                    int statusCode = response.code();
//                    List<Taxes> taxesList = response.body().getResults();
//
//                    bd.deleteAllTaxes();
//                    bd.addTaxes(taxesList);
//
//                }
//
//                @Override
//                public void onFailure(Call<TaxesResponse> call, Throwable t) {
//                    // Log error here since request failed
//                    Log.e(TAG, t.toString());
//                }
//            });
//
//            //Buscar a localização....
//            final Geoip geoip = new Geoip();
//            Call<GeoipResponse> call1 = apiService.getGeoip(API_KEY, API_ADDRESS);
//            call1.enqueue(new Callback<GeoipResponse>() {
//                @Override
//                public void onResponse(Call<GeoipResponse> call, Response<GeoipResponse> response) {
//                    int statusCode = response.code();
//                    Geoip geoip = response.body().getResults();
//
//                    bd.deleteAllGeoips();
//                    bd.addGeoips(geoip);
//                }
//
//                @Override
//                public void onFailure(Call<GeoipResponse> call, Throwable t) {
//                    Log.e(TAG, t.toString());
//                }
//            });
//            //Buscar a Quotações....
//            final Quotations quotations = new Quotations();
//            Call<QuotationsResponse> call2 = apiService.getQuotations(API_KEY);
//            call2.enqueue(new Callback<QuotationsResponse>() {
//                @Override
//                public void onResponse(Call<QuotationsResponse> call, Response<QuotationsResponse> response) {
//                    int statusCode = response.code();
//                    Quotations quotations = response.body().getResults();
//
//                    bd.deleteAllStocks();
//                    bd.addStocks(quotations.getStocks());
//
//                    bd.deleteAllCurrencies();
//                    quotations.getCurrencies().remove("source");
//                    bd.addCurrencies(quotations.getCurrencies());
//
//
//                }
//
//                @Override
//                public void onFailure(Call<QuotationsResponse> call, Throwable t) {
//                    Log.e(TAG, t.toString());
//                }
//            });
//        }



        //List<Taxes> taxesL = new ArrayList<>();
        //taxesList.add(new Taxes("02-05-2021",2.0,3.0,4.0,5.6,6.9));
        //TaxesAdapter adapter = new TaxesAdapter(taxesList);
        //recyclerView.setAdapter(adapter);
        //taxesL = bd.getAllTaxes();
        //recyclerView.setAdapter(new TaxesAdapter(taxesL, R.layout.list_taxes, getApplicationContext()));
        //adapter.notifyDataSetChanged();

        Toast.makeText(this,"Mostrando dados do banco local",Toast.LENGTH_LONG).show();

    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void fillWithAPIData(){
        //Realiza a busca das taxas na API
        ApiInterface apiService =
                ApiHgBrasil.getHgBrasil().create(ApiInterface.class);

        Call<TaxesResponse> call = apiService.getTaxes(API_KEY);
        call.enqueue(new Callback<TaxesResponse>() {
            @Override
            public void onResponse(Call<TaxesResponse> call, Response<TaxesResponse> response) {
                int statusCode = response.code();
                List<Taxes> taxesList = response.body().getResults();

                bd.deleteAllTaxes();
                bd.addTaxes(taxesList);

            }

            @Override
            public void onFailure(Call<TaxesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        //Buscar a localização....
        final Geoip geoip = new Geoip();
        Call<GeoipResponse> call1 = apiService.getGeoip(API_KEY, API_ADDRESS);
        call1.enqueue(new Callback<GeoipResponse>() {
            @Override
            public void onResponse(Call<GeoipResponse> call, Response<GeoipResponse> response) {
                int statusCode = response.code();
                Geoip geoip = response.body().getResults();

                bd.deleteAllGeoips();
                bd.addGeoips(geoip);
            }

            @Override
            public void onFailure(Call<GeoipResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        //Buscar a Quotações....
        final Quotations quotations = new Quotations();
        Call<QuotationsResponse> call2 = apiService.getQuotations(API_KEY);
        call2.enqueue(new Callback<QuotationsResponse>() {
            @Override
            public void onResponse(Call<QuotationsResponse> call, Response<QuotationsResponse> response) {
                int statusCode = response.code();
                Quotations quotations = response.body().getResults();

                bd.deleteAllStocks();
                bd.addStocks(quotations.getStocks());

                bd.deleteAllCurrencies();
                quotations.getCurrencies().remove("source");
                bd.addCurrencies(quotations.getCurrencies());


            }

            @Override
            public void onFailure(Call<QuotationsResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}