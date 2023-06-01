pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "PokeApp"
include (":app")
include (":features")
include (":persistence")
include(":features:PokemonList")
include(":features:pokemonDetail")
include(":core-base")
