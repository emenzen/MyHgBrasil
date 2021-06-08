package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class GeoipResponse {
    @SerializedName("by")
    private String by;
    @SerializedName("valid_key")
    private Boolean validKey;
    @SerializedName("results")
    private Geoip results;
    @SerializedName("execution_time")
    private Double executionTime;
    @SerializedName("from_cache")
    private Boolean fromCache;

    public GeoipResponse() { super(); }

    public GeoipResponse(String by, Boolean validKey, Geoip results, Double executionTime, Boolean fromCache) {
        super();
        this.by = by;
        this.validKey = validKey;
        this.results = results;
        this.executionTime = executionTime;
        this.fromCache = fromCache;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Boolean getValidKey() {
        return validKey;
    }

    public void setValidKey(Boolean validKey) {
        this.validKey = validKey;
    }

    public Geoip getResults() {
        return results;
    }

    public void setResults(Geoip results) {
        this.results = results;
    }

    public Double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public Boolean getFromCache() {
        return fromCache;
    }

    public void setFromCache(Boolean fromCache) {
        this.fromCache = fromCache;
    }
}
