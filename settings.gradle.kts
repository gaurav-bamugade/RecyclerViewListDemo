pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        jcenter()
        maven ("https://maven.zebra.com/maven2/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven ("https://maven.zebra.com/maven2/")
    }
}

rootProject.name = "RecyclerViewListDemo"
include(":app")
