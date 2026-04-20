package org.example.stack

class MyStack<E> {
    val list = mutableListOf<E>()
    val size get() = list.size

    fun push(value: E) {
        list.add(list.size, value)
    }

    fun pop(): E? {
        return list.removeLastOrNull()
    }

    fun peek(): E? {
        return list.lastOrNull()
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}
