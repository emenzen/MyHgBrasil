package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Quotations {
    @SerializedName("currencies")
    private Map<String,?> currencies;
    @SerializedName("stocks")
    private Map<String,Stocks> stocks;
    @SerializedName("available_sources")
    private List<String> availableSources = null;
    @SerializedName("bitcoin")
    private Map<String,Bitcoin> bitcoin;

    public Quotations() {
    }

    public Quotations(Map<String,?> currencies, Map<String,Stocks> stocks, List<String> availableSources, Map<String,Bitcoin> bitcoin) {
        super();
        this.currencies = currencies;
        this.stocks = stocks;
        this.availableSources = availableSources;
        this.bitcoin = bitcoin;
    }

    public Map<String,?> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String,?> currencies) {
        this.currencies = currencies;
    }

    public Map<String,Stocks> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String,Stocks> stocks) {
        this.stocks = stocks;
    }

    public List<String> getAvailableSources() {
        return availableSources;
    }

    public void setAvailableSources(List<String> availableSources) {
        this.availableSources = availableSources;
    }

    public Map<String,Bitcoin> getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(Map<String,Bitcoin> bitcoin) {
        this.bitcoin = bitcoin;
    }
}
