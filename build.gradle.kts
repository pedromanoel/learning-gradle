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
repeat(4) {
    tasks.register("task$it") {
        printName()
    }
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
        repeat(4) { print("$it ")}
    }
}

fun Task.printName() {
    doLast {
        println(name)
    }
}