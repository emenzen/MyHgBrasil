package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Flag {
    @SerializedName("svg")
    private String svg;
    @SerializedName("png_16")
    private String png16;

    public Flag() { super(); }

    public Flag(String svg, String png16) {
        super();
        this.svg = svg;
        this.png16 = png16;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPng16() {
        return png16;
    }

    public void setPng16(String png16) {
        this.png16 = png16;
    }
}
