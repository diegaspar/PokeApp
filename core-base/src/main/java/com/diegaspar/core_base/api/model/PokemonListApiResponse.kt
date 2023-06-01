package com.diegaspar.core_base.api.model

import com.diegaspar.core_base.domain.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonListApiResponse(
    @SerializedName("next")
    val nextUrlPage: String?,
    @SerializedName("results")
    val results: List<PokemonApiResult>
)

data class PokemonApiResult(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val pokemonUrl: String
) {
    fun toDomain(): Pokemon {
        return Pokemon(name = name, url = pokemonUrl)
    }
}
