pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "MessageApp"
include(":app")

//database region
include(":database:contract")
include(":database:impl")
//region end

//data region
include(":data:contract")
include(":data:impl")
//region end

//domain region
include(":domain:contract")
include(":domain:model")
include(":domain:impl")
//region end
