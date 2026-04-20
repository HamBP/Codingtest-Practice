package org.example.queue

@Suppress("UNCHECKED_CAST")
class MyQueue<E : Any>(private var capacity: Int = 16) {
    val array = Array(capacity) { Any() }
    var head = 0
    var tail = 0

    private var _size = 0
    val size get() = _size

    private val isFull get() = head == tail && !isEmpty()

    fun enqueue(value: E) {
        array[tail++] = value
        tail %= capacity
        _size++
    }

    fun dequeue(): E? {
        if (isEmpty()) return null

        val res = array[head++] as E
        head %= capacity
        _size--
        return res
    }

    fun peek(): E? {
        if (isEmpty()) return null

        return array[head] as E
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
}
