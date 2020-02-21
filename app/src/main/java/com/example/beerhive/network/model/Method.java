package com.example.beerhive.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Method {

    @SerializedName("mash_temp")
    private List<MashTemp> mashTemp = null;
    private Fermentation fermentation;

}
