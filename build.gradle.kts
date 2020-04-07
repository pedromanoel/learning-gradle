// Simple task
tasks.register("hello") {
    doLast {
        println("Hello")
    }
}
// Dependent on hello
tasks.register("intro") {
    dependsOn("hello")
    doLast {
        println("I'm Gradle")
    }
}

// declare dependency before taskY exists
tasks.register("taskX") {
    dependsOn("taskY")
    printName()
}

tasks.register("taskY") {
    printName()
}

// dynamic tasks
repeat(4) { count ->
    tasks.register("task$count") {
        doLast {
            println("I'm task numbered $count")
        }
    }
}

tasks.named("task0") {
    dependsOn("task3", "task2")
}

// Task with computation
tasks.register("upper") {
    doLast {
        val someString = "mY_NaMe"
        println("Original: $someString")
        println("Upper case: ${someString.toUpperCase()}")
    }
}
// Task with computation
tasks.register("count") {
    doLast {
        repeat(4) { print("$it ") }
    }
}

// Change task after creation
val helloSolarSystem by tasks.registering {
    doLast {
        println("Hello Earth")
    }
}

helloSolarSystem {
    doFirst {
        println("Hello Venus")
    }
}

helloSolarSystem {
    doLast {
        println("Hello Mars")
    }
}

helloSolarSystem {
    doLast {
        println("Hello Jupiter")
    }
}
// Define extra properties for a task
tasks.register("myTask") {
    extra["myProperty"] = "My value"
}

tasks.register("printTaskProperties") {
    doLast {
        println(tasks["myTask"].extra["myProperty"])
    }
}

// default tasks
defaultTasks("clean", "run")

task("clean") {
    doLast {
        println("Default Cleaning!")
    }
}

tasks.register("run") {
    doLast {
        println("Default run!")
    }
}

// Ant tasks
tasks.register("loadfile") {
    doLast {
        File("./antLoadfileResources")
                .forEachFileInSortedOrder { file ->
                    ant.withGroovyBuilder {
                        "loadfile"("srcFile" to file, "property" to file.name)
                    }

                    println(" *** ${file.name} *** ")
                    println("${ant.properties[file.name]}")
                }
    }
}

fun File.forEachFileInSortedOrder(block: (File) -> Unit) = walk()
        .filter(File::isFile)
        .sorted()
        .forEach(block)

fun Task.printName() {
    doLast {
        println(name)
    }
}