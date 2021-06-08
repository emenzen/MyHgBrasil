package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("capital")
    private String capital;
    @SerializedName("flag")
    private Flag flag;
    @SerializedName("calling_code")
    private String callingCode;

    public Country() { super(); }

    public Country(String name, String code, String capital, Flag flag, String callingCode) {
        super();
        this.name = name;
        this.code = code;
        this.capital = capital;
        this.flag = flag;
        this.callingCode = callingCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }
}
