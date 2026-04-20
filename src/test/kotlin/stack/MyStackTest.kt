package org.example.stack

import kotlin.test.*

class MyStackTest {

    private lateinit var stack: MyStack<Int>

    @BeforeTest
    fun setUp() {
        stack = MyStack()
    }

    // ── push ──────────────────────────────────────────────────

    @Test
    fun `push on empty stack`() {
        stack.push(1)
        assertEquals(1, stack.size)
        assertEquals(1, stack.peek())
    }

    @Test
    fun `push follows LIFO order`() {
        stack.push(1)
        stack.push(2)
        stack.push(3)
        assertEquals(3, stack.peek())
        assertEquals(3, stack.size)
    }

    @Test
    fun `push increases size`() {
        repeat(5) { stack.push(it) }
        assertEquals(5, stack.size)
    }

    // ── pop ───────────────────────────────────────────────────

    @Test
    fun `pop returns top and removes it`() {
        stack.push(1)
        stack.push(2)
        val result = stack.pop()
        assertEquals(2, result)
        assertEquals(1, stack.size)
    }

    @Test
    fun `pop decreases size`() {
        stack.push(1)
        stack.push(2)
        stack.pop()
        assertEquals(1, stack.size)
    }

    @Test
    fun `pop on single element empties stack`() {
        stack.push(42)
        stack.pop()
        assertTrue(stack.isEmpty())
        assertEquals(0, stack.size)
    }

    @Test
    fun `pop on empty stack returns null`() {
        assertNull(stack.pop())
    }

    @Test
    fun `pop returns elements in LIFO order`() {
        stack.push(1)
        stack.push(2)
        stack.push(3)
        assertEquals(3, stack.pop())
        assertEquals(2, stack.pop())
        assertEquals(1, stack.pop())
        assertNull(stack.pop())
    }

    // ── peek ──────────────────────────────────────────────────

    @Test
    fun `peek returns top without removing`() {
        stack.push(1)
        stack.push(2)
        assertEquals(2, stack.peek())
        assertEquals(2, stack.size)
    }

    @Test
    fun `peek does not change size`() {
        stack.push(10)
        stack.peek()
        stack.peek()
        assertEquals(1, stack.size)
    }

    @Test
    fun `peek on empty stack returns null`() {
        assertNull(stack.peek())
    }

    // ── isEmpty / size ────────────────────────────────────────

    @Test
    fun `isEmpty on new stack`() {
        assertTrue(stack.isEmpty())
    }

    @Test
    fun `isEmpty false after push`() {
        stack.push(1)
        assertFalse(stack.isEmpty())
    }

    @Test
    fun `isEmpty true after all elements popped`() {
        stack.push(1)
        stack.push(2)
        stack.pop()
        stack.pop()
        assertTrue(stack.isEmpty())
    }

    @Test
    fun `size tracks push and pop correctly`() {
        assertEquals(0, stack.size)
        stack.push(1)
        assertEquals(1, stack.size)
        stack.push(2)
        assertEquals(2, stack.size)
        stack.pop()
        assertEquals(1, stack.size)
        stack.pop()
        assertEquals(0, stack.size)
    }
}
