package com.diegaspar.pokemonlist.mapper

import com.diegaspar.core_base.domain.Pokemon
import com.diegaspar.pokemonlist.presentation.mapper.PokemonUIMapper
import com.diegaspar.pokemonlist.presentation.model.PokemonUI
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PokemonUIMapperTest {

    private val mapper = PokemonUIMapper()

    @Test
    fun `mapFromDomain should return the correct PokemonUI object`() {
        // Given
        val pokemonDomain = Pokemon("pikachu", "https://pokeapi.co/api/v2/pokemon/25")
        val expectedUI = PokemonUI("Pikachu", "0", "https://pokeapi.co/api/v2/pokemon/25", "25")

        // When
        val actualUI = mapper.mapFromDomain(pokemonDomain)

        // Then
        assertEquals(expectedUI, actualUI)
    }

    @Test
    fun `addIndexNumber should return a new PokemonUI object with the updated index`() {
        // Given
        val pokemonUI = PokemonUI("Pikachu", "0", "https://pokeapi.co/api/v2/pokemon/25", "25")
        val expectedUI = PokemonUI("Pikachu", "42", "https://pokeapi.co/api/v2/pokemon/25", "25")

        // When
        val actualUI = mapper.addIndexNumber(42, pokemonUI)

        // Then
        assertEquals(expectedUI, actualUI)
    }

    @Test
    fun `extractIdFromUrl should return the correct ID from the Pokemon's URL`() {
        // Given
        val pokemonDomain = Pokemon("pikachu", "https://pokeapi.co/api/v2/pokemon/25")
        val expectedId = "25"

        // When
        val actualId = mapper.extractIdFromUrl(pokemonDomain)

        // Then
        assertEquals(expectedId, actualId)
    }
}