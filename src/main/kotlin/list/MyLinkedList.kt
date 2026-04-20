package org.example.list

class MyLinkedList<E> {
    private var _size = 0
    val size
        get() = _size

    private var head: Node<E>? = null

    fun addFirst(value: E) {
        val newNode = Node(value = value)

        if (head == null) {
            head = newNode
            head!!.next = newNode
            head!!.prev = newNode
            _size++
            return
        }

        val oldHead = head!!
        head = newNode

        head!!.next = oldHead
        head!!.prev = oldHead.prev

        oldHead.prev!!.next = head
        oldHead.prev = head

        _size++
    }

    fun addLast(value: E) {
        val newNode = Node(value = value)

        if (head == null) {
            head = newNode
            head!!.next = newNode
            head!!.prev = newNode
            _size++
            return
        }

        val oldLast = head!!.prev

        // 기존 last와의 관계 정리
        oldLast!!.next = newNode
        newNode.prev = oldLast

        // head와의 관계 정리
        newNode.next = head
        head!!.prev = newNode

        _size++
    }

    fun add(index: Int, value: E) {
        check(index < size) { "Index $index out of size < $size" }

        if (index == 0) {
            addFirst(value)
            return
        }

        val newNode = Node(value = value)

        var cur = head
        repeat(index) {
            cur = cur!!.next
        }

        val targetPrev = cur!!.prev
        val targetNext = cur

        targetPrev!!.next = newNode
        newNode.prev = targetPrev

        targetNext.prev = newNode
        newNode.next = targetNext

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