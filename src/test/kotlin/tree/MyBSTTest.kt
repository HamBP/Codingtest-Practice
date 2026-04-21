package org.example.tree

import kotlin.test.*

class MyBSTTest {

    private lateinit var bst: MyBST<Int>

    //       5
    //      / \
    //     3   7
    //    / \ / \
    //   1  4 6  8
    @BeforeTest
    fun setUp() {
        bst = MyBST()
        listOf(5, 3, 7, 1, 4, 6, 8).forEach { bst.insert(it) }
    }

    // ── insert ────────────────────────────────────────────────

    @Test
    fun `insert into empty tree sets root`() {
        val t = MyBST<Int>()
        t.insert(5)
        assertEquals(5, t.root?.value)
//        assertEquals(1, t.size)
    }

    @Test
    fun `insert maintains BST order (inorder is sorted)`() {
        assertEquals(listOf(1, 3, 4, 5, 6, 7, 8), bst.inorder())
    }

//    @Test
//    fun `insert increases size`() {
//        assertEquals(7, bst.size)
//    }

//    @Test
//    fun `insert duplicate does not increase size`() {
//        bst.insert(5)
//        assertEquals(7, bst.size)
//    }

    // ── contains ──────────────────────────────────────────────

    @Test
    fun `contains existing values`() {
        listOf(1, 3, 4, 5, 6, 7, 8).forEach {
            assertTrue(bst.contains(it), "expected contains($it) == true")
        }
    }

    @Test
    fun `contains missing value`() {
        assertFalse(bst.contains(9))
        assertFalse(bst.contains(0))
    }

    @Test
    fun `contains on empty tree`() {
        assertFalse(MyBST<Int>().contains(1))
    }

    // ── remove — 리프 노드 ─────────────────────────────────────

    @Test
    fun `remove leaf node`() {
        bst.remove(1)
        assertEquals(listOf(3, 4, 5, 6, 7, 8), bst.inorder())
//        assertEquals(6, bst.size)
        assertFalse(bst.contains(1))
    }

    @Test
    fun `remove rightmost leaf`() {
        bst.remove(8)
        assertEquals(listOf(1, 3, 4, 5, 6, 7), bst.inorder())
//        assertEquals(6, bst.size)
    }

    // ── remove — 자식 1개 ──────────────────────────────────────

    @Test
    fun `remove node with only left child`() {
        // 3 제거 후 4만 남김
        bst.remove(4)
        bst.remove(3) // 이제 3은 left=1만 존재
        assertEquals(listOf(1, 5, 6, 7, 8), bst.inorder())
//        assertEquals(5, bst.size)
    }

    @Test
    fun `remove node with only right child`() {
        bst.remove(1)
        bst.remove(3) // 이제 3은 right=4만 존재
        assertEquals(listOf(4, 5, 6, 7, 8), bst.inorder())
//        assertEquals(5, bst.size)
    }

    // ── remove — 자식 2개 ──────────────────────────────────────

    @Test
    fun `remove node with two children`() {
        bst.remove(3) // left=1, right=4 → successor=4
        assertEquals(listOf(1, 4, 5, 6, 7, 8), bst.inorder())
//        assertEquals(6, bst.size)
        assertFalse(bst.contains(3))
    }

    @Test
    fun `remove root with two children`() {
        bst.remove(5) // successor=6
        assertEquals(listOf(1, 3, 4, 6, 7, 8), bst.inorder())
//        assertEquals(6, bst.size)
        assertFalse(bst.contains(5))
    }

    // ── remove — 특수 케이스 ───────────────────────────────────

    @Test
    fun `remove only node empties tree`() {
        val t = MyBST<Int>().also { it.insert(1) }
        t.remove(1)
        assertTrue(t.inorder().isEmpty())
//        assertEquals(0, t.size)
    }

    @Test
    fun `remove non-existent value does nothing`() {
        bst.remove(99)
        assertEquals(listOf(1, 3, 4, 5, 6, 7, 8), bst.inorder())
//        assertEquals(7, bst.size)
    }

    // ── inorder ───────────────────────────────────────────────

    @Test
    fun `inorder returns sorted list`() {
        assertEquals(listOf(1, 3, 4, 5, 6, 7, 8), bst.inorder())
    }

    @Test
    fun `inorder on empty tree`() {
        assertEquals(emptyList(), MyBST<Int>().inorder())
    }

    // ── min / max ─────────────────────────────────────────────

    @Test
    fun `min returns smallest value`() {
        assertEquals(1, bst.min())
    }

    @Test
    fun `max returns largest value`() {
        assertEquals(8, bst.max())
    }

    @Test
    fun `min on empty tree returns null`() {
        assertNull(MyBST<Int>().min())
    }

    @Test
    fun `max on empty tree returns null`() {
        assertNull(MyBST<Int>().max())
    }

    @Test
    fun `min max after remove`() {
        bst.remove(1)
        bst.remove(8)
        assertEquals(3, bst.min())
        assertEquals(7, bst.max())
    }

    // ── height ────────────────────────────────────────────────

//    @Test
//    fun `height of empty tree is 0`() {
//        assertEquals(0, MyBST<Int>().height)
//    }
//
//    @Test
//    fun `height of single node is 1`() {
//        assertEquals(1, MyBST<Int>().also { it.insert(1) }.height)
//    }
//
//    @Test
//    fun `height of balanced tree`() {
//        assertEquals(3, bst.height)
//    }
//
//    @Test
//    fun `height of right-skewed tree`() {
//        val t = MyBST<Int>()
//        listOf(1, 2, 3, 4).forEach { t.insert(it) }
//        assertEquals(4, t.height)
//    }
}
