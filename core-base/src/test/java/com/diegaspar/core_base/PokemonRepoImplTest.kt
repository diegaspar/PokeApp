package com.diegaspar.core_base

import com.diegaspar.core_base.data.persistence.PokemonLocalDataSource
import com.diegaspar.core_base.data.persistence.mapper.PokemonEntityMapper
import com.diegaspar.core_base.data.persistence.model.PokemonEntity
import com.diegaspar.core_base.data.remote.PokemonRemoteDataSource
import com.diegaspar.core_base.domain.PaginationData
import com.diegaspar.core_base.domain.Pokemon
import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated
import com.diegaspar.core_base.repo.PokemonRepoImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PokemonRepoImplTest {

    @Mock
    private lateinit var remoteDataSource: PokemonRemoteDataSource

    @Mock
    private lateinit var localDataSource: PokemonLocalDataSource

    @Mock
    private lateinit var mapper: PokemonEntityMapper

    private lateinit var repo: PokemonRepoImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repo = PokemonRepoImpl(remoteDataSource, localDataSource, mapper)
    }

    @Test
    fun `getPokemons should call remoteDataSource and return PokemonListPaginated`() = runBlocking {
        // Given
        val offset = 0
        val pokemonListPaginated = PokemonListPaginated(
            pokemonList = listOf(anyPokemonDomain()),
            pageData = PaginationData(hasNextPage = false)
        )
        whenever(remoteDataSource.getPokemons(offset)).thenReturn(pokemonListPaginated)

        // When
        val result = repo.getPokemons(offset)

        // Then
        assertEquals(pokemonListPaginated, result)
    }

    @Test
    fun `getPokemonDetail should call remoteDataSource, insert into localDataSource, and return PokemonDetail`() =
        runBlocking {
            // Given
            val id = "1"
            val pokemonDetail = anyPokemonDomainDetail()
            whenever(remoteDataSource.getPokemonDetail(id)).thenReturn(pokemonDetail)

            // When
            val result = repo.getPokemonDetail(id)

            // Then
            assertEquals(pokemonDetail, result)
        }

    @Test
    fun `getPokemonDetail should handle exception and retrieve from localDataSource`() =
        runBlocking {
            // Given
            val id = "1"
            val localPokemonEntity = anyPokemonEntity()
            val mappedPokemonDetail = anyPokemonDomainDetail()
            whenever(remoteDataSource.getPokemonDetail(id)).thenAnswer { Exception() }
            whenever(localDataSource.retrievePokemon(id)).thenReturn(localPokemonEntity)
            whenever(mapper.mapFromEntity(localPokemonEntity)).thenReturn(mappedPokemonDetail)

            // When
            val result = repo.getPokemonDetail(id)

            // Then
            assertEquals(mappedPokemonDetail, result)
        }

    @Test
    fun `getPokemonDetail should throw exception if Pokemon not found locally or remotely`() =
        runBlocking {
            // Given
            val id = "1"
            whenever(remoteDataSource.getPokemonDetail(id)).thenAnswer { Exception() }
            whenever(localDataSource.retrievePokemon(id)).thenAnswer { Exception() }

            // When
            val exception = assertThrows(Exception::class.java) {
                runBlocking {
                    repo.getPokemonDetail(id)
                }
            }

            // Then
            assertEquals("Pokemon not found for id: $id", exception.message)
        }

    private fun anyPokemonEntity() = PokemonEntity("", "", "", emptyList())
    private fun anyPokemonDomainDetail() = PokemonDetail("", "", emptyList())
    private fun anyPokemonDomain() = Pokemon("", "")
}
