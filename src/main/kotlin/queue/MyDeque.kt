package org.example.queue

@Suppress("UNCHECKED_CAST")
class MyDeque<E : Any>(private val capacity: Int = 16) {
    val array = Array(capacity) { Any() }
    var head = 0
    var tail = capacity - 1

    private var _size = 0
    val size get() = _size

    private val isFull get() = (tail + 1) % capacity == head && size > 0

    fun addFirst(value: E) {
        head = (head + capacity - 1) % capacity
        array[head] = value
        _size++
    }

    fun addLast(value: E) {
        tail++
        tail %= capacity

        array[tail] = value
        _size++
    }

    fun removeFirst(): E? {
        if (isEmpty()) return null

        val res = array[head] as E
        head++
        head %= capacity

        _size--

        return res
    }

    fun removeLast(): E? {
        if (isEmpty()) return null

        val res = array[tail] as E
        tail = (tail + capacity - 1) % capacity
        _size--

        return res
    }

    fun peekFirst(): E? {
        if (isEmpty()) return null

        return array[head] as E
    }

    fun peekLast(): E? {
        if (isEmpty()) return null

        return array[tail] as E
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
}
