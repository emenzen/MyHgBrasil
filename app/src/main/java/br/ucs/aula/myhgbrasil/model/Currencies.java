package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Currencies {
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("buy")
    private Double buy;
    @SerializedName("sell")
    private Double sell;
    @SerializedName("variation")
    private Double variation;

    public Currencies() { super(); }

    public Currencies(String name, Double buy, Double sell, Double variation) {
        super();
        this.name = name;
        this.buy = buy;
        this.sell = sell;
        this.variation = variation;
    }

    public String getId() {return  id;}

    public  void  setId(String id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }
}
