package org.example.tree

class MyBST<E : Comparable<E>> {
    var root: Node<E>? = null
    private var _size = 0
    val size get() = _size

    fun insert(value: E) {
        TODO("Implement this")

        _size++
    }

    fun contains(value: E): Boolean {
        TODO("Implement this")
    }

    fun remove(value: E) {
        TODO("Implement this")
    }

    fun inorder(): List<E> {
        TODO("Implement this")
    }

    fun min(): E? {
        TODO("Implement this")
    }

    fun max(): E? {
        TODO("Implement this")
    }

    fun height(): Int {
        TODO("Implement this")
    }
}

data class Node<E>(
    val value: E,
    val left: Node<E>? = null,
    val right: Node<E>? = null,
)