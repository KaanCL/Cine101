package com.example.cine101.model.Tmdb;
import com.google.gson.annotations.SerializedName;
public class Cast {
    private boolean adult;
    private long gender;
    private long id;
    private String knownForDepartment;
    private String name;
    private String originalName;
    private double popularity;
   @SerializedName("profile_path")
    private String profilePath;
    private long castID;
    private String character;
    private String creditID;
    private long order;


    public boolean getAdult() { return adult; }

    public void setAdult(boolean value) { this.adult = value; }


    public long getGender() { return gender; }

    public void setGender(long value) { this.gender = value; }


    public long getID() { return id; }

    public void setID(long value) { this.id = value; }


    public String getKnownForDepartment() { return knownForDepartment; }

    public void setKnownForDepartment(String value) { this.knownForDepartment = value; }


    public String getName() { return name; }

    public void setName(String value) { this.name = value; }


    public String getOriginalName() { return originalName; }

    public void setOriginalName(String value) { this.originalName = value; }


    public double getPopularity() { return popularity; }

    public void setPopularity(double value) { this.popularity = value; }


    public String getProfilePath() { return profilePath; }

    public void setProfilePath(String value) { this.profilePath = value; }


    public long getCastID() { return castID; }

    public void setCastID(long value) { this.castID = value; }


    public String getCharacter() { return character; }

    public void setCharacter(String value) { this.character = value; }


    public String getCreditID() { return creditID; }

    public void setCreditID(String value) { this.creditID = value; }


    public long getOrder() { return order; }

    public void setOrder(long value) { this.order = value; }
    
}
