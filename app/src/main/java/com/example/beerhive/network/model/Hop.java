package com.example.beerhive.network.model;

import com.google.gson.annotations.SerializedName;

class Hop {
    private String name;
    @SerializedName("amount")
    private Amount amount;
    @SerializedName("add")
    private String add;
    @SerializedName("attribute")
    private String attribute;
}
