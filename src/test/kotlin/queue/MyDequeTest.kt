package org.example.queue

import kotlin.test.*

class MyDequeTest {

    private lateinit var deque: MyDeque<Int>

    @BeforeTest
    fun setUp() {
        deque = MyDeque()
    }

    // ── addFirst ──────────────────────────────────────────────

    @Test
    fun `addFirst on empty deque`() {
        deque.addFirst(1)
        assertEquals(1, deque.size)
        assertEquals(1, deque.peekFirst())
    }

    @Test
    fun `addFirst prepends elements`() {
        deque.addFirst(1)
        deque.addFirst(2)
        deque.addFirst(3)
        assertEquals(3, deque.peekFirst())
        assertEquals(1, deque.peekLast())
        assertEquals(3, deque.size)
    }

    // ── addLast ───────────────────────────────────────────────

    @Test
    fun `addLast on empty deque`() {
        deque.addLast(1)
        assertEquals(1, deque.size)
        assertEquals(1, deque.peekLast())
    }

    @Test
    fun `addLast appends elements`() {
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(1, deque.peekFirst())
        assertEquals(3, deque.peekLast())
        assertEquals(3, deque.size)
    }

    @Test
    fun `addFirst then addLast`() {
        deque.addFirst(1)
        deque.addLast(2)
        assertEquals(1, deque.peekFirst())
        assertEquals(2, deque.peekLast())
    }

    @Test
    fun `addLast then addFirst`() {
        deque.addLast(1)
        deque.addFirst(2)
        assertEquals(2, deque.peekFirst())
        assertEquals(1, deque.peekLast())
    }

    // ── removeFirst ───────────────────────────────────────────

    @Test
    fun `removeFirst returns front and removes it`() {
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(1, deque.removeFirst())
        assertEquals(2, deque.peekFirst())
        assertEquals(2, deque.size)
    }

    @Test
    fun `removeFirst on single element empties deque`() {
        deque.addLast(42)
        assertEquals(42, deque.removeFirst())
        assertTrue(deque.isEmpty())
        assertEquals(0, deque.size)
    }

    @Test
    fun `removeFirst on empty deque returns null`() {
        assertNull(deque.removeFirst())
    }

    // ── removeLast ────────────────────────────────────────────

    @Test
    fun `removeLast returns rear and removes it`() {
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(3, deque.removeLast())
        assertEquals(2, deque.peekLast())
        assertEquals(2, deque.size)
    }

    @Test
    fun `removeLast on single element empties deque`() {
        deque.addFirst(42)
        assertEquals(42, deque.removeLast())
        assertTrue(deque.isEmpty())
        assertEquals(0, deque.size)
    }

    @Test
    fun `removeLast on empty deque returns null`() {
        assertNull(deque.removeLast())
    }

    @Test
    fun `removeFirst and removeLast alternate`() {
        deque.addLast(1)
        deque.addLast(2)
        deque.addLast(3)
        assertEquals(1, deque.removeFirst())
        assertEquals(3, deque.removeLast())
        assertEquals(2, deque.removeFirst())
        assertNull(deque.removeFirst())
    }

    // ── peekFirst / peekLast ──────────────────────────────────

    @Test
    fun `peekFirst does not remove element`() {
        deque.addLast(1)
        deque.addLast(2)
        assertEquals(1, deque.peekFirst())
        assertEquals(2, deque.size)
    }

    @Test
    fun `peekLast does not remove element`() {
        deque.addLast(1)
        deque.addLast(2)
        assertEquals(2, deque.peekLast())
        assertEquals(2, deque.size)
    }

    @Test
    fun `peekFirst on empty deque returns null`() {
        assertNull(deque.peekFirst())
    }

    @Test
    fun `peekLast on empty deque returns null`() {
        assertNull(deque.peekLast())
    }

    // ── 원형 배열 wrap-around ──────────────────────────────────

    @Test
    fun `addFirst wrap-around`() {
        val d = MyDeque<Int>(capacity = 4)
        d.addLast(1)
        d.addLast(2)
        d.addFirst(0)  // head가 capacity - 1로 wrap-around
        d.addFirst(-1)
        assertEquals(-1, d.peekFirst())
        assertEquals(2, d.peekLast())
        assertEquals(4, d.size)
    }

    @Test
    fun `removeLast wrap-around`() {
        val d = MyDeque<Int>(capacity = 4)
        d.addFirst(3)
        d.addFirst(2)
        d.addFirst(1)
        assertEquals(3, d.removeLast())
        assertEquals(2, d.removeLast())
        assertEquals(1, d.removeLast())
        assertNull(d.removeLast())
    }

    @Test
    fun `reuse slots after remove (wrap-around)`() {
        val d = MyDeque<Int>(capacity = 4)
        d.addLast(1)
        d.addLast(2)
        d.removeFirst()
        d.removeFirst()
        d.addLast(3)
        d.addLast(4)
        d.addFirst(0)
        assertEquals(0, d.peekFirst())
        assertEquals(4, d.peekLast())
        assertEquals(3, d.size)
    }

    // ── isEmpty / size ────────────────────────────────────────

    @Test
    fun `isEmpty on new deque`() {
        assertTrue(deque.isEmpty())
    }

    @Test
    fun `isEmpty false after add`() {
        deque.addFirst(1)
        assertFalse(deque.isEmpty())
    }

    @Test
    fun `size tracks all operations correctly`() {
        assertEquals(0, deque.size)
        deque.addFirst(1)
        assertEquals(1, deque.size)
        deque.addLast(2)
        assertEquals(2, deque.size)
        deque.removeFirst()
        assertEquals(1, deque.size)
        deque.removeLast()
        assertEquals(0, deque.size)
    }
}
