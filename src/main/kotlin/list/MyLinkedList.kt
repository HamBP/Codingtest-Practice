package org.example.list

class MyLinkedList<E> {
    private var _size = 0
    val size
        get() = _size

    private var head: Node<E>? = null

    fun addFirst(value: E) {
        if (head == null) {
            head = Node(value = value)
            _size++
            return
        }

        val oldHead = head!!
        head = Node(value = value)

        head!!.next = oldHead
        head!!.prev = oldHead.prev

        oldHead.prev!!.next = head
        oldHead.prev = head

        _size++
    }

    fun addLast(value: E) {
        if (head == null) {
            head = Node(value = value)
        }

        TODO("Implement this")

        _size++
    }

    fun add(index: Int, value: E) {
        TODO("Implement this")

        _size++
    }

    operator fun get(index: Int): E {
        check(index < size) { "Index $index out of size < $size" }

        var cur = head
        repeat(index) {
            cur = cur?.next
        }

        return cur!!.value
    }

    fun isEmpty(): Boolean {
        return head == null
    }

    fun removeFirst(): Boolean {
        TODO()

        _size--
    }

    fun remove(index: Int) {
        TODO()

        _size--
    }
}

data class Node<E>(
    val value: E,
    var next: Node<E>? = null,
    var prev: Node<E>? = null,
)