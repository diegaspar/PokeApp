package com.diegaspar.core_base.api.model

import com.diegaspar.core_base.domain.PokemonDetail
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<TypeInfo>,
    @SerializedName("sprites")
    val sprites: Sprites
)

fun PokemonDetailResponse.toPokemonDetail() = PokemonDetail(
    name = this.name,
    imageUrl = this.sprites.other.officialArtWork.frontDefault,
    typesList = this.types.map { it.type.name },
)

data class TypeInfo(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDetail
)

data class TypeDetail(
    @SerializedName("name")
    val name: String
)

data class Sprites(
    @SerializedName("other")
    val other: Other
)

data class Other(
    @SerializedName("official-artWork")
    val officialArtWork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)
