package com.diegaspar.core_base.api.model

import com.diegaspar.core_base.domain.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonListApiResponse(
    @SerializedName("results")
    val results: List<PokemonApiResult>
)

data class PokemonApiResult(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    fun toDomain(index: Int): Pokemon {
        //TODO add some tests
        return Pokemon(name = name, url = url)
    }
}
