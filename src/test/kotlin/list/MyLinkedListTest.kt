package org.example.list

import kotlin.test.*

class MyLinkedListTest {

    private lateinit var list: MyLinkedList<Int>

    @BeforeTest
    fun setUp() {
        list = MyLinkedList()
    }

    // ── addFirst ──────────────────────────────────────────────

    @Test
    fun `addFirst on empty list`() {
        list.addFirst(1)
        assertEquals(1, list.size)
        assertEquals(1, list[0])
    }

    @Test
    fun `addFirst prepends element`() {
        list.addFirst(1)
        list.addFirst(2)
        assertEquals(2, list[0])
        assertEquals(1, list[1])
        assertEquals(2, list.size)
    }

    // ── addLast ───────────────────────────────────────────────

    @Test
    fun `addLast on empty list`() {
        list.addLast(1)
        assertEquals(1, list.size)
        assertEquals(1, list[0])
    }

    @Test
    fun `addLast appends element`() {
        list.addLast(1)
        list.addLast(2)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(2, list.size)
    }

    // ── add(index, value) ─────────────────────────────────────

    @Test
    fun `add at index 0 equals addFirst`() {
        list.addLast(1)
        list.addLast(3)
        list.add(0, 99)
        assertEquals(99, list[0])
        assertEquals(1, list[1])
    }

    @Test
    fun `add at last index equals addLast`() {
        list.addLast(1)
        list.addLast(2)
        list.add(list.size, 99)
        assertEquals(99, list[list.size - 1])
    }

    @Test
    fun `add at middle index`() {
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.add(1, 99)
        assertEquals(1, list[0])
        assertEquals(99, list[1])
        assertEquals(2, list[2])
        assertEquals(3, list[3])
        assertEquals(4, list.size)
    }

    @Test
    fun `add with invalid index throws`() {
        assertFailsWith<IndexOutOfBoundsException> { list.add(-1, 1) }
        assertFailsWith<IndexOutOfBoundsException> { list.add(1, 1) }
    }

    // ── get ───────────────────────────────────────────────────

    @Test
    fun `get returns correct values`() {
        list.addLast(10)
        list.addLast(20)
        list.addLast(30)
        assertEquals(10, list[0])
        assertEquals(20, list[1])
        assertEquals(30, list[2])
    }

    @Test
    fun `get with invalid index throws`() {
        list.addLast(1)
        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[1] }
    }

    @Test
    fun `get on empty list throws`() {
        assertFailsWith<IndexOutOfBoundsException> { list[0] }
    }

    // ── removeFirst ───────────────────────────────────────────

    @Test
    fun `removeFirst returns true and removes head`() {
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        val result = list.removeFirst()
        assertTrue(result)
        assertEquals(2, list[0])
        assertEquals(2, list.size)
    }

    @Test
    fun `removeFirst on single node empties list`() {
        list.addLast(1)
        list.removeFirst()
        assertTrue(list.isEmpty())
        assertEquals(0, list.size)
    }

    @Test
    fun `removeFirst on empty list returns false`() {
        val result = list.removeFirst()
        assertFalse(result)
    }

    // ── remove(index) ─────────────────────────────────────────

    @Test
    fun `remove first index`() {
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.remove(0)
        assertEquals(2, list[0])
        assertEquals(2, list.size)
    }

    @Test
    fun `remove last index`() {
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.remove(2)
        assertEquals(2, list[list.size - 1])
        assertEquals(2, list.size)
    }

    @Test
    fun `remove middle index`() {
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.remove(1)
        assertEquals(1, list[0])
        assertEquals(3, list[1])
        assertEquals(2, list.size)
    }

    @Test
    fun `remove single node empties list`() {
        list.addLast(42)
        list.remove(0)
        assertTrue(list.isEmpty())
    }

    @Test
    fun `remove with invalid index throws`() {
        list.addLast(1)
        assertFailsWith<IndexOutOfBoundsException> { list.remove(-1) }
        assertFailsWith<IndexOutOfBoundsException> { list.remove(1) }
    }

    // ── isEmpty / size ────────────────────────────────────────

    @Test
    fun `isEmpty on new list`() {
        assertTrue(list.isEmpty())
    }

    @Test
    fun `isEmpty false after add`() {
        list.addFirst(1)
        assertFalse(list.isEmpty())
    }

    @Test
    fun `size tracks operations correctly`() {
        assertEquals(0, list.size)
        list.addFirst(1)
        assertEquals(1, list.size)
        list.addLast(2)
        assertEquals(2, list.size)
        list.removeFirst()
        assertEquals(1, list.size)
        list.remove(0)
        assertEquals(0, list.size)
    }
}
