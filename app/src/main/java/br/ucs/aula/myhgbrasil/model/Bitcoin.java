package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bitcoin {
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("format")
    private List<String> format = null;
    @SerializedName("last")
    private Double last;
    @SerializedName("buy")
    private Double buy;
    @SerializedName("sell")
    private Double sell;
    @SerializedName("variation")

    private Double variation;

    public Bitcoin() {
    }

    public Bitcoin(String name, List<String> format, Double last, Double buy, Double sell, Double variation) {
        super();
        this.name = name;
        this.format = format;
        this.last = last;
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

    public List<String> getFormat() {
        return format;
    }

    public void setFormat(List<String> format) {
        this.format = format;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
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
