package dev.nicolaszein.linkinho.domain.entities

import junit.framework.Assert.assertTrue
import org.junit.Test

class LinkTest {

    @Test
    fun `add click`() {
        val link = Link(longUrl = "https://long.url", title = "A title")

        link.addClick(userAgent = "userAgent", ip = "ip_address")

        assertTrue(link.clicks.size == 1)
        assertTrue(link.clicks[0].userAgent == "userAgent")
        assertTrue(link.clicks[0].ip == "ip_address")
    }
}
