package dev.nicolaszein.linkinho.infra

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

class Base62Test {
    private val base62 = Base62()

    @Test
    fun encode() {
        val value = base62.encode(9999999999.toBigInteger())

        assertTrue(value == "aUKYOz")
    }

    @Test
    fun `encode passing 0 as parameter`() {
        val value = base62.encode(0.toBigInteger())

        assertTrue(value == "0")
    }
}
