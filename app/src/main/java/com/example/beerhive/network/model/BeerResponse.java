package com.example.beerhive.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerResponse {
    private Integer id;
    private String name;
    private String tagline;
    @SerializedName("first_brewed")
    private String firstBrewed;
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    private Double abv;
    private Double ibu;
    @SerializedName("target_fg")
    private Integer targetFg;
    @SerializedName("target_og")
    private Double targetOg;
    @SerializedName("ebc")
    private Integer ebc;
    @SerializedName("srm")
    private Double srm;
    @SerializedName("ph")
    private Double ph;
    @SerializedName("attenuation_level")
    private Double attenuationLevel;
    @SerializedName("volume")
    private Volume volume;
    @SerializedName("boil_volume")
    private BoilVolume boilVolume;
    @SerializedName("method")
    private Method method;
    @SerializedName("ingredients")
    private Ingredients ingredients;
    @SerializedName("food_pairing")
    private List<String> foodPairing = null;
    @SerializedName("brewers_tips")
    private String brewersTips;
    @SerializedName("contributed_by")
    private String contributedBy;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getAbv() {
        return abv;
    }

    public Double getIbu() {
        return ibu;
    }

    public Integer getTargetFg() {
        return targetFg;
    }

    public Double getTargetOg() {
        return targetOg;
    }

    public Integer getEbc() {
        return ebc;
    }

    public Double getSrm() {
        return srm;
    }

    public Double getPh() {
        return ph;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public Volume getVolume() {
        return volume;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public Method getMethod() {
        return method;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }
}
