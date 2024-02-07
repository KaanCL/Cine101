package com.example.cine101.model;
import com.google.gson.annotations.SerializedName;
public class ProductionCompany {

    @SerializedName("id")
    private long id;
    @SerializedName("logo_path")
    private String logoPath;
    @SerializedName("name")
    private String name;
    @SerializedName("origin_country")
    private String originCountry;


    public long getID() { return id; }

    public void setID(long value) { this.id = value; }


    public String getLogoPath() { return logoPath; }

    public void setLogoPath(String value) { this.logoPath = value; }


    public String getName() { return name; }

    public void setName(String value) { this.name = value; }


    public String getOriginCountry() { return originCountry; }

    public void setOriginCountry(String value) { this.originCountry = value; }
}
