package com.example.cine101.model;
import com.google.gson.annotations.SerializedName;
public class Genre {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;


    public long getID() { return id; }

    public void setID(long value) { this.id = value; }


    public String getName() { return name; }

    public void setName(String value) { this.name = value; }
}
