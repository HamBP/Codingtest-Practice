package org.example.list

class MyLinkedList<E> {
    private var _size = 0
    val size
        get() = _size

    private val head: Node<E>? = null

    fun addFirst(value: E) {
        TODO("Implement this")
    }

    fun addLast(value: E) {
        TODO("Implement this")
    }

    fun add(index: Int, value: E) {
        TODO("Implement this")
    }

    operator fun get(index: Int): E {
        TODO("Implement this")
    }

    fun isEmpty(): Boolean {
        TODO("Implement this")
    }

    fun removeFirst(): Boolean {
        TODO()
    }

    fun remove(index: Int) {
        TODO()
    }
}

data class Node<E>(
    val value: E,
    val next: Node<E>? = null,
    val prev: Node<E>? = null,
)