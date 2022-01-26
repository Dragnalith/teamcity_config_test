import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.1"

project {

    buildType(Experiment3_Print)
}

object PrePrint : BuildType({
    id("Print")
    name = "PrePrint"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "PrePrint Do"
            scriptContent = "type README.md"
        }
    }
})

object Experiment3_Print : BuildType({
    id("Print")
    name = "Hello World Git6"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "MyStep6"
            scriptContent = "type README.md"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }

    dependencies {
        snapshot(PrePrint) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})