package dev.nicolaszein.linkinho.application.services

import dev.nicolaszein.linkinho.application.commands.AddLinkClickCommand
import dev.nicolaszein.linkinho.application.commands.CreateLinkCommand
import dev.nicolaszein.linkinho.domain.entities.Link
import dev.nicolaszein.linkinho.domain.repositories.LinksRepository
import dev.nicolaszein.linkinho.infra.Base62
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test

class LinkServiceTest {

    @Test
    fun `creates a link`() {
        val link = spyk(Link(id = 1, longUrl = "alou.com", title = "alou title"))
        val command = mockk<CreateLinkCommand>()
        val linksRepository = mockk<LinksRepository>()
        val base62 = mockk<Base62>()
        every { linksRepository.save(any<Link>()) } returns link
        every { command.toDomain() } returns link
        every { base62.encode(1.toBigInteger()) } returns "abc"

        LinkService(linksRepository, base62).create(command)

        verify(exactly = 1) { base62.encode(1.toBigInteger()) }
        verify(exactly = 2) { linksRepository.save(link) }
        verify(exactly = 1) { link.tag = "abc" }
    }

    @Test
    fun `add link click`() {
        val link = spyk(Link(id = 1, longUrl = "alou.com", title = "alou title"))
        val command = AddLinkClickCommand(link = link, userAgent = "userAgent", ip = "ip_address")
        val linksRepository = mockk<LinksRepository>()
        val base62 = mockk<Base62>()
        every { linksRepository.save(any<Link>()) } returns link

        LinkService(linksRepository, base62).addLinkClick(command)

        verify(exactly = 1) { link.addClick(userAgent = "userAgent", ip = "ip_address") }
        verify(exactly = 1) { linksRepository.save(link) }
    }
}
