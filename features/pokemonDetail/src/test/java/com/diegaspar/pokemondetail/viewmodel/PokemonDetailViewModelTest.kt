package com.diegaspar.pokemondetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.pokemondetail.domain.GetPokemonDetailUseCase
import com.diegaspar.pokemondetail.presentation.mapper.PokemonDetailUIMapper
import com.diegaspar.pokemondetail.presentation.model.PokemonDetailUI
import com.diegaspar.pokemondetail.presentation.state.PokemonDetailState
import com.diegaspar.pokemondetail.presentation.viewmodel.PokemonDetailViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PokemonDetailViewModel
    private val useCase = Mockito.mock<GetPokemonDetailUseCase>()
    private val mapper = Mockito.mock<PokemonDetailUIMapper>()

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Test thread")


    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        viewModel = PokemonDetailViewModel(useCase, mapper)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    private fun TestScope.loadViewModelStates(events: MutableList<PokemonDetailState>) {
        viewModel.uiState
            .onEach { events.add(it) }
            .launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))
    }

    @Test
    fun `should load initial loading state when starting and should be successful after getting success data`() {
        val events = mutableListOf<PokemonDetailState>()
        runTest {
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonDetailUseCase.Params(anyId)))
                .thenReturn(anyPokemonDetail)

            whenever(mapper.mapFromDomain(anyPokemonDetail))
                .thenReturn(anyPokemonDetailUI)

            // When
            viewModel.loadPokemonDetail(anyId)
        }
        // Then
        assertThat(
            events.first(),
            CoreMatchers.instanceOf(PokemonDetailState.InitialLoadingState::class.java)
        )
        assertThat(
            events.last(),
            CoreMatchers.instanceOf(PokemonDetailState.SuccessState::class.java)
        )
    }

    @Test
    fun `should be ErrorState when getting a pokemon detail and there was any error successful after trying to get data`() {
        val events = mutableListOf<PokemonDetailState>()
        runTest {
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonDetailUseCase.Params(anyId)))
                .thenAnswer { Exception() }

            // When
            viewModel.loadPokemonDetail(anyId)
        }
        // Then
        assertThat(
            events.first(),
            CoreMatchers.instanceOf(PokemonDetailState.InitialLoadingState::class.java)
        )
        assertThat(
            events.last(),
            CoreMatchers.instanceOf(PokemonDetailState.ErrorState::class.java)
        )
    }


    @Test
    fun `should update the color of the SuccessState when pokemon data is already loaded and onColorsLoaded is triggered`() {
        val events = mutableListOf<PokemonDetailState>()
        runTest { //First state getting the first pokemon
            loadViewModelStates(events)
            // Given
            whenever(useCase.invoke(GetPokemonDetailUseCase.Params(anyId)))
                .thenReturn(anyPokemonDetail)

            whenever(mapper.mapFromDomain(anyPokemonDetail))
                .thenReturn(anyPokemonDetailUI)

            // When
            viewModel.loadPokemonDetail(anyId)
        }
        // Then
        assertThat(
            events.first(),
            CoreMatchers.instanceOf(PokemonDetailState.InitialLoadingState::class.java)
        )
        assertThat(
            events.last(),
            CoreMatchers.instanceOf(PokemonDetailState.SuccessState::class.java)
        )

        runTest { //Second state changing the color
            // Given
            val color = randomColor

            // When
            viewModel.onColorsLoaded(color)
        }

        assertThat(
            events.last(),
            CoreMatchers.instanceOf(PokemonDetailState.SuccessState::class.java)
        )
        assertEquals((events.last() as PokemonDetailState.SuccessState).mainColor, randomColor)
    }

    companion object {
        private const val anyId = "0"
        private const val randomColor = 21312312
        private val anyPokemonDetail = PokemonDetail(
            name = "pikachu",
            imageUrl = "https://pokeapi.co/api/v2/pokemon/image",
            typesList = listOf("Electric,Rock")
        )

        private val anyPokemonDetailUI = PokemonDetailUI(
            name = "Pikachu",
            imageUrl = "https://pokeapi.co/api/v2/pokemon/image",
            types = listOf("Electric,Rock")
        )
    }

}