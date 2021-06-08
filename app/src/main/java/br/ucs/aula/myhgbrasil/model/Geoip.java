package br.ucs.aula.myhgbrasil.model;

import com.google.gson.annotations.SerializedName;

public class Geoip {
    @SerializedName("address")
    private String address;
    @SerializedName("type")
    private String type;
    @SerializedName("city")
    private String city;
    @SerializedName("region")
    private String region;
    @SerializedName("country_name")
    private String countryName;
    @SerializedName("continent")
    private String continent;
    @SerializedName("continent_code")
    private String continentCode;
    @SerializedName("region_code")
    private String regionCode;
    @SerializedName("country")
    private Country country;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("woeid")
    private Integer woeid;

    public Geoip() { super(); }

    public Geoip(String address, String type, String city, String region, String countryName, String continent, String continentCode, String regionCode, Country country, Double latitude, Double longitude, Integer woeid) {
        super();
        this.address = address;
        this.type = type;
        this.city = city;
        this.region = region;
        this.countryName = countryName;
        this.continent = continent;
        this.continentCode = continentCode;
        this.regionCode = regionCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.woeid = woeid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }
}
