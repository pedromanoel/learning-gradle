tasks.register("hello") {
    doLast {
        println("Hello")
    }
}

tasks.register("intro") {
    dependsOn("hello")
    doLast {
        println("I'm Gradle")
    }
}

tasks.register("upper") {
    doLast {
        val someString = "mY_NaMe"
        println("Original: $someString")
        println("Upper case: ${someString.toUpperCase()}")
    }
}

tasks.register("count") {
    doLast {
        repeat(4) { print("$it ")}
    }
}
