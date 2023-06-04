# PokeApp
Android Jetpack Compose Pokemon App based on the api https://pokeapi.co/. Getting all the pokemons in paginated mode, and going to the detail of every pokemon itself.


In the detail of the Pokemon, after loading the image of the pokemon, using **Palette** to determine the **dominant** color and applying it to the background/and types of the Pokemon.

Online mode. 

<img src="https://github.com/diegaspar/PokeApp/assets/45268196/ecf5ca50-4323-4980-b620-2315a9d40067" width="30%" height="30%"/>
<img src="https://github.com/diegaspar/PokeApp/assets/45268196/31c235cf-12f7-4329-8668-6235d52c71fa" width="30%" height="30%"/>

--------

Offline mode.  

<img src="https://github.com/diegaspar/PokeApp/assets/45268196/05156166-20fd-4a01-bc7b-73ff9c018592" width="30%" height="30%"/>
<img src="https://github.com/diegaspar/PokeApp/assets/45268196/a8919f8d-67fb-4844-bd61-3a31c04c6318" width="30%" height="30%"/>


## Clean Architecture based on : 
- **JetpackCompose View**
- **ViewModel**
- **Use Case**
- **Repository**
- **DataSource**


## Main libraries used
- [Kotlin](https://kotlinlang.org/docs/reference/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Koin](https://github.com/InsertKoinIO/koin) (DI)
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Architecture)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Room](https://developer.android.com/training/data-storage/room)
- [Lottie](https://github.com/airbnb/lottie-android)
- [Coil](https://coil-kt.github.io/coil/)
- [Palette](https://developer.android.com/develop/ui/views/graphics/palette-colors)
- [Mockito](https://site.mockito.org/)


-------

# Modules Architecture 
## App
App contains Application and the MainActivity and knows all the other modules, it is in charge of initialising Koin.
No other module knows App.
App controls the Navigation Compose of the project.

## buildSrc
Everything related to Dependencies and Kotlin DSL implementation

## core-base
Used in the features of the app.
Contains everything related to :
- Api and retrofit implementation
- Persistence and its implementation
- Domain
- DataSources

## core-ui
Everything related to the common Compose components UI that can be reusable.

## features
- **pokemonList** > Module related to the list of pokemons (Initial Screen) [Composables-States-ViewModel-UseCase]
- **pokemonDetail** > Module related to the detail of pokemon (Detail Secondary Screen) [Composables-States-ViewModel-UseCase]

----

## MUST TODO IN FUTURE
- Clean and unify the gradle.kts files
- Add UI tests
