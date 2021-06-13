package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Taxes {
    private int idTaxes;
    @SerializedName("date")
    private String date;
    @SerializedName("cdi")
    private Double cdi;
    @SerializedName("selic")
    private Double selic;
    @SerializedName("daily_factor")
    private Double dailyFactor;
    @SerializedName("selic_daily")
    private Double selicDaily;
    @SerializedName("cdi_daily")
    private Double cdiDaily;

    public Taxes() { super(); }

    public Taxes(String date, Double cdi, Double selic, Double dailyFactor, Double selicDaily, Double cdiDaily){
        super();
        this.date = date;
        this.cdi = cdi;
        this.selic = selic;
        this.dailyFactor = dailyFactor;
        this.cdiDaily = cdiDaily;
        this.selicDaily = selicDaily;
    }

    public String getDate() {
        return date;
    }

    /*
    public void setDate(String date) {
        //para formatar a data no formato dd/mm/yyyy
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date(date);
        try {
            date1 = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = dateFormat.format(date1);
    }
     */
    public void setDate(String date){this.date = date;}

    public int getIdTaxes(){return idTaxes;}

    public void setIdTaxes(int idTaxes){this.idTaxes = idTaxes;}

    public Double getCdi() {
        return cdi;
    }

    public void setCdi(Double cdi) {
        this.cdi = cdi;
    }

    public Double getSelic() {
        return selic;
    }

    public void setSelic(Double selic) {
        this.selic = selic;
    }

    public Double getDailyFactor() {
        return dailyFactor;
    }

    public void setDailyFactor(Double dailyFactor) {
        this.dailyFactor = dailyFactor;
    }

    public Double getSelicDaily() {
        return selicDaily;
    }

    public void setSelicDaily(Double selicDaily) {
        this.selicDaily = selicDaily;
    }

    public Double getCdiDaily() {
        return cdiDaily;
    }

    public void setCdiDaily(Double cdiDaily) {
        this.cdiDaily = cdiDaily;
    }

}
