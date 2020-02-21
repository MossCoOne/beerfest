package com.example.beerhive.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Ingredients {

    @SerializedName("malt")
    private List<Malt> maltLis = null;
    @SerializedName("hops")
    private List<Hop> hopsList = null;
    private String yeast;
}
