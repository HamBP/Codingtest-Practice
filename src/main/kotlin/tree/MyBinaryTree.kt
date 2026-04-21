package org.example.tree

class MyBinaryTree<E : Comparable<E>> {
    var root: Node<E>? = null
    private var _size = 0
    val size get() = _size

    fun insert(value: E) {
        TODO("Implement this")
    }

    fun contains(value: E): Boolean {
        TODO("Implement this")
    }

    fun preorder(): List<E> {
        TODO("Implement this")
    }

    fun inorder(): List<E> {
        TODO("Implement this")
    }

    fun postorder(): List<E> {
        TODO("Implement this")
    }

    fun levelorder(): List<E> {
        TODO("Implement this")
    }

    fun height(): Int {
        TODO("Implement this")
    }
}

data class Node<E>(
    val value: E,
    var left: Node<E>? = null,
    var right: Node<E>? = null,
)
