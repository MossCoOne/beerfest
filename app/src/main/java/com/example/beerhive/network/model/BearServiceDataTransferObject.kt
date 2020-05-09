package com.example.beerhive.network.model

import com.example.beerhive.database.DataBaseBeer
import com.example.beerhive.domain.Beer
import com.google.gson.annotations.SerializedName

data class BeerResponse(val id: Int? = null,
                        val name: String,
                        val tagline: String,

                        @SerializedName("first_brewed")
                        val firstBrewed: String? = null,
                        val description: String,

                        @SerializedName("image_url")
                        val imageUrl: String,
                        val abv: Double? = null,
                        val ibu: Double? = null,

                        @SerializedName("target_fg")
                        val targetFg: Int? = null,

                        @SerializedName("target_og")
                        val targetOg: Double? = null,

                        @SerializedName("ebc")
                        val ebc: Int? = null,

                        @SerializedName("srm")
                        val srm: Double? = null,

                        @SerializedName("ph")
                        val ph: Double? = null,

                        @SerializedName("attenuation_level")
                        val attenuationLevel: Double? = null,

                        @SerializedName("volume")
                        val volume: Volume,

                        @SerializedName("boil_volume")
                        val boilVolume: BoilVolume? = null,

                        @SerializedName("method")
                        val method: Method? = null,

                        @SerializedName("ingredients")
                        val ingredients: Ingredients? = null,

                        @SerializedName("food_pairing")
                        val foodPairing: List<String>? = null,

                        @SerializedName("brewers_tips")
                        val brewersTips: String,

                        @SerializedName("contributed_by")
                        val contributedBy: String? = null) {

    private lateinit var beerResponseList: List<BeerResponse>

    fun asDomainModel(): List<Beer> {
        return beerResponseList.map {
            Beer(
                    beerName = it.name,
                    beerDescription = it.description,
                    beerBrewerTips = it.brewersTips,
                    beerImageUrl = it.imageUrl,
                    beerBrewingTipsTitle = it.tagline,
                    volume = it.volume.value,
                    volumeUnit = it.volume.unit
            )
        }
    }
}

fun List<BeerResponse>.asDataBaseModel(): Array<DataBaseBeer> {
    return map {
        DataBaseBeer(beerName = it.name,
                beerDescription = it.description,
                beerBrewerTips = it.brewersTips,
                beerImageUrl = it.imageUrl,
                beerBrewingTipsTitle = it.tagline,
                volume = it.volume.value,
                volumeUnit = it.volume.unit)
    }.toTypedArray()
}

data class BoilVolume(val value: Int? = null,
                      val unit: String? = null)

data class Temp(val value: Int? = null,
                val unit: String? = null)

data class MashTemp(private val temp: Temp? = null,
                    val duration: Int? = null)

data class Fermentation(
        @SerializedName("temp")
        val temperature: Temp? = null)

data class Method(@SerializedName("mash_temp")
                  val mashTemp: List<MashTemp>? = null,
                  val fermentation: Fermentation? = null)

data class Amount(val value: Double? = null,
                  val unit: String? = null)

data class Hop(private val name: String? = null,
               @SerializedName("amount")
               private val amount: Amount? = null,
               @SerializedName("add")
               private val add: String? = null,
               @SerializedName("attribute")
               private val attribute: String? = null)


data class Malt(val name: String? = null,
                val amount: Amount? = null)

data class Ingredients(@SerializedName("malt")
                       private val maltLis: List<Malt>? = null,
                       @SerializedName("hops")
                       val hopsList: List<Hop>? = null,
                       val yeast: String? = null)


data class Volume(val value: Int,
                  val unit: String)