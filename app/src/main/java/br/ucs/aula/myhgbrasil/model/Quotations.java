package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quotations {
    @SerializedName("currencies")
    private Currencies currencies;
    @SerializedName("stocks")
    private Stocks stocks;
    @SerializedName("available_sources")
    private List<String> availableSources = null;
    @SerializedName("bitcoin")
    private Bitcoin bitcoin;

    public Quotations() {
    }

    public Quotations(Currencies currencies, Stocks stocks, List<String> availableSources, Bitcoin bitcoin) {
        super();
        this.currencies = currencies;
        this.stocks = stocks;
        this.availableSources = availableSources;
        this.bitcoin = bitcoin;
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }

    public Stocks getStocks() {
        return stocks;
    }

    public void setStocks(Stocks stocks) {
        this.stocks = stocks;
    }

    public List<String> getAvailableSources() {
        return availableSources;
    }

    public void setAvailableSources(List<String> availableSources) {
        this.availableSources = availableSources;
    }

    public Bitcoin getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(Bitcoin bitcoin) {
        this.bitcoin = bitcoin;
    }
}
