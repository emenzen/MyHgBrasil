package br.ucs.aula.myhgbrasil.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHgBrasil {

    public static final String BASE_URL = "https://api.hgbrasil.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getHgBrasil() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}