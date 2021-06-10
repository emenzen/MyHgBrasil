package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Stocks {
    @SerializedName("IBOVESPA")
    private Stock ibovespa;
    @SerializedName("NASDAQ")
    private Stock nasdaq;
    @SerializedName("CAC")
    private Stock cac;
    @SerializedName("NIKKEI")
    private Stock nikkei;

    public Stocks() {
    }

    public Stocks(Stock ibovespa, Stock nasdaq, Stock cac, Stock nikkei) {
        super();
        this.ibovespa = ibovespa;
        this.nasdaq = nasdaq;
        this.cac = cac;
        this.nikkei = nikkei;
    }

    public Stock getIbovespa() {
        return ibovespa;
    }

    public void setIbovespa(Stock ibovespa) {
        this.ibovespa = ibovespa;
    }

    public Stock getNasdaq() {
        return nasdaq;
    }

    public void setNasdaq(Stock nasdaq) {
        this.nasdaq = nasdaq;
    }

    public Stock getCac() {
        return cac;
    }

    public void setCac(Stock cac) {
        this.cac = cac;
    }

    public Stock getNikkei() {
        return nikkei;
    }

    public void setNikkei(Stock nikkei) {
        this.nikkei = nikkei;
    }
}
