package dev.nicolaszein.linkinho.application.services

import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LinkServiceTest {

    @Test
    fun `creates a link`() {
        val link = Link(longUrl = "alou.com", title = "alou title")
        val command = mockk<CreateLinkCommand>()
        val linksRepository = mockk<LinksRepository>()
        every { linksRepository.save(any<Link>()) } returns link
        every { command.toDomain() } returns link

        LinkService(linksRepository).create(command)

        verify(exactly = 1) { linksRepository.save(link) }
    }
}
