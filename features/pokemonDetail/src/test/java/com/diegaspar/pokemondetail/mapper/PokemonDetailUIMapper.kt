package com.diegaspar.pokemondetail.mapper

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.pokemondetail.presentation.mapper.PokemonDetailUIMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDetailUIMapperTest {

    private val mapper = PokemonDetailUIMapper()

    @Test
    fun `mapFromDomain should map PokemonDetail to PokemonDetailUI correctly`() {
        // Given
        val pokemonDomain = PokemonDetail(
            name = "pikachu",
            imageUrl = pikachuUrl(),
            typesList = listOf("Electric", "Normal")
        )

        // When
        val pokemonUI = mapper.mapFromDomain(pokemonDomain)

        // Then
        assertEquals("Pikachu", pokemonUI.name)
        assertEquals(pikachuUrl(), pokemonUI.imageUrl)
        assertEquals(listOf("Electric", "Normal"), pokemonUI.types)
    }

    private fun pikachuUrl() = "https://example.com/pikachu.png"

}