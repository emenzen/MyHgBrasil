package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Stocks {
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("points")
    private Double points;
    @SerializedName("variation")
    private Double variation;

    public Stocks() {
    }

    public Stocks(String name, String location, Double points, Double variation) {
        super();
        this.name = name;
        this.location = location;
        this.points = points;
        this.variation = variation;
    }

    public String getKey() {return  key;}

    public  void  setKey(String key) {this.key = key;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }
}
