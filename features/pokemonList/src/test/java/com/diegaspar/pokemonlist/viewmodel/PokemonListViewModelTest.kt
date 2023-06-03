package com.diegaspar.pokemonlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegaspar.core_base.domain.PaginationData
import com.diegaspar.core_base.domain.Pokemon
import com.diegaspar.core_base.domain.PokemonListPaginated
import com.diegaspar.pokemonlist.domain.GetPokemonsUseCase
import com.diegaspar.pokemonlist.presentation.mapper.PokemonUIMapper
import com.diegaspar.pokemonlist.presentation.state.PokemonListState
import com.diegaspar.pokemonlist.presentation.viewmodel.PokemonListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonListViewModel
    private val useCase = mock<GetPokemonsUseCase>()
    private val mapper = mock<PokemonUIMapper>()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Test thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        viewModel = PokemonListViewModel(useCase, mapper)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    private fun TestScope.loadViewModelStates(events: MutableList<PokemonListState>) {
        viewModel.uiState
            .onEach { events.add(it) }
            .launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
    }

    @Test
    fun `should load initial loading state when starting and should be successful after getting success data`() {
        val events = mutableListOf<PokemonListState>()
        runTest {
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonsUseCase.Params(0))).thenReturn(
                PokemonListPaginated(
                    pokemonList,
                    PaginationData(hasNextPage = true)
                )
            )

            // When
            viewModel.getPokemons()
        }
        // Then
        assertThat(events.first(), instanceOf(PokemonListState.InitialLoadingState::class.java))
        assertThat(events.last(), instanceOf(PokemonListState.SuccessState::class.java))
        assert(events.last().list.size == pokemonList.size)
    }

    @Test
    fun `should be ErrorStateEmptyList when current pokemon list is empty when getPokemons fails`() {
        val events = mutableListOf<PokemonListState>()
        runTest {
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonsUseCase.Params(0))).thenAnswer { Exception() }

            // When
            viewModel.getPokemons()
        }
        // Then
        assertThat(events.first(), instanceOf(PokemonListState.InitialLoadingState::class.java))
        assertThat(events.last(), instanceOf(PokemonListState.ErrorStateEmptyList::class.java))
        assert(events.last().list.isEmpty())
    }

    @Test
    fun `should be ErrorState when current pokemon list is NOT empty and when getPokemons fails`() {
        val events = mutableListOf<PokemonListState>()

        runTest {//First successful call
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonsUseCase.Params(0))).thenReturn(
                PokemonListPaginated(
                    pokemonList,
                    PaginationData(hasNextPage = true)
                )
            )

            // When
            launch { viewModel.getPokemons() }
            advanceUntilIdle()
        }

        runTest {//Second error call
            // Given
            whenever(useCase.invoke(GetPokemonsUseCase.Params(pokemonList.size))).thenAnswer { Exception() }

            // When
            launch { viewModel.getPokemons() }
            advanceUntilIdle()
        }

        // Then
        assertThat(events.first(), instanceOf(PokemonListState.InitialLoadingState::class.java))
        assertThat(events.last(), instanceOf(PokemonListState.ErrorState::class.java))
        assert(events.last().list.isNotEmpty())
    }

    @Test
    fun `should NOT get any more pokemons when hasNextPage() is false`() {
        val events = mutableListOf<PokemonListState>()

        runTest {//First successful call with hastNextPage = false
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonsUseCase.Params(0))).thenReturn(
                PokemonListPaginated(
                    pokemonList,
                    PaginationData(hasNextPage = false)
                )
            )

            // When
            launch { viewModel.getPokemons() }
            advanceUntilIdle()
        }

        runTest {//Second error call

            // When
            launch { viewModel.getPokemons() }
            advanceUntilIdle()
            //Then
            verify(useCase, never()).invoke(GetPokemonsUseCase.Params(pokemonList.size))
        }

    }

    private val pokemonList = listOf(
        Pokemon(name = "Pikachu", url = "https://pokeapi.co/api/v2/pokemon/6"),
        Pokemon(name = "Ditto", url = "https://pokeapi.co/api/v2/pokemon/5"),
    )
}
