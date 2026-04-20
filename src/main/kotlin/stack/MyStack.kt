package org.example.stack

import java.util.Stack

@Suppress("UNCHECKED_CAST")
class MyStack<E : Any>(capacity: Int = 16) {
    val array = Array(capacity) { Any() }
    var top = -1
    val size get() = top + 1

    fun push(value: E) {
        array[++top] = value
    }

    fun pop(): E? {
        if (top == -1) return null

        val valueToDelete = array[top--]

        return valueToDelete as E
    }

    fun peek(): E? {
        if (top == -1) return null

        return array[top] as E
    }

    fun isEmpty(): Boolean {
        return top == -1
    }
}
