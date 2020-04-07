# Learning Gradle

Gradle has two main concepts: **Project** and **Task**.

## Project

* It represents something that must be done, like:
    * a zip archive
    * a compiled package
* Some action that must be taken:
    * a deploy to an environment
    * a configuration
    
## Task

It's an atomic piece of work which a build performs.

They are registered with:

```kotlin
tasks.register("taskName") {
    // ...
}
```

Tasks can depend on one another, forming a task graph when called:

```kotlin
tasks.register("taskName") {
    dependOn("otherTask")
    // ...
}
```

## The build script is a program

It is possible to use any capability from kotlin/groovy to create a build script, including:

* dynamically create tasks
* organize with functions and classes
* using dependencies from other libraries

```kotlin
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("group:artifact:version")
    }
}
```

## It is compatible with Ant

You can call ant targets from gradle tasks

```kotlin
tasks.register("atask") {
    ant.withGroovyBuilder {
        "loadfile"("srcFile" to file, "property" to file.name)
    }
}
```
