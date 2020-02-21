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
    private Integer ibu;
    @SerializedName("target_fg")
    private Integer targetFg;
    @SerializedName("target_og")
    private Integer targetOg;
    @SerializedName("ebc")
    private Integer ebc;
    @SerializedName("srm")
    private Integer srm;
    @SerializedName("ph")
    private Double ph;
    @SerializedName("attenuation_level")
    private Integer attenuationLevel;
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
}
