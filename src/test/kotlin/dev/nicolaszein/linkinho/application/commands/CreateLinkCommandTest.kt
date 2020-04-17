package dev.nicolaszein.linkinho.application.commands

import dev.nicolaszein.linkinho.domain.entities.Link
import org.junit.Assert.assertTrue
import org.junit.Test

class CreateLinkCommandTest {

    @Test
    fun `returns domain link entity`() {
        val command = CreateLinkCommand(longUrl = "https://alou.com.br", title = "Alou website")

        val link = command.toDomain()

        assertTrue(link is Link)
    }

    @Test
    fun `returns correct domain attributes`() {
        val command = CreateLinkCommand(longUrl = "https://alou.com.br", title = "Alou website")

        val link = command.toDomain()

        assertTrue(link.longUrl == command.longUrl)
        assertTrue(link.title == command.title)
    }
}
