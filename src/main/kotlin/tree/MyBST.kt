package org.example.tree

import kotlin.math.max

class MyBST<E : Comparable<E>> {
    var root: Node<E>? = null
//    private var _size = 0
//    val size get() = _size
//    var height = 0

    fun insert(value: E) {
//        height = max(height, 1)
        val newNode = Node(value)

        if (root == null) {
            root = newNode
        } else {
            insertNode(2, root!!, newNode)
        }
    }

    private fun insertNode(depth: Int, currentNode: Node<E>, newNode: Node<E>) {
//        height = max(height, depth)

        if (newNode.value == currentNode.value) return
        if (newNode.value < currentNode.value) {
            val leftNode = currentNode.left
            if (leftNode == null) {
                currentNode.left = newNode
            } else {
                insertNode(depth + 1, leftNode, newNode)
            }
        } else {
            val rightNode = currentNode.right
            if (rightNode == null) {
                currentNode.right = newNode
            } else {
                insertNode(depth + 1, rightNode, newNode)
            }
        }
    }

    fun contains(value: E): Boolean {
        return if (root == null) false else search(root!!, value)
    }

    private fun search(currentNode: Node<E>, value: E): Boolean {
        if (currentNode.value == value) return true
        return if (value < currentNode.value) {
            val leftNode = currentNode.left
            if (leftNode == null) false
            else search(leftNode, value)
        } else {
            val rightNode = currentNode.right
            if (rightNode == null) false
            else search(rightNode, value)
        }
    }

    fun remove(value: E) {
        TODO("Implement this")
    }

    fun inorder(): List<E> {
        return inorder(currentNode = root)
    }

    fun inorder(currentNode: Node<E>?): List<E> {
        val left = currentNode?.left?.run { inorder(this) } ?: emptyList()
        val mid = currentNode?.let { listOf(it.value) } ?: emptyList<E>()
        val right = currentNode?.right?.run { inorder(this) } ?: emptyList()
        return left + mid + right
    }

    fun min(): E? {
        return if (root == null) null
        else searchMin(currentNode = root!!)
    }

    private fun searchMin(currentNode: Node<E>): E {
        return if (currentNode.left == null) currentNode.value
        else searchMin(currentNode.left!!)
    }

    fun max(): E? {
        return if (root == null) null
        else searchMax(currentNode = root!!)
    }

    private fun searchMax(currentNode: Node<E>): E {
        return if (currentNode.right == null) currentNode.value
        else searchMax(currentNode.right!!)
    }
}

data class Node<E>(
    val value: E,
    var left: Node<E>? = null,
    var right: Node<E>? = null,
)