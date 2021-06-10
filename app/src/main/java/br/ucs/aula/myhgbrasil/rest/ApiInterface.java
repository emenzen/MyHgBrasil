package br.ucs.aula.myhgbrasil.rest;

import br.ucs.aula.myhgbrasil.model.GeoipResponse;
import br.ucs.aula.myhgbrasil.model.QuotationsResponse;
import br.ucs.aula.myhgbrasil.model.TaxesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // curl -X GET -i 'https://api.hgbrasil.com/finance/taxes?key=3c8e5da5'
    @GET("finance/taxes")
    Call<TaxesResponse> getTaxes(@Query("key") String apiKey);

    // curl -X GET -i 'https://api.hgbrasil.com/geoip?key=3c8e5da5&address=remote'
    @GET("geoip")
    Call<GeoipResponse> getGeoip(@Query("key") String apiKey, @Query("address") String apiAddress);

    // curl -X GET -i 'https://api.hgbrasil.com/finance/quotations?key=3c8e5da5'
    @GET("finance/quotations")
    Call<QuotationsResponse> getQuotations(@Query("key") String apiKey);
}
