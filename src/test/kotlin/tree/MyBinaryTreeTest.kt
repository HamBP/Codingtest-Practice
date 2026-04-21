package org.example.tree

import kotlin.test.*

class MyBinaryTreeTest {

    private lateinit var tree: MyBinaryTree<Int>

    //        1
    //       / \
    //      2   3
    //     / \ / \
    //    4  5 6  7
    @BeforeTest
    fun setUp() {
        tree = MyBinaryTree()
        listOf(1, 2, 3, 4, 5, 6, 7).forEach { tree.insert(it) }
    }

    // ── insert ────────────────────────────────────────────────

    @Test
    fun `insert into empty tree sets root`() {
        val t = MyBinaryTree<Int>()
        t.insert(1)
        assertEquals(1, t.root?.value)
        assertEquals(1, t.size)
    }

    @Test
    fun `insert fills level order (complete binary tree)`() {
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7), tree.levelorder())
    }

    @Test
    fun `insert increases size`() {
        assertEquals(7, tree.size)
    }

    // ── preorder ──────────────────────────────────────────────

    @Test
    fun `preorder on full tree`() {
        assertEquals(listOf(1, 2, 4, 5, 3, 6, 7), tree.preorder())
    }

    @Test
    fun `preorder on single node`() {
        val t = MyBinaryTree<Int>().also { it.insert(1) }
        assertEquals(listOf(1), t.preorder())
    }

    @Test
    fun `preorder on empty tree`() {
        assertEquals(emptyList(), MyBinaryTree<Int>().preorder())
    }

    // ── inorder ───────────────────────────────────────────────

    @Test
    fun `inorder on full tree`() {
        assertEquals(listOf(4, 2, 5, 1, 6, 3, 7), tree.inorder())
    }

    @Test
    fun `inorder on single node`() {
        val t = MyBinaryTree<Int>().also { it.insert(1) }
        assertEquals(listOf(1), t.inorder())
    }

    @Test
    fun `inorder on empty tree`() {
        assertEquals(emptyList(), MyBinaryTree<Int>().inorder())
    }

    // ── postorder ─────────────────────────────────────────────

    @Test
    fun `postorder on full tree`() {
        assertEquals(listOf(4, 5, 2, 6, 7, 3, 1), tree.postorder())
    }

    @Test
    fun `postorder on single node`() {
        val t = MyBinaryTree<Int>().also { it.insert(1) }
        assertEquals(listOf(1), t.postorder())
    }

    @Test
    fun `postorder on empty tree`() {
        assertEquals(emptyList(), MyBinaryTree<Int>().postorder())
    }

    // ── levelorder ────────────────────────────────────────────

    @Test
    fun `levelorder on full tree`() {
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7), tree.levelorder())
    }

    @Test
    fun `levelorder on single node`() {
        val t = MyBinaryTree<Int>().also { it.insert(1) }
        assertEquals(listOf(1), t.levelorder())
    }

    @Test
    fun `levelorder on empty tree`() {
        assertEquals(emptyList(), MyBinaryTree<Int>().levelorder())
    }

    // ── contains ──────────────────────────────────────────────

    @Test
    fun `contains existing value`() {
        assertTrue(tree.contains(4))
        assertTrue(tree.contains(1))
        assertTrue(tree.contains(7))
    }

    @Test
    fun `contains missing value`() {
        assertFalse(tree.contains(9))
        assertFalse(tree.contains(0))
    }

    @Test
    fun `contains on empty tree`() {
        assertFalse(MyBinaryTree<Int>().contains(1))
    }

    // ── height ────────────────────────────────────────────────

    @Test
    fun `height of empty tree is 0`() {
        assertEquals(0, MyBinaryTree<Int>().height())
    }

    @Test
    fun `height of single node is 1`() {
        val t = MyBinaryTree<Int>().also { it.insert(1) }
        assertEquals(1, t.height())
    }

    @Test
    fun `height of full 3-level tree`() {
        assertEquals(3, tree.height())
    }

    @Test
    fun `height of left-skewed tree equals node count`() {
        val t = MyBinaryTree<Int>()
        // insert 순서를 제어해 좌편향 트리 수동 구성
        t.root = Node(1, left = Node(2, left = Node(3)))
        assertEquals(3, t.height())
    }

    // ── size ──────────────────────────────────────────────────

    @Test
    fun `size of empty tree is 0`() {
        assertEquals(0, MyBinaryTree<Int>().size)
    }

    @Test
    fun `size after inserts`() {
        assertEquals(7, tree.size)
    }
}
