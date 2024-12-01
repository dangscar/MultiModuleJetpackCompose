package com.nlhd.multimodulejetpackcompose

 fun main() {
    doSomething {
        println("Action is running")
    }
}

inline fun doSomething(action: () -> Unit) {
    println("Before action")
    action()
    println("After action")
}