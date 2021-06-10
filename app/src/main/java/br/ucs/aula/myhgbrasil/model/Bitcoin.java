package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Bitcoin {
    @SerializedName("blockchain_info")
    private Coin blockchainInfo;
    @SerializedName("coinbase")
    private Coin coinbase;
    @SerializedName("bitstamp")
    private Coin bitstamp;
    @SerializedName("foxbit")
    private Coin foxbit;
    @SerializedName("mercadobitcoin")
    private Coin mercadobitcoin;

    public Bitcoin() {
    }

    public Bitcoin(Coin blockchainInfo, Coin coinbase, Coin bitstamp, Coin foxbit, Coin mercadobitcoin) {
        super();
        this.blockchainInfo = blockchainInfo;
        this.coinbase = coinbase;
        this.bitstamp = bitstamp;
        this.foxbit = foxbit;
        this.mercadobitcoin = mercadobitcoin;
    }

    public Coin getBlockchainInfo() {
        return blockchainInfo;
    }

    public void setBlockchainInfo(Coin blockchainInfo) {
        this.blockchainInfo = blockchainInfo;
    }

    public Coin getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(Coin coinbase) {
        this.coinbase = coinbase;
    }

    public Coin getBitstamp() {
        return bitstamp;
    }

    public void setBitstamp(Coin bitstamp) {
        this.bitstamp = bitstamp;
    }

    public Coin getFoxbit() {
        return foxbit;
    }

    public void setFoxbit(Coin foxbit) {
        this.foxbit = foxbit;
    }

    public Coin getMercadobitcoin() {
        return mercadobitcoin;
    }

    public void setMercadobitcoin(Coin mercadobitcoin) {
        this.mercadobitcoin = mercadobitcoin;
    }
}
