package br.ucs.aula.myhgbrasil.rest;

import br.ucs.aula.myhgbrasil.model.TaxesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // curl -X GET -i 'https://api.hgbrasil.com/finance/taxes?key=3c8e5da5'

    @GET("finance/taxes")
    Call<TaxesResponse> getTaxes(@Query("key") String apiKey);

    // curl -X GET -i 'http://api.themoviedb.org/3/movie/238?api_key=7e8f60e325cd06e164799af1e317d7a7'

    @GET("movie/{id}")
    Call<TaxesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
