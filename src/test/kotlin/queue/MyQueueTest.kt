package org.example.queue

import kotlin.test.*

class MyQueueTest {

    private lateinit var queue: MyQueue<Int>

    @BeforeTest
    fun setUp() {
        queue = MyQueue()
    }

    // ── enqueue ───────────────────────────────────────────────

    @Test
    fun `enqueue on empty queue`() {
        queue.enqueue(1)
        assertEquals(1, queue.size)
        assertEquals(1, queue.peek())
    }

    @Test
    fun `enqueue follows FIFO order`() {
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        assertEquals(1, queue.peek())
        assertEquals(3, queue.size)
    }

    @Test
    fun `enqueue increases size`() {
        repeat(5) { queue.enqueue(it) }
        assertEquals(5, queue.size)
    }

    // ── dequeue ───────────────────────────────────────────────

    @Test
    fun `dequeue returns front and removes it`() {
        queue.enqueue(1)
        queue.enqueue(2)
        val result = queue.dequeue()
        assertEquals(1, result)
        assertEquals(1, queue.size)
    }

    @Test
    fun `dequeue decreases size`() {
        queue.enqueue(1)
        queue.enqueue(2)
        queue.dequeue()
        assertEquals(1, queue.size)
    }

    @Test
    fun `dequeue on single element empties queue`() {
        queue.enqueue(42)
        queue.dequeue()
        assertTrue(queue.isEmpty())
        assertEquals(0, queue.size)
    }

    @Test
    fun `dequeue on empty queue returns null`() {
        assertNull(queue.dequeue())
    }

    @Test
    fun `dequeue returns elements in FIFO order`() {
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        assertEquals(1, queue.dequeue())
        assertEquals(2, queue.dequeue())
        assertEquals(3, queue.dequeue())
        assertNull(queue.dequeue())
    }

    // ── peek ──────────────────────────────────────────────────

    @Test
    fun `peek returns front without removing`() {
        queue.enqueue(1)
        queue.enqueue(2)
        assertEquals(1, queue.peek())
        assertEquals(2, queue.size)
    }

    @Test
    fun `peek does not change size`() {
        queue.enqueue(10)
        queue.peek()
        queue.peek()
        assertEquals(1, queue.size)
    }

    @Test
    fun `peek on empty queue returns null`() {
        assertNull(queue.peek())
    }

    // ── 원형 큐 wrap-around ────────────────────────────────────

    @Test
    fun `reuse slots after dequeue (wrap-around)`() {
        val q = MyQueue<Int>(capacity = 4)
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)
        q.dequeue()  // front 이동
        q.dequeue()
        q.enqueue(4)
        q.enqueue(5) // rear가 wrap-around 되어야 함
        assertEquals(3, q.dequeue())
        assertEquals(4, q.dequeue())
        assertEquals(5, q.dequeue())
        assertNull(q.dequeue())
    }

    // ── isEmpty / size ────────────────────────────────────────

    @Test
    fun `isEmpty on new queue`() {
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `isEmpty false after enqueue`() {
        queue.enqueue(1)
        assertFalse(queue.isEmpty())
    }

    @Test
    fun `isEmpty true after all elements dequeued`() {
        queue.enqueue(1)
        queue.enqueue(2)
        queue.dequeue()
        queue.dequeue()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `size tracks enqueue and dequeue correctly`() {
        assertEquals(0, queue.size)
        queue.enqueue(1)
        assertEquals(1, queue.size)
        queue.enqueue(2)
        assertEquals(2, queue.size)
        queue.dequeue()
        assertEquals(1, queue.size)
        queue.dequeue()
        assertEquals(0, queue.size)
    }
}
