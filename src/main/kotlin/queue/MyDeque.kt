package org.example.queue

@Suppress("UNCHECKED_CAST")
class MyDeque<E : Any>(private val capacity: Int = 16) {
    val array = Array(capacity) { Any() }
    var head = 0
    var tail = 0

    private var _size = 0
    val size get() = _size

    fun addFirst(value: E) {
        TODO("Implement this")
    }

    fun addLast(value: E) {
        TODO("Implement this")
    }

    fun removeFirst(): E? {
        TODO("Implement this")
    }

    fun removeLast(): E? {
        TODO("Implement this")
    }

    fun peekFirst(): E? {
        TODO("Implement this")
    }

    fun peekLast(): E? {
        TODO("Implement this")
    }

    fun isEmpty(): Boolean {
        TODO("Implement this")
    }
}
