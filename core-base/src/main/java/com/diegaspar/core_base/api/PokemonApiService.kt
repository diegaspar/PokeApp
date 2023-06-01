package com.diegaspar.core_base.api

import com.diegaspar.core_base.api.model.PokemonDetailResponse
import com.diegaspar.core_base.api.model.PokemonListApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val pageSizeLimit = 50

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") pageSize: Int = pageSizeLimit,
        @Query("offset") offset: Int
    ): PokemonListApiResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") pokemonId: String
    ): PokemonDetailResponse
}