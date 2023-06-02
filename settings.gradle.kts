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
include(":features:pokemonList")
include(":features:pokemonDetail")
include(":core-base")
include(":core-ui")
