package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Currencies {
    @SerializedName("source")
    private String source;
    @SerializedName("USD")
    private Currency usd;
    @SerializedName("EUR")
    private Currency eur;
    @SerializedName("GBP")
    private Currency gbp;
    @SerializedName("ARS")
    private Currency ars;
    @SerializedName("CAD")
    private Currency cad;
    @SerializedName("AUD")
    private Currency aud;
    @SerializedName("JPY")
    private Currency jpy;
    @SerializedName("CNY")
    private Currency cny;
    @SerializedName("BTC")
    private Currency btc;

    public Currencies() {
    }

    public Currencies(String source, Currency usd, Currency eur, Currency gbp, Currency ars, Currency cad, Currency aud, Currency jpy, Currency cny, Currency btc) {
        super();
        this.source = source;
        this.usd = usd;
        this.eur = eur;
        this.gbp = gbp;
        this.ars = ars;
        this.cad = cad;
        this.aud = aud;
        this.jpy = jpy;
        this.cny = cny;
        this.btc = btc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Currency getUsd() {
        return usd;
    }

    public void setUsd(Currency usd) {
        this.usd = usd;
    }

    public Currency getEur() {
        return eur;
    }

    public void setEur(Currency eur) {
        this.eur = eur;
    }

    public Currency getGbp() {
        return gbp;
    }

    public void setGbp(Currency gbp) {
        this.gbp = gbp;
    }

    public Currency getArs() {
        return ars;
    }

    public void setArs(Currency ars) {
        this.ars = ars;
    }

    public Currency getCad() {
        return cad;
    }

    public void setCad(Currency cad) {
        this.cad = cad;
    }

    public Currency getAud() {
        return aud;
    }

    public void setAud(Currency aud) {
        this.aud = aud;
    }

    public Currency getJpy() {
        return jpy;
    }

    public void setJpy(Currency jpy) {
        this.jpy = jpy;
    }

    public Currency getCny() {
        return cny;
    }

    public void setCny(Currency cny) {
        this.cny = cny;
    }

    public Currency getBtc() {
        return btc;
    }

    public void setBtc(Currency btc) {
        this.btc = btc;
    }

}
